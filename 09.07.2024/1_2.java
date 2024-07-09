package ru.ac.uniyar.Shebeta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        System.out.print("Введите пример: ");

        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        String[] parts = expression.split(" ");

        String first = parts[0];
        String second = parts[2];
        String operation = parts[1];

        String[] firstParts = first.split("/");
        String[] secondParts = second.split("/");

        int firstNum = Integer.parseInt(firstParts[0]);
        int secondNum = Integer.parseInt(secondParts[0]);

        int firstDen;
        int secondDen;

        if (firstParts.length == 1){
            firstDen = 1;
        }
        else{
            firstDen = Integer.parseInt(firstParts[1]);
        }

        if (secondParts.length == 1){
            secondDen = 1;
        }
        else{
            secondDen = Integer.parseInt(secondParts[1]);
        }

        switch (operation) {
            case "+":
                if (firstDen == secondDen){
                    System.out.println("Результат: " + (firstNum + secondNum) + "/" + firstDen);
                }
                else{
                    System.out.println("Результат: " + (firstNum * secondDen
                            + secondNum * firstDen) + "/" + firstDen * secondDen);
                }
                break;
            case "-":
                if (firstDen == secondDen){
                    System.out.println("Результат: " + (firstNum - secondNum) + "/" + firstDen);
                }
                else{
                    System.out.println("Результат: " + (firstNum * secondDen
                            - secondNum * firstDen) + "/" + firstDen * secondDen);
                }
                break;
            case "*":
                System.out.println("Результат: " + (firstNum * secondNum) + "/" + (firstDen * secondDen) );
                break;
            case "/":
                if (secondNum == 0){
                    System.out.println("Деление на 0.");
                }
                else{
                    System.out.println("Результат: " + (firstNum * secondDen) + "/" + (firstDen * secondNum) );
                }
                break;
        }
    }
}
