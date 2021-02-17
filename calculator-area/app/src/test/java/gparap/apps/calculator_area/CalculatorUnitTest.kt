package gparap.apps.calculator_area

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by gparap on 2021-02-12.
 */
class CalculatorUnitTest {
    @Test
    fun calculateSquare(){
        val expected = 25
        val actual = CalculatorOperations.calculateSquare(5)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateRectangle() {
        val expected = 20
        val actual = CalculatorOperations.calculateRectangle(4, 5)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateParallelogram() {
        val expected = 20
        val actual = CalculatorOperations.calculateParallelogram(4, 5)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateEquilateralTriangle() {
        val expected = 10.825317547305483f
        val actual = CalculatorOperations.calculateEquilateralTriangle(5)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateTriangle() {
        val expected = 10f
        val actual = CalculatorOperations.calculateTriangle(5, 4)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateTrapezoid() {
        val expected = 25.0f
        val actual = CalculatorOperations.calculateTrapezoid(6, 4, 5)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateHexagon() {
        val expected = 64.9519052838329f
        val actual = CalculatorOperations.calculateHexagon(5)
        assertEquals(expected, actual)
    }
    @Test
    fun calculateCircle() {
        val expected = 6.283185307179586
        val actual = CalculatorOperations.calculateCircle(4)
        assertEquals(expected, actual, 0.0)
    }
}