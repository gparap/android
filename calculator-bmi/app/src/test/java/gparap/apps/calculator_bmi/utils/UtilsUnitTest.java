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
package gparap.apps.calculator_bmi.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import gparap.apps.calculator_bmi.R;

class UtilsUnitTest {

    @org.junit.jupiter.api.Test
    @DisplayName("should calculate body max index")
    void calculateBMI() {
        float weight = 90, height = 1.9f;
        float expectedBMI = 24.930748f;
        float actualBMI = Utils.getInstance().calculateBMI(weight, height);

        Assertions.assertEquals(expectedBMI, actualBMI);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should get a formatted decimal number")
    void getDecimalFormat() {
        float number = 24.930748f;
        String expectedNumber = "24.9";
        String actualNumber = Utils.getInstance().getDecimalFormat(number);

        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should get the body max index category")
    void getBMICategory() {
        float bmi = 24.930748f;
        int expectedCategory = R.string.categoryNormal;
        int actualCategory = Utils.getInstance().getBMICategory(bmi);

        Assertions.assertEquals(expectedCategory, actualCategory);
    }
}