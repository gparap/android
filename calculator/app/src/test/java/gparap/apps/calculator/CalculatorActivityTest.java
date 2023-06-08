/*
 * Copyright 2023 gparap
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
package gparap.apps.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(4, CalculatorOperationsKt.powerTwoNumbers(-2, 2), 0);
        assertEquals(-8, CalculatorOperationsKt.powerTwoNumbers(-2, 3), 0);
    }

    @Test
    public void powerTwoNumbers_zero_positive() {
        assertEquals(0, CalculatorOperationsKt.powerTwoNumbers(0, 3), 0);
    }

    @Test
    public void powerTwoNumbers_any_zero() {
        assertEquals(1, CalculatorOperationsKt.powerTwoNumbers(2, 0), 0);
        assertEquals(1, CalculatorOperationsKt.powerTwoNumbers(-1, 0), 0);
        assertEquals(1, CalculatorOperationsKt.powerTwoNumbers(0, 0), 0);
    }
}