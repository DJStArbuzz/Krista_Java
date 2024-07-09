package ru.ac.uniyar.Shebeta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        System.out.print("Введите пример: ");

        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        String[] parts = expression.split(" ");

        int first = Integer.parseInt(parts[0]);
        int second = Integer.parseInt(parts[2]);
        String operation = parts[1];

        switch (operation) {
            case "+":
                System.out.println("Результат: " + (first + second));
                break;
            case "-":
                System.out.println("Результат: " + (first - second));
                break;
            case "*":
                System.out.println("Результат: " + (first * second));
                break;
            case "/":
                if (second == 0){
                    System.out.println("Деление на 0.");
                }
                else{
                    System.out.println("Результат: " + (first / second));
                }
                break;
        }
    }
}
