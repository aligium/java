package com.calc;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Сканирум пользовательский ввод
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter an expression: ");
        String inputString = userInput.nextLine();
        //Вызываем калькулятор
        System.out.println(calc(inputString));
    }
    //Переводим Римские числа в арабские
    static int toArabic(String number) {
        return switch (number) {
            case ("I") -> 1;
            case ("II") -> 2;
            case ("III") -> 3;
            case ("IV") -> 4;
            case ("V") -> 5;
            case ("VI") -> 6;
            case ("VII") -> 7;
            case ("VIII") -> 8;
            case ("IX") -> 9;
            case ("X") -> 10;
            default -> -10;
        };
    }
    //Переводим арабские числа в римские
    static String toRoman (int number) {
        String romanNumber = "";

        if (number == 100) {
            romanNumber = "C";
            number = 0;
        }
        else if (number>=90) {
            romanNumber = romanNumber + "XC";
            number = number - 90;
        }
        else if (number>=80) {
            romanNumber = romanNumber + "LXXX";
            number = number - 80;
        }
        else if (number>=70) {
            romanNumber = romanNumber + "LXX";
            number = number - 70;
        }
        else if (number>=60) {
            romanNumber = romanNumber + "LX";
            number = number - 60;
        }
        else if (number>=50) {
            romanNumber = romanNumber + "L";
            number = number - 50;
        }
        else if (number>=40) {
            romanNumber = romanNumber + "XL";
            number = number - 40;
        }
        else if (number>=30) {
            romanNumber = romanNumber + "XXX";
            number = number - 30;
        }
        else if (number>=20) {
            romanNumber = romanNumber + "XX";
            number = number - 20;
        }
        else if (number>=10) {
            romanNumber = romanNumber + "X";
            number = number - 10;
        }
        else romanNumber = "";
        return switch (number) {
            case 1 -> romanNumber + "I";
            case 2 -> romanNumber + "II";
            case 3 -> romanNumber + "III";
            case 4 -> romanNumber + "IV";
            case 5 -> romanNumber + "V";
            case 6 -> romanNumber + "VI";
            case 7 -> romanNumber + "VII";
            case 8 -> romanNumber + "VIII";
            case 9 -> romanNumber + "IX";
            default -> romanNumber + "";
        };
    }
    //Складываем, отнимаем, умножаем, делим
    static int operation(int arg1, int arg2, String operator) {
        return switch (operator.strip()) {
            case "+" -> arg1 + arg2;
            case "-" -> arg1 - arg2;
            case "*" -> arg1 * arg2;
            case "/" -> arg1 / arg2;
            default -> -1;
        };
    }
    //Проверяем если оба аргумента одной системы счиления
    static String checkNumbers(String number) {
        try {
            if (toArabic(number) >= 1)
                return "Roman";
            else if (Integer.parseInt(number) >= 1)
                return "Arabic";
            else return null;
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return null;
    }
    public static String calc(String input) {
        //Разбиваем ввод на части
        String[] userExpression = input.split(" ");
        //System.out.println("User expression is: " + Arrays.toString(userExpression));
        String expressionResult = "";
        //Проверям на наличие всех аргументов
        try {
            if (userExpression.length < 3) {
                throw new Exception ("строка не является математической операцией");
            }
            //Присваем имена аргументам и знаку
            String numberOne = userExpression[0];
            String sign = userExpression[1];
            String numberTwo = userExpression[2];
            //System.out.println(numberOne + sign + numberTwo);
            if (userExpression.length > 3) {
                throw new Exception ("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (!Objects.equals(checkNumbers(numberOne), checkNumbers(numberTwo)))
                throw new Exception("используются одновременно разные системы счисления");
            if (numberOne.matches("\\d") && numberTwo.matches("\\d")) {
                int argument1 = (Integer.parseInt(numberOne));
                int argument2 = (Integer.parseInt(numberTwo));
                if ((1 <= argument1 && argument1 <= 10) && (1 <= argument2 && argument2 <= 10)) {
                    expressionResult = String.valueOf(operation(argument1, argument2, sign));
                }
            }
            else if (toArabic(numberOne) > 0 && toArabic(numberTwo) > 0) {
                int argument1 = (toArabic(numberOne));
                int argument2 = (toArabic(numberTwo));
                if ((1 <= argument1 && argument1 <= 10) && (1 <= argument2 && argument2 <= 10)) {
                    if (operation(argument1, argument2, sign) <= 0) {
                        throw new Exception("в римской системе нет нуля и отрицательных чисел");
                    }
                    else expressionResult = toRoman(operation(argument1, argument2, sign));
                }
            }
        }
        catch(Exception E) {
            System.out.println(E.getMessage());
        }
        return expressionResult;
    }
}