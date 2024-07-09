package ru.ac.uniyar.Shebeta;

public class Number {
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
