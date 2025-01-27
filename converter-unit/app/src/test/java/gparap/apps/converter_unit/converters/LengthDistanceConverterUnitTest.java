/*
 * Copyright 2025 gparap
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
package gparap.apps.converter_unit.converters;

import org.junit.Test;

public class LengthDistanceConverterUnitTest {
    @Test
    public void isCorrect_convertMeterToKilometer() {
        int meters = 1;
        double kilometersExpected = 0.001;
        assert kilometersExpected == meters * 0.001;
    }

    @Test
    public void isCorrect_convertMeterToMile() {
        int meters = 1;
        double milesExpected = 6.21371E-4;
        assert milesExpected == meters * 0.000621371;
    }

    @Test
    public void isCorrect_convertMeterToYard() {
        int meters = 1;
        double yardsExpected = 1.09361;
        assert yardsExpected == meters * 1.09361;
    }

    @Test
    public void isCorrect_convertMeterToFoot() {
        int meters = 1;
        double feetExpected = 3.28084;
        assert feetExpected == meters * 3.28084;
    }

    @Test
    public void isCorrect_convertMeterToInch() {
        int meters = 1;
        double inchesExpected = 39.3701;
        assert inchesExpected == meters * 39.3701;
    }

    @Test
    public void isCorrect_convertKilometerToMeter() {
        int kilometers = 1;
        double metersExpected = 1000;
        assert metersExpected == kilometers * 1000;
    }

    @Test
    public void isCorrect_convertKilometerToMile() {
        int kilometers = 1;
        double milesExpected = 0.621371;
        assert milesExpected == kilometers * 0.621371;
    }

    @Test
    public void isCorrect_convertKilometerToYard() {
        int kilometers = 1;
        double yardsExpected = 1093.61;
        assert yardsExpected == kilometers * 1093.61;
    }

    @Test
    public void isCorrect_convertKilometerToFoot() {
        int kilometers = 1;
        double feetExpected = 3280.84;
        assert feetExpected == kilometers * 3280.84;
    }

    @Test
    public void isCorrect_convertKilometerToInch() {
        int kilometers = 1;
        double inchesExpected = 39370.1;
        assert inchesExpected == kilometers * 39370.1;
    }

    @Test
    public void isCorrect_convertMileToMeter() {
        int miles = 1;
        double metersExpected = 1609.34;
        assert metersExpected == miles * 1609.34;
    }

    @Test
    public void isCorrect_convertMileToKilometer() {
        int miles = 1;
        double kilometersExpected = 1.60934;
        assert kilometersExpected == miles * 1.60934;
    }

    @Test
    public void isCorrect_convertMileToYard() {
        int miles = 1;
        double yardsExpected = 1760;
        assert yardsExpected == miles * 1760;
    }

    @Test
    public void isCorrect_convertMileToFoot() {
        int miles = 1;
        double feetExpected = 5280;
        assert feetExpected == miles * 5280;
    }

    @Test
    public void isCorrect_convertMileToInch() {
        int miles = 1;
        double inchesExpected = 63360;
        assert inchesExpected == miles * 63360;
    }

    @Test
    public void isCorrect_convertYardToMeter() {
        int yards = 1;
        double metersExpected = 0.9144;
        assert metersExpected == yards * 0.9144;
    }

    @Test
    public void isCorrect_convertYardToKilometer() {
        int yards = 1;
        double kilometersExpected = 9.144E-4;
        assert kilometersExpected == yards * 0.0009144;
    }

    @Test
    public void isCorrect_convertYardToMile() {
        int yards = 1;
        double milesExpected = 5.68182E-4;
        assert milesExpected == yards * 0.000568182;
    }

    @Test
    public void isCorrect_convertYardToFoot() {
        int yards = 1;
        double feetExpected = 3;
        assert feetExpected == yards * 3;
    }

    @Test
    public void isCorrect_convertYardToInch() {
        int yards = 1;
        double inchesExpected = 36;
        assert inchesExpected == yards * 36;
    }

    @Test
    public void isCorrect_convertFootToMeter() {
        int feet = 1;
        double metersExpected = 0.3048;
        assert metersExpected == feet * 0.3048;
    }

    @Test
    public void isCorrect_convertFootToKilometer() {
        int feet = 1;
        double kilometersExpected = 3.048E-4;
        assert kilometersExpected == feet * 0.0003048;
    }

    @Test
    public void isCorrect_convertFootToMile() {
        int feet = 1;
        double milesExpected = 1.89394E-4;
        assert milesExpected == feet * 0.000189394;
    }

    @Test
    public void isCorrect_convertFootToYard() {
        int feet = 1;
        double yardsExpected = 0.333333;
        assert yardsExpected == feet * 0.333333;
    }

    @Test
    public void isCorrect_convertFootToInch() {
        int feet = 1;
        double inchesExpected = 12;
        assert inchesExpected == feet * 12;
    }

    @Test
    public void isCorrect_convertInchToMeter() {
        int inches = 1;
        double metersExpected = 0.0254;
        assert metersExpected == inches * 0.0254;
    }

    @Test
    public void isCorrect_convertInchToKilometer() {
        int inches = 1;
        double kilometersExpected = 2.54E-5;
        assert kilometersExpected == inches * 0.0000254;
    }

    @Test
    public void isCorrect_convertInchToMile() {
        int inches = 1;
        double milesExpected = 1.57828E-5;
        assert milesExpected == inches * 0.0000157828;
    }

    @Test
    public void isCorrect_convertInchToYard() {
        int inches = 1;
        double yardsExpected = 0.0277778;
        assert yardsExpected == inches * 0.0277778;
    }

    @Test
    public void isCorrect_convertInchToFoot() {
        int inches = 1;
        double feetExpected = 0.0833333;
        assert feetExpected == inches * 0.0833333;
    }
}
