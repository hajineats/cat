package com.se701.cat.controller;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class SomeController {

    @GetMapping("/hello")
    @ResponseBody
    public Map<String, Object> hello(){

        RCaller caller = RCaller.create();
        RCode code = RCode.create();
        code.addRCode("library('mstR')");
        code.addRCode("result <- genDichoMatrix(items = 100, model = \"4PL\", aPrior = c(\"norm\", 1, 0.2),\n" +
                "bPrior = c(\"norm\", 0, 1), cPrior = c(\"unif\", 0, 0.25),\n" +
                "dPrior = c(\"unif\", 0.75, 1), seed = 1)");
        caller.setRCode(code);
        caller.runAndReturnResult("result");
        double[] a = caller.getParser().getAsDoubleArray("a");

        Map<String, Object> map = new HashMap<>();
        map.put("data", a);
        return map;
    }
}