/*
 * Copyright (c) 2022 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.calculator_area

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorUnitTest {
    @Test
    fun calculateSquare() {
        val expected = "25.0"
        val actual = CalculatorOperations.calculateSquare(5.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateRectangle() {
        val expected = "20.0"
        val actual = CalculatorOperations.calculateRectangle(4.0, 5.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateParallelogram() {
        val expected = "20.0"
        val actual = CalculatorOperations.calculateParallelogram(4.0, 5.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateEquilateralTriangle() {
        val expected = "10.825317353010178"
        val actual = CalculatorOperations.calculateEquilateralTriangle(5.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateTriangle() {
        val expected = "10.0"
        val actual = CalculatorOperations.calculateTriangle(5.0, 4.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateTrapezoid() {
        val expected = "25.0"
        val actual = CalculatorOperations.calculateTrapezoid(6.0, 4.0, 5.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateHexagon() {
        val expected = "64.95190262794495"
        val actual = CalculatorOperations.calculateHexagon(5.0)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateCircle() {
        val expected = "50.26548245743669"
        val actual = CalculatorOperations.calculateCircle(4.0)
        assertEquals(expected, actual)
    }

    @Test
    fun beautifyResult() {
        val initialResult = "64.95190262794495"
        val expectedResult = "64.95"
        val actualResult: String = Utils.beautifyResult(initialResult)
        assertEquals(expectedResult, actualResult)
    }
}