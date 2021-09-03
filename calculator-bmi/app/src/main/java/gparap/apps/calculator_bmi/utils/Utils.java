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

import java.text.DecimalFormat;

import gparap.apps.calculator_bmi.R;

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
     * Calculates the Body Mass Index.
     *
     * @param weight person's weight
     * @param height person's height
     * @return bmi
     */
    public float calculateBMI(float weight, float height) {
        return weight / (height * height);
    }

    /**
     * Formats a decimal number based on a pattern that removes all decimals, except the first.
     *
     * @param number number to format
     * @return number
     */
    public String getDecimalFormat(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(number);
    }

    /**
     * Finds the Body Mass Index category of a person.
     *
     * @param bmi person's bmi
     * @return category's resource id
     */
    public int getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return R.string.categoryUnderweight;
        } else if (bmi > 18.5 && bmi < 25) {
            return R.string.categoryNormal;
        } else if (bmi > 25 && bmi < 30) {
            return R.string.categoryOverweight;
        } else {
            return R.string.categoryObese;
        }
    }
}
