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

public class Utils {
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    private Utils() {
    }

    /**
     * Computes tip amount based on initial bill and tip percentage.
     *
     * @param bill          initial bill
     * @param tipPercentage tip percentage
     * @return tip amount
     */
    public float getTip(String bill, String tipPercentage) {
        return Float.parseFloat(bill) * Integer.parseInt(tipPercentage) / 100;
    }

    /**
     * Computes total bill amount including tips.
     *
     * @param bill      initial bill
     * @param amountTip total tips
     * @return total bill
     */
    public float getBill(String bill, float amountTip) {
        return Float.parseFloat(bill) + amountTip;
    }

    /**
     * Computes how the bill will be split among people.
     *
     * @param amountTotal    initial bill plus tips
     * @param personsToSplit how many persons the total bill will split to
     * @return amount the total bill will split to
     */
    public float getSplit(float amountTotal, String personsToSplit) {
        return amountTotal / Integer.parseInt(personsToSplit);
    }
}
