package ru.ac.uniyar.Shebeta;

import java.util.Scanner;

public class Main {
    public static class Number{
        private int numerator;
        private int denumerator;

        public Number(String numberStr){
            String[] parts = numberStr.split("/");

            try {
                this.numerator = Integer.parseInt(parts[0]);

                if (parts.length == 1){
                    this.denumerator = 1;
                }
                else{
                    this.denumerator = Integer.parseInt(parts[1]);
                }
            } catch (NumberFormatException e) {
                return;
            }
        }

        public int getNum() { return numerator; }

        public int getDen() { return denumerator; }
    }

    public static void addition(Number first, Number second){
        if (first.getDen()  == second.getDen()){
            System.out.println("Результат: " + (first.getNum() + second.getNum()) + "/" + first.getDen() );
        }
        else{
            System.out.println("Результат: " + (first.getNum() * second.getDen()
                    + second.getNum() * first.getDen()) + "/" + first.getDen() * second.getDen());
        }
    }

    public static void subtraction(Number first, Number second){
        if (first.getDen() == second.getDen()){
            System.out.println("Результат: " + (first.getNum() - second.getNum()) + "/" + first.getDen());
        }
        else{
            System.out.println("Результат: " + (first.getNum() * second.getDen()
                    - second.getNum() * first.getDen()) + "/" + first.getDen() * second.getDen());
        }
    }

    public static void multiplication(Number first, Number second){
        System.out.println("Результат: " + (first.getNum() * second.getNum()) + "/" + (first.getDen() * second.getDen()) );
    }

    public static void division(Number first, Number second){
        if (second.getNum() == 0){
            System.out.println("Деление на 0.");
        }
        else{
            System.out.println("Результат: " + (first.getNum() * second.getDen()) + "/" + (first.getDen() * second.getNum()) );
        }
    }

    public static void main(String[] args)
    {
        System.out.print("Введите пример: ");

        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        String[] parts = expression.split(" ");
        String firstPart = parts[0];
        String secondPart = parts[2];
        String operation = parts[1];

        Number first = new Number(firstPart);
        Number second = new Number(secondPart);

        if (first.getDen() == 0 || second.getDen() == 0){
            System.out.println("Работа с невозможными числами.");
            return;
        }

        switch (operation) {
            case "+":
                addition(first, second);
                break;
            case "-":
                subtraction(first, second);
                break;
            case "*":
                multiplication(first, second);
                break;
            case "/":
                division(first, second);
                break;
        }
    }
}
