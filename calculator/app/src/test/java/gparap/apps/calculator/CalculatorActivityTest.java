package gparap.apps.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gparap on 2021-01-29.
 */
public class CalculatorActivityTest {
    CalculatorActivity calculatorActivity = new CalculatorActivity();

    @Before
    public void setUp() {
        calculatorActivity = new CalculatorActivity();
    }

    @Test
    public void addTwoNumbers_positive_positive() {
        assertEquals(2, calculatorActivity.addTwoNumbers(1, 1), 0);
    }

    @Test
    public void addTwoNumbers_negative_positive() {
        assertEquals(0, calculatorActivity.addTwoNumbers(-1, 1), 0);
    }

    @Test
    public void deleteTwoNumbers_positive_positive() {
        assertEquals(0, calculatorActivity.subtractTwoNumbers(1, 1), 0);
    }

    @Test
    public void deleteTwoNumbers_negative_positive() {
        assertEquals(-2, calculatorActivity.subtractTwoNumbers(-1, 1), 0);
    }

    @Test
    public void multiplyTwoNumbers_positive_positive() {
        assertEquals(4, calculatorActivity.multiplyTwoNumbers(2, 2), 0);
    }

    @Test
    public void multiplyTwoNumbers_negative_positive() {
        assertEquals(-4, calculatorActivity.multiplyTwoNumbers(-2, 2), 0);
    }

    @Test
    public void divideTwoNumbers_positive_positive() {
        assertEquals(1, calculatorActivity.divideTwoNumbers(2, 2), 0);
    }

    @Test
    public void divideTwoNumbers_negative_positive() {
        assertEquals(-1, calculatorActivity.divideTwoNumbers(-2, 2), 0);
    }

    @Test
    public void moduloTwoNumbers_positive_positive() {
        assertEquals(1, calculatorActivity.moduloTwoNumbers(5, 2), 0);
    }
}