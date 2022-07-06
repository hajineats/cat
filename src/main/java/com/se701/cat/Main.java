package com.se701.cat;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

    }


    public void callNextModule(){
        /**
         * nextModule:
         * 2D complete item bank - 1 column for each parameter (4 columns), 1 row for each item
         * modules binary matrix where modules[i][j]=1 means item i (row i in item bank) in the item bank belongs to module j
         * out vector, stores list of integers n where n is the item (row in item bank) that has already been answered
         */

        RCaller caller = RCaller.create();
        RCode code = RCode.create();

        code.addRCode("library(\"mstR\")");
        double[][] itemBank = new double[30][4];
        for(int i = 0; i<30; i++){
            for(int j = 0; j<4; j++){
                if(j==0||j==1||j==3){
                    itemBank[i][j] = 1;
                }
            }
            if(i>=10 && i<20){
                itemBank[i][1] = 0.2;
            }
        }

        double[][] modules = new double[30][3];
        for(int i = 0; i<10; i++){
            modules[i][0] = 1;
        }
        for(int i = 10; i<20; i++){
            modules[i][1] = 1;
        }
        for(int i = 20; i<30; i++){
            modules[i][2] = 1;
        }

        double[][] transMatrix = new double[][]{
                {0,1,1},
                {0,0,0},
                {0,0,0}
        };

        double[] out = {1,2,3,4,5,6,7,8,9,10};
//        double currentModule = 1;

        code.addDoubleMatrix("itemBank", itemBank);
        code.addDoubleMatrix("modules", modules);
        code.addDoubleMatrix("transMatrix", transMatrix);
        code.addDoubleArray("out", out);
        code.addRCode("result <- nextModule(itemBank, modules, transMatrix, model = NULL, 1, out, x = NULL, cutoff = NULL, theta = -1, criterion = \"MFI\"," +
                "priorDist = \"norm\", priorPar = c(0, 1), D = 1, range = c(-4, 4), parInt = c(-4, 4, 33), randomesque = 1, random.seed = NULL)");
        code.addRCode("nextModuleResult <- result$module");
        caller.setRCode(code);
        caller.runAndReturnResult("nextModuleResult");
        int[] results = caller.getParser().getAsIntArray("nextModuleResult");
        System.out.println("result = " + results[0]);
    }

    public void callEst(){
        /**
         * eapEST:
         * response vector,
         *  1. 2D subset item bank - 1 column for each parameter (4 columns), 1 row for each item
         *  2. response vector where response[i]=1 means item i (row i in item bank) in the item bank is correctly answered
         */
        double[][] itemBank = new double[10][4];
        for(int i = 0; i<10; i++){
            for(int j = 0; j<4; j++){
                if(j==0||j==1||j==3){
                    itemBank[i][j] = 1;
                }
            }
        }

        double[] responses = new double[10];
        for(int i = 0; i<10; i++){
            responses[i] = 1;
        }
//        2.2923709125288
        RCaller caller = RCaller.create();
        RCode code = RCode.create();

        code.addRCode("library(\"mstR\")");
        code.addDoubleMatrix("itemBank", itemBank);
        code.addDoubleArray("responses", responses);

        code.addRCode("result<-eapEst(itemBank, responses, model = NULL, D = 1, priorDist = \"norm\", priorPar = c(0, 1), lower = -4, upper = 4, nqp = 33)");

        caller.setRCode(code);
        caller.runAndReturnResult("result");
        double[] results = caller.getParser().getAsDoubleArray("result");
        System.out.println("result = " + results[0]);
    }
}
