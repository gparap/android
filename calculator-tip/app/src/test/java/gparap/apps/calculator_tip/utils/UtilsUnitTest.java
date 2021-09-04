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
package gparap.apps.calculator_tip.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

class UtilsUnitTest {

    @org.junit.jupiter.api.Test
    @DisplayName("should compute tip amount")
    void getTip() {
        String bill = "70", tipPercentage = "15";

        float expectedTip = 10.5f;
        float actualTip = Utils.getInstance().getTip(bill, tipPercentage);

        Assertions.assertEquals(expectedTip, actualTip);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should computes total bill amount including tips")
    void getBill() {
        String bill = "70";
        float amountTip = 10.5f;

        float expectedBill = 80.5f;
        float actualBill = Utils.getInstance().getBill(bill, amountTip);

        Assertions.assertEquals(expectedBill, actualBill);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should computes how the bill will be split among people")
    void getSplit() {
        float amountTotal = 90;
        String personsToSplit = "2";

        float expectedAmount = 45;
        float actualAmount = Utils.getInstance().getSplit(amountTotal, personsToSplit);

        Assertions.assertEquals(expectedAmount, actualAmount);
    }
}