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

public class TemperatureConverter {
    public double convertCelsiusToFahrenheit(double celsius) { return (celsius * 9 / 5) + 32; }
    public double convertCelsiusToKelvin(double celsius) { return celsius + 273.15; }
    public double convertCelsiusToRankine(double celsius) { return (celsius + 273.15) * 9 / 5; }

    public double convertFahrenheitToCelsius(double fahrenheit) { return (fahrenheit - 32) * 5 / 9; }
    public double convertFahrenheitToKelvin(double fahrenheit) { return (fahrenheit + 459.67) * 5 / 9; }
    public double convertFahrenheitToRankine(double fahrenheit) { return fahrenheit + 459.67; }

    public double convertKelvinToCelsius(double kelvin) { return kelvin - 273.15; }
    public double convertKelvinToFahrenheit(double kelvin) { return (kelvin * 9 / 5) - 459.67; }
    public double convertKelvinToRankine(double kelvin) { return kelvin * 9 / 5; }

    public double convertRankineToCelsius(double rankine) { return (rankine - 491.67) * 5 / 9; }
    public double convertRankineToFahrenheit(double rankine) { return rankine - 459.67; }
    public double convertRankineToKelvin(double rankine) { return rankine * 5 / 9; }
}
