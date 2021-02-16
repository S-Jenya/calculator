package com.nat.calculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class ModTrue {

    public static void main(String a[]) {
        String expression = "10*-10";
        System.out.println(new ModTrue().checkString(expression));
    }

    public String checkString(String str) {

        System.out.println("Lenght str = " + str.length());

        int countB = 0;
        String filePath = ".//logFile.txt";
        String text;
        File file = new File(filePath);

        try {
            if (file.createNewFile()) {
                System.out.println("\nFile is created!");
            } else {
                System.out.println("\nFile already exists.");
            }

            if (str.length() > 65536) {
                text = "\nError 04 - Слишком длинное выражение. Максимальная длина - 65536 символе";
                Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                return text;
            }

            for (int i = 0; i < str.length(); i++) {
                Character curSym = str.charAt(i);
                Character nextSym;

                if (i + 1 < str.length()) {
                    nextSym = str.charAt(i + 1);
                } else {
                    nextSym = '!';
                }

                if (curSym == '/' && nextSym == '0') {
                    text =  "\nError 06 - ошибка деления на 0";
                    Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                    return text;
                }

                if (i == 0 && (curSym == '+' || curSym == '-' || curSym == '*' || curSym == '/' || curSym == '%')) {
                    text = "\nError 01 - Неверная синтаксическая конструкция входного выражения";
                    Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                    return text;
                }

                if (
                        (!Character.isDigit(curSym) && curSym != '+' && curSym != '-' && curSym != '*' && curSym != '/' && curSym != '(' && curSym != ')' && curSym != '%') ||
                                (Character.isDigit(curSym) && nextSym == '(')
                ) {
                    text = "\nError 01 - Неверная синтаксическая конструкция входного выражения";
                    Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                    return text;
                }

                if ((curSym == '+' || curSym == '-' || curSym == '*' || curSym == '/' || curSym == '(' || curSym == '%') && nextSym == '!') {
                    text = "\nError 02 - Незаконченное выражение";
                    Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                    return text;
                }

                if (
                        (curSym == '+' || curSym == '-' || curSym == '*' || curSym == '/' || curSym == '%') &&
                                (!Character.isDigit(nextSym) && (nextSym == '+' || nextSym == '-' || nextSym == '*' || nextSym == '/' || nextSym == '!'))
                ) {
                    text = "\nError 01 at <" + (i + 1) + "> - Два подряд оператора на <" + (i + 1) + "> символе";
                    Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                    return text;
                }

                // Для случаев ")(" или "(+" или "(-" или "(*" или "(/"
                if ((curSym == ')' && nextSym == '(') || (curSym == ')' && (nextSym == '+' || nextSym == '-' || nextSym == '*' || nextSym == '/'  || nextSym == '%'))) {
                    text = "\nError 01 at <" + (i + 1) + "> - Два подряд оператора на <" + (i + 1) + "> символе";
                    Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                    return text;
                }

                // считаем количество открытых и закрытых скобок
                if (curSym == '(') {
                    countB++;
                } else if (curSym == ')') {
                    countB--;
                }


            }

            if (countB != 0) {
                text = "\nError - Неверно расставлены скобки";
                Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                return text;
            }

        }catch(IOException e){
            System.out.println(e);
        }
        return "success";
    }

}
