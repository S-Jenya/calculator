package com.nat.calculator;

import java.text.DecimalFormat;

public class SquerCalc {
    public String calcSq(String paramA, String paramB, String paramC) {
        int pA = Integer.parseInt(paramA);
        int pB = Integer.parseInt(paramB);
        int pC = Integer.parseInt(paramC);
        String answer = "";

        Double d = Math.sqrt(pB * pB - (4 * pA * pC));
        if (d > 0) {
            Double x1 = ((-1) * pB + d) / (2 * pA);
            Double x2 = ((-1) * pB - d) / (2 * pA);
            DecimalFormat df = new DecimalFormat("###.##");
            answer = "x1 =  " + df.format(x1) + "; x2 = " + df.format(x2);
        }
        if (d == 0) {
            int x = (-1) * pB / (2 * pA);
            answer = "x =  " + x;
        }
        if (d < 0) {
            answer = "NO ANSWER";
        }


        return answer;
    }
}
