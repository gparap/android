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

public class LengthDistanceConverter {
    public double convertMeterToKilometer(double meters) { return meters * 0.001; }
    public double convertMeterToMile(double meters) { return 0.000621371; }
    public double convertMeterToYard(double meters) { return meters * 1.09361; }
    public double convertMeterToFoot(double meters) { return meters * 3.28084; }
    public double convertMeterToInch(double meters) { return meters * 39.3701; }

    public double convertKilometerToMeter(double kilometers) { return kilometers * 1000; }
    public double convertKilometerToMile(double kilometers) { return kilometers * 0.621371; }
    public double convertKilometerToYard(double kilometers) { return kilometers * 1093.61; }
    public double convertKilometerToFoot(double kilometers) { return kilometers * 3280.84; }
    public double convertKilometerToInch(double kilometers) { return kilometers * 39370.1; }

    public double convertMileToMeter(double miles) { return miles * 1609.34; }
    public double convertMileToKilometer(double miles) { return miles * 1.60934; }
    public double convertMileToYard(double miles) { return miles * 1760; }
    public double convertMileToFoot(double miles) { return miles * 5280; }
    public double convertMileToInch(double miles) { return miles * 63360; }

    public double convertYardToMeter(double yards) { return yards * 0.9144; }
    public double convertYardToKilometer(double yards) { return yards * 0.0009144; }
    public double convertYardToMile(double yards) { return yards * 0.000568182; }
    public double convertYardToFoot(double yards) { return yards * 3; }
    public double convertYardToInch(double yards) { return yards * 36; }

    public double convertFootToMeter(double feet) { return feet * 0.3048; }
    public double convertFootToKilometer(double feet) { return feet * 0.0003048; }
    public double convertFootToMile(double feet) { return feet * 0.000189394; }
    public double convertFootToYard(double feet) { return feet * 0.333333; }
    public double convertFootToInch(double feet) { return feet * 12; }

    public double convertInchToMeter(double inches) { return inches * 0.0254; }
    public double convertInchToKilometer(double inches) { return inches * 0.0000254; }
    public double convertInchToMile(double inches) { return inches * 0.0000157828; }
    public double convertInchToYard(double inches) { return inches * 0.0277778; }
    public double convertInchToFoot(double inches) { return inches * 0.0833333; }
}
