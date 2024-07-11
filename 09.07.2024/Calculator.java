package ru.ac.uniyar.Shebeta;

public class Calculator {

    public static int GCD(Integer first, Integer second) {
        int res = 1;
        boolean flag = true;
        int firstNum = first;

        int tmp;
        for(int secondNum = second; flag; secondNum = tmp % secondNum) {
            if (firstNum % secondNum == 0) {
                res = secondNum;
                flag = false;
                break;
            }

            tmp = firstNum;
            firstNum = secondNum;
        }

        return res;
    }

    public static String addition(Number first, Number second){
        Integer numeratorRes = first.getNum() * second.getDen()
                + second.getNum() * first.getDen();
        Integer denumeratorRes = first.getDen() * second.getDen();

        int gcd = GCD(numeratorRes, denumeratorRes);
        numeratorRes /= gcd;
        denumeratorRes /= gcd;

        String result;
        if (numeratorRes > 0 && denumeratorRes < 0){
            numeratorRes *= -1;
            denumeratorRes *= -1;
        }
        if (numeratorRes >= denumeratorRes && (numeratorRes % denumeratorRes == 0)){
            Integer tmp = numeratorRes / denumeratorRes;
            result = String.valueOf(tmp);
        }
        else{
            result = String.valueOf(numeratorRes) + "/" + String.valueOf(denumeratorRes);
        }

        return result;
    }

    public static String subtraction(Number first, Number second){
        Integer numeratorRes = first.getNum() * second.getDen()
                - second.getNum() * first.getDen();
        Integer denumeratorRes = first.getDen() * second.getDen();

        int gcd = GCD(numeratorRes, denumeratorRes);
        numeratorRes /= gcd;
        denumeratorRes /= gcd;

        String result;
        if (numeratorRes > 0 && denumeratorRes < 0){
            numeratorRes *= -1;
            denumeratorRes *= -1;
        }
        if (numeratorRes % denumeratorRes == 0){
            Integer tmp = numeratorRes / denumeratorRes;
            result = String.valueOf(tmp);
        }
        else{
            result = String.valueOf(numeratorRes) + "/" + String.valueOf(denumeratorRes);
        }

        return result;
    }

    public static String multiplication(Number first, Number second){
        Integer numeratorRes = first.getNum() * second.getNum();
        Integer denumeratorRes = first.getDen() * second.getDen();

        int gcd = GCD(numeratorRes, denumeratorRes);
        numeratorRes /= gcd;
        denumeratorRes /= gcd;

        String result;
        if (numeratorRes >= denumeratorRes && (numeratorRes % denumeratorRes == 0)){
            Integer tmp = numeratorRes / denumeratorRes;
            result = String.valueOf(tmp);
        }
        else{
            result = String.valueOf(numeratorRes) + "/" + String.valueOf(denumeratorRes);
        }

        return result;
    }

    public static String division(Number first, Number second){
        Integer numeratorRes = first.getNum() * second.getDen();
        Integer denumeratorRes = first.getDen() * second.getNum();

        if (denumeratorRes == 0){
            return "div_by_zero";
        }
        int gcd = GCD(numeratorRes, denumeratorRes);
        numeratorRes /= gcd;
        denumeratorRes /= gcd;

        String result;
        if (numeratorRes >= denumeratorRes && (numeratorRes % denumeratorRes == 0)){
            Integer tmp = numeratorRes / denumeratorRes;
            result = String.valueOf(tmp);
        }
        else{
            result = String.valueOf(numeratorRes) + "/" + String.valueOf(denumeratorRes);
        }

        return result;
    }

}
