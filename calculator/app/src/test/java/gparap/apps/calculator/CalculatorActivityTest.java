package gparap.apps.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gparap on 2021-01-29.
 */
public class CalculatorActivityTest {
    @Test
    public void addTwoNumbers_positive_positive() {
        assertEquals(2, CalculatorOperationsKt.addTwoNumbers(1, 1), 0);
    }

    @Test
    public void addTwoNumbers_negative_positive() {
        assertEquals(0, CalculatorOperationsKt.addTwoNumbers(-1, 1), 0);
    }

    @Test
    public void deleteTwoNumbers_positive_positive() {
        assertEquals(0, CalculatorOperationsKt.subtractTwoNumbers(1, 1), 0);
    }

    @Test
    public void deleteTwoNumbers_negative_positive() {
        assertEquals(-2, CalculatorOperationsKt.subtractTwoNumbers(-1, 1), 0);
    }

    @Test
    public void multiplyTwoNumbers_positive_positive() {
        assertEquals(4, CalculatorOperationsKt.multiplyTwoNumbers(2, 2), 0);
    }

    @Test
    public void multiplyTwoNumbers_negative_positive() {
        assertEquals(-4, CalculatorOperationsKt.multiplyTwoNumbers(-2, 2), 0);
    }

    @Test
    public void divideTwoNumbers_positive_positive() {
        assertEquals(1, CalculatorOperationsKt.divideTwoNumbers(2, 2), 0);
    }

    @Test
    public void divideTwoNumbers_negative_positive() {
        assertEquals(-1, CalculatorOperationsKt.divideTwoNumbers(-2, 2), 0);
    }

    @Test
    public void moduloTwoNumbers_positive_positive() {
        assertEquals(1, CalculatorOperationsKt.moduloTwoNumbers(5, 2), 0);
    }

    @Test
    public void powerTwoNumbers_positive_positive() {
        assertEquals(2, CalculatorOperationsKt.powerTwoNumbers(2, 1), 0);
        assertEquals(8, CalculatorOperationsKt.powerTwoNumbers(2, 3), 0);
    }

    @Test
    public void powerTwoNumbers_negative_positive() {
        assertEquals( 4, CalculatorOperationsKt.powerTwoNumbers(-2, 2), 0);
        assertEquals(-8, CalculatorOperationsKt.powerTwoNumbers(-2, 3), 0);
    }

    @Test
    public void powerTwoNumbers_zero_positive() {
        assertEquals(0, CalculatorOperationsKt.powerTwoNumbers(0, 3), 0);
    }

    @Test
    public void powerTwoNumbers_any_zero() {
        assertEquals(1, CalculatorOperationsKt.powerTwoNumbers( 2, 0), 0);
        assertEquals(1, CalculatorOperationsKt.powerTwoNumbers(-1, 0), 0);
        assertEquals(1, CalculatorOperationsKt.powerTwoNumbers( 0, 0), 0);
    }
}