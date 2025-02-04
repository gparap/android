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

public class TemperatureConverterUnitTest {
    private final TemperatureConverter converter = new TemperatureConverter();

    @Test
    public void isCorrect_convertCelsiusToFahrenheit() {
        double celsius = 1;
        double fahrenheitExpected = 33.8;
        assert fahrenheitExpected == converter.convertCelsiusToFahrenheit(celsius);
    }

    @Test
    public void isCorrect_convertCelsiusToKelvin() {
        double celsius = 1;
        double kelvinExpected = 274.15;
        assert kelvinExpected == converter.convertCelsiusToKelvin(celsius);
    }

    @Test
    public void isCorrect_convertCelsiusToRankine() {
        double celsius = 1;
        double rankineExpected = 493.46999999999997;
        assert rankineExpected == converter.convertCelsiusToRankine(celsius);
    }

    @Test
    public void isCorrect_convertFahrenheitToCelsius() {
        double fahrenheit = 1;
        double celsiusExpected = -17.22222222222222;
        assert celsiusExpected == converter.convertFahrenheitToCelsius(fahrenheit);
    }

    @Test
    public void isCorrect_convertFahrenheitToKelvin() {
        double fahrenheit = 1;
        double kelvinExpected = 255.92777777777778;
        assert kelvinExpected == converter.convertFahrenheitToKelvin(fahrenheit);
    }

    @Test
    public void isCorrect_convertFahrenheitToRankine() {
        double fahrenheit = 1;
        double rankineExpected = 460.67;
        assert rankineExpected == converter.convertFahrenheitToRankine(fahrenheit);
    }

    @Test
    public void isCorrect_convertKelvinToCelsius() {
        double kelvin = 1;
        double celsiusExpected = -272.15;
        assert celsiusExpected == converter.convertKelvinToCelsius(kelvin);
    }

    @Test
    public void isCorrect_convertKelvinToFahrenheit() {
        double kelvin = 1;
        double fahrenheitExpected = -457.87;
        assert fahrenheitExpected == converter.convertKelvinToFahrenheit(kelvin);
    }

    @Test
    public void isCorrect_convertKelvinToRankine() {
        double kelvin = 1;
        double rankineExpected = 1.8;
        assert rankineExpected == converter.convertKelvinToRankine(kelvin);
    }

    @Test
    public void isCorrect_convertRankineToCelsius() {
        double rankine = 1;
        double celsiusExpected = -272.59444444444443;
        assert celsiusExpected == converter.convertRankineToCelsius(rankine);
    }

    @Test
    public void isCorrect_convertRankineToFahrenheit() {
        double rankine = 1;
        double fahrenheitExpected = -458.67;
        assert fahrenheitExpected == converter.convertRankineToFahrenheit(rankine);
    }

    @Test
    public void isCorrect_convertRankineToKelvin() {
        double rankine = 1;
        double kelvinExpected = 0.5555555555555556;
        assert kelvinExpected == converter.convertRankineToKelvin(rankine);
    }
}
