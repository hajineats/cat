package com.se701.cat.controller;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import com.se701.cat.dto.TestResponseDTO;
import com.se701.cat.entity.Question;
import com.se701.cat.entity.TestType;
import com.se701.cat.entity.User;
import com.se701.cat.service.QuestionService;
import com.se701.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("mst")
public class MultistageTestController {
    private static final int NUM_OF_ITEM_PARAMETERS = 4;
    private static final int NUM_OF_MODULES = 4;

    private static final int DISCRIMINATION_PARAMETER = 0;

    private static final int DEFAULT_DISCRIMINATION = 1;

    private static final int DIFFICULTY_PARAMETER = 1;

    private static final int PSEUDO_GUESSING_PARAMETER = 2;

    private static final int DEFAULT_PSEUDO_GUESSING = 0;

    private static final int INATTENTION_PARAMETER = 3;

    private static final int DEFAULT_INATTENTION = 1;

    // not actually immutable
    private static final double[][] TRANSITION_MATRIX = new double[][]{
            {0, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @GetMapping("{moduleId}")
    public ResponseEntity<List<Question>> getModuleQuestions(@PathVariable Integer moduleId) {
        return ResponseEntity.ok(questionService.findAllMultistageQuestionsByModuleNumber(moduleId));
    }

    /**
     * @param testResponseDTO
     * @return the number of the next module or null if the test is complete
     */
    @PostMapping()
    public ResponseEntity submitResults(@RequestBody TestResponseDTO testResponseDTO) {
        User user = userService.findUserById(testResponseDTO.getUserId());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (user.getShouldTakes().isEmpty() || user.getShouldTakes().get(0) == TestType.FL) {
            return ResponseEntity.badRequest().body("User should not be taking the MST test");
        }

        userService.addUserResponses(true, testResponseDTO.getResponses(), user);

        Map<String, String> mstResponses = user.getMstTestResponses();
        List<Question> allMultistageQuestions = questionService.findAllMultistageQuestions();
        double abilityEstimate = calculateAbilityEstimate(mstResponses, allMultistageQuestions);
        user.setMstScore(abilityEstimate);

        boolean finished = user.getCurrentModule() != User.DEFAULT_STAGE;
        if (!finished) {
            int nextModule = calculateNextModule(abilityEstimate, mstResponses, allMultistageQuestions);
            user.setCurrentModule(nextModule);
        } else {
            user.getShouldTakes().remove(0);
        }
        userService.updateUser(user);

        return ResponseEntity.noContent().build();
    }

    private double calculateAbilityEstimate(Map<String, String> mstResponses, List<Question> questionBank) {
        double[][] itemBank = initialise1PLItemBank(mstResponses.size());
        double[] responses = new double[mstResponses.size()];

        int index = 0;
        for (String questionId : mstResponses.keySet()) {
            Question q = questionBank
                    .stream()
                    .filter((element) -> element.getId().equals(questionId))
                    .findAny()
                    .orElseThrow();
            itemBank[index][DIFFICULTY_PARAMETER] = q.getDifficultyParameter();
            responses[index] = q.getCorrectAnswer().equalsIgnoreCase(mstResponses.get(questionId)) ? 1 : 0;
            index++;
        }

        RCaller caller = RCaller.create();
        RCode code = RCode.create();

        code.addRCode("library(\"mstR\")");
        code.addDoubleMatrix("itemBank", itemBank);
        code.addDoubleArray("responses", responses);

        code.addRCode("result<-eapEst(itemBank, responses, model = NULL, D = 1, priorDist = \"norm\", priorPar = c(0, 1), lower = -4, upper = 4, nqp = 33)");

        caller.setRCode(code);
        caller.runAndReturnResult("result");
        return caller.getParser().getAsDoubleArray("result")[0];
    }

    private int calculateNextModule(double abilityEstimate, Map<String, String> mstResponses, List<Question> allMultistageQuestions) {
        RCaller caller = RCaller.create();
        RCode code = RCode.create();

        code.addRCode("library(\"mstR\")");

        double[][] itemBank = initialise1PLItemBank(allMultistageQuestions.size());
        double[][] modules = new double[allMultistageQuestions.size()][NUM_OF_MODULES];
        List<Double> questionsAnswered = new ArrayList<>();

        for (int i = 0; i < allMultistageQuestions.size(); i++) {
            Question question = allMultistageQuestions.get(i);
            itemBank[i][DIFFICULTY_PARAMETER] = question.getDifficultyParameter();
            modules[i][question.getModuleNumber()] = 1;
            if (mstResponses.containsKey(question.getId())) {
                // Need to add offset of 1 as R is 1-indexed
                questionsAnswered.add((double) (i + 1));
            }
        }

        double[] out = new double[questionsAnswered.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = questionsAnswered.get(i);
        }

        code.addDoubleMatrix("itemBank", itemBank);
        code.addDoubleMatrix("modules", modules);
        code.addDoubleMatrix("transMatrix", TRANSITION_MATRIX);
        code.addDoubleArray("out", out);

        code.addRCode("result <- nextModule(itemBank, modules, transMatrix, model = NULL, 1, out, x = NULL, cutoff = NULL, theta = " + abilityEstimate + ", criterion = \"MFI\"," +
                "priorDist = \"norm\", priorPar = c(0, 1), D = 1, range = c(-4, 4), parInt = c(-4, 4, 33), randomesque = 1, random.seed = NULL)");
        code.addRCode("nextModuleResult <- result$module");

        caller.setRCode(code);
        caller.runAndReturnResult("nextModuleResult");
        return caller
                .getParser()
                .getAsIntArray("nextModuleResult")[0] - 1; // Convert from 1-indexed back to 0-indexed
    }

    private double[][] initialise1PLItemBank(int itemCount) {
        double[][] itemBank = new double[itemCount][NUM_OF_ITEM_PARAMETERS];
        for (int i = 0; i < itemBank.length; i++) {
            for (int j = 0; j < NUM_OF_ITEM_PARAMETERS; j++) {
                itemBank[i][DISCRIMINATION_PARAMETER] = DEFAULT_DISCRIMINATION;
                itemBank[i][PSEUDO_GUESSING_PARAMETER] = DEFAULT_PSEUDO_GUESSING;
                itemBank[i][INATTENTION_PARAMETER] = DEFAULT_INATTENTION;
            }
        }
        return itemBank;
    }
}
