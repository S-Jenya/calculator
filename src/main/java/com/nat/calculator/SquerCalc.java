package com.nat.calculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SquerCalc {
    public String calcSq(String paramA, String paramB, String paramC) {
        String answer = "";
        int forD;
        try {
            int pA = Integer.parseInt(paramA);
            int pB = Integer.parseInt(paramB);
            int pC = Integer.parseInt(paramC);
            forD = pB * pB - (4 * pA * pC);
            if (forD > 0) {
                Double d = Math.sqrt(forD);
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
            } else if (forD < 0) {
                answer = "Дискриминант (" + forD + ") меньше нуля.";
            }
        } catch (Exception ex) {
            answer = "Ошибка решения квадратного уравнения\nПодробнее: " + ex.getMessage();
            System.out.println(answer);
            return answer;
        }

        // Запись в log файл
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String filePath = ".//logFile.txt";
        String text;
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
            text = "\n" + formatForDateNow.format(dateNow) + " Решение квадратного уравнения. A = " + paramA + " B = " + paramB + " C = " + paramC + ". Дискриминант = "
                    + forD + " Корни уравнения: " + answer;
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
            System.out.println(text);
        } catch (IOException e) {
            System.out.println(e);
        }

        return answer;
    }
}
