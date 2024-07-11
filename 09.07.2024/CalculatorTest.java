package org.example;

import org.junit.jupiter.api.Test;
import ru.ac.uniyar.Shebeta.Number;
import ru.ac.uniyar.Shebeta.Calculator;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    void testAdditionIntWithInt(){
        Number first = new Number("1");
        Number second = new Number("2");
        assertEquals("3", Calculator.addition(first, second), "Сложение неправильно определено.");
    }

    @Test
    void testAdditionRatWithRat(){
        Number first = new Number("1/2");
        Number second = new Number("3/5");
        assertEquals("11/10", Calculator.addition(first, second), "Сложение неправильно определено.");
    }

    @Test
    void testAdditionRatWithInt(){
        Number first = new Number("1/2");
        Number second = new Number("5");
        assertEquals("11/2", Calculator.addition(first, second), "Сложение неправильно определено.");
    }

    @Test
    void testSubtractionIntWithInt(){
        Number first = new Number("1");
        Number second = new Number("2");
        assertEquals("-1", Calculator.subtraction(first, second), "Вычитание неправильно определено.");
    }

    @Test
    void testSubtractionRatWithRat(){
        Number first = new Number("1/2");
        Number second = new Number("3/5");
        assertEquals("-1/10", Calculator.subtraction(first, second), "Вычитание неправильно определено.");
    }

    @Test
    void testSubtractionRatWithInt(){
        Number first = new Number("1/2");
        Number second = new Number("5");
        assertEquals("-9/2", Calculator.subtraction(first, second), "Вычитание неправильно определено.");
    }

    @Test
    void testMultiplicationIntWithInt(){
        Number first = new Number("1");
        Number second = new Number("2");
        assertEquals("2", Calculator.multiplication(first, second), "Умножение неправильно определено.");
    }

    @Test
    void testMultiplicationRatWithRat(){
        Number first = new Number("1/2");
        Number second = new Number("3/5");
        assertEquals("3/10", Calculator.multiplication(first, second), "Умножение неправильно определено.");
    }

    @Test
    void testMultiplicationRatWithInt(){
        Number first = new Number("1/2");
        Number second = new Number("5");
        assertEquals("5/2", Calculator.multiplication(first, second), "Умножение неправильно определено.");
    }

    @Test
    void testDivisionIntWithInt(){
        Number first = new Number("1");
        Number second = new Number("2");
        assertEquals("1/2", Calculator.division(first, second), "Деление неправильно определено.");
    }

    @Test
    void testDivisionRatWithRat(){
        Number first = new Number("1/2");
        Number second = new Number("3/5");
        assertEquals("5/6", Calculator.division(first, second), "Деление неправильно определено.");
    }

    @Test
    void testDivisionRatWithInt(){
        Number first = new Number("1/2");
        Number second = new Number("5");
        assertEquals("1/10", Calculator.division(first, second), "Деление неправильно определено.");
    }

    @Test
    void testDivisionByZero(){
        Number first = new Number("1");
        Number second = new Number("0");
        assertEquals("div_by_zero", Calculator.division(first, second), "Деление на 0 неправильно обрабатывается");
    }
}
