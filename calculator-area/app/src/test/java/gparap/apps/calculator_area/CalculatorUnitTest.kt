/*
 * Copyright 2021 gparap
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
        val expected = 50.26548245743669
        val actual = CalculatorOperations.calculateCircle(4)
        assertEquals(expected, actual, 0.0)
    }
}