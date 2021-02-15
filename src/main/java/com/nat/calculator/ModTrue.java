package com.nat.calculator;

import java.util.regex.Pattern;

public class ModTrue {

    public static void main(String a[]) {
        String expression = "10*-10";
        System.out.println(new ModTrue().checkString(expression));
    }

    public String checkString(String str) {

        System.out.println("Lenght str = " + str.length());

        int countB = 0;

        if (str.length() > 65536) {
            return "Error 04 - Слишком длинное выражение. Максимальная длина - 65536 символе";
        }

        for(int i = 0; i < str.length(); i++) {
            Character curSym = str.charAt(i);
            Character nextSym;

            if(i+1 < str.length()) {
                nextSym = str.charAt(i+1);
            } else {
                nextSym = '!';
            }

            if(curSym == '/' && nextSym == '0')   {
                return "Error 06 - ошибка деления на 0";
            }

            if(
                    (!Character.isDigit(curSym) && curSym != '+' && curSym != '-' && curSym != '*' && curSym != '/' && curSym != '(' && curSym != ')') ||
                    (Character.isDigit(curSym) && nextSym == '(')
            )   {
                return "Error 01 - Неверная синтаксическая конструкция входного выражения";
            }

            if(
                    (curSym == '+' || curSym == '-' || curSym == '*' || curSym == '/') &&
                            (!Character.isDigit(nextSym) && (nextSym == '+' || nextSym == '-' || nextSym == '*' || nextSym == '/' || nextSym == '!'))
            )   {
                return "Error 01 at <"+ (i + 1) + "> - Два подряд оператора на <"+ (i + 1) + "> символе";
            }

            // Для случаев ")(" или "(+" или "(-" или "(*" или "(/"
            if ((curSym == ')' && nextSym == '(') || (curSym == ')' && (nextSym == '+' || nextSym == '-' || nextSym == '*' || nextSym == '/')))  {
                return "Error 01 at <"+ (i + 1) + "> - Два подряд оператора на <"+ (i + 1) + "> символе";
            }

            if((curSym == '+' || curSym == '-' || curSym == '*' || curSym == '/' || curSym == '(') && nextSym == '!')   {
                return "Error 02 - Незаконченное выражение";
            }

            // считаем количество открытых и закрытых скобок
            if(curSym == '(') {
                countB++;
            } else if(curSym ==')') {
                countB--;
            }


        }

        if(countB != 0) {
            return "Error - Неверно расставлены скобки";
        }
        return "success";
    }

}
