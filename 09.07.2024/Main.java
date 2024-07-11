package ru.ac.uniyar.Shebeta;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        String[] parts;
        if (args.length == 0){
            System.out.print("Введите пример: ");

            Scanner in = new Scanner(System.in);
            String expression = in.nextLine();

            parts = expression.split(" ");
        }
        else{
            parts = args;
        }

        String firstPart = parts[0];
        String secondPart = parts[2];
        String operation = parts[1];

        Number first = new Number(firstPart);
        Number second = new Number(secondPart);

        if (first.getDen() == 0 || second.getDen() == 0){
            System.out.println("Работа с невозможными числами.");
            return;
        }

        System.out.print("Результат: ");
        String res;
        switch (operation) {
            case "+":
                res = Calculator.addition(first, second);
                System.out.println(res);
                break;
            case "-":
                res = Calculator.subtraction(first, second);
                System.out.println(res);
                break;
            case "*":
                res = Calculator.multiplication(first, second);
                System.out.println(res);
                break;
            case "/":
                res = Calculator.division(first, second);
                System.out.println(res);
                break;
        }
    }
}