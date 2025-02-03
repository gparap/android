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

public class PressureConverter {
    public double convertPascalToBar(double pascals) { return 100000 * pascals; }
    public double convertPascalToAtmosphere(double pascals) { return 101325 * pascals; }
    public double convertPascalToTorr(double pascals) { return 133.322 * pascals; }
    public double convertPascalToPoundPerSquareInch(double pascals) { return 6894.76 * pascals; }
    public double convertPascalToKilopascal(double pascals) { return 1000 * pascals; }
    public double convertPascalToMillimeterOfMercury(double pascals) { return 133.322 * pascals; }

    public double convertBarToPascal(double bars) { return 1 * Math.pow(10, -5) * bars; }
    public double convertBarToAtmosphere(double bars) { return 1.01325 * bars; }
    public double convertBarToTorr(double bars) { return 1.33322 * Math.pow(10, -3) * bars; }
    public double convertBarToPoundPerSquareInch(double bars) { return 6.89476 * Math.pow(10, -2) * bars; }
    public double convertBarToKilopascal(double bars) { return 0.01 * bars; }
    public double convertBarToMillimeterOfMercury(double bars) { return 1.33322 * Math.pow(10, -3) * bars; }

    public double convertAtmosphereToPascal(double atmospheres) { return 9.86923 * Math.pow(10, -6) * atmospheres; }
    public double convertAtmosphereToBar(double atmospheres) { return 0.986923 * atmospheres; }
    public double convertAtmosphereToTorr(double atmospheres) { return 1.31579 * Math.pow(10, -3) * atmospheres; }
    public double convertAtmosphereToPoundPerSquareInch(double atmospheres) { return 6.8046 * Math.pow(10, -2) * atmospheres; }
    public double convertAtmosphereToKilopascal(double atmospheres) { return 9.86923 * Math.pow(10, -3) * atmospheres; }
    public double convertAtmosphereToMillimeterOfMercury(double atmospheres) { return 1.31579 * Math.pow(10, -3) * atmospheres; }

    public double convertTorrToPascal(double torr) { return 7.50062 * Math.pow(10, -3) * torr; }
    public double convertTorrToBar(double torr) { return 750.062 * torr; }
    public double convertTorrToAtmosphere(double torr) { return 760 * torr; }
    public double convertTorrToPoundPerSquareInch(double torr) { return 51.7149 * torr; }
    public double convertTorrToKilopascal(double torr) { return 7.50062 * torr; }
    public double convertTorrToMillimeterOfMercury(double torr) { return 1 * torr; }

    public double convertPoundPerSquareInchToPascal(double poundsPerSquareInch) { return 1.45038 * Math.pow(10, -4) * poundsPerSquareInch; }
    public double convertPoundPerSquareInchToBar(double poundsPerSquareInch) { return 14.5038 * poundsPerSquareInch; }
    public double convertPoundPerSquareInchToAtmosphere(double poundsPerSquareInch) { return 14.6959 * poundsPerSquareInch; }
    public double convertPoundPerSquareInchToTorr(double poundsPerSquareInch) { return 1.93368 * Math.pow(10, -2) * poundsPerSquareInch; }
    public double convertPoundPerSquareInchToKilopascal(double poundsPerSquareInch) { return 0.145038 * poundsPerSquareInch; }
    public double convertPoundPerSquareInchToMillimeterOfMercury(double poundsPerSquareInch) { return 1.93368 * Math.pow(10, -2) * poundsPerSquareInch; }

    public double convertKilopascalToPascal(double kilopascals) { return 0.001 * kilopascals; }
    public double convertKilopascalToBar(double kilopascals) { return 100 * kilopascals; }
    public double convertKilopascalToAtmosphere(double kilopascals) { return 101.325 * kilopascals; }
    public double convertKilopascalToTorr(double kilopascals) { return 0.133322 * kilopascals; }
    public double convertKilopascalToPoundPerSquareInch(double kilopascals) { return 6.89476 * kilopascals; }
    public double convertKilopascalToMillimeterOfMercury(double kilopascals) { return 0.133322 * kilopascals; }

    public double convertMillimeterOfMercuryToPascal(double millimetersOfMercury) { return 7.50062 * Math.pow(10, -3) * millimetersOfMercury; }
    public double convertMillimeterOfMercuryToBar(double millimetersOfMercury) { return 750.062 * millimetersOfMercury; }
    public double convertMillimeterOfMercuryToAtmosphere(double millimetersOfMercury) { return 760 * millimetersOfMercury; }
    public double convertMillimeterOfMercuryToTorr(double millimetersOfMercury) { return 1 * millimetersOfMercury; }
    public double convertMillimeterOfMercuryToPoundPerSquareInch(double millimetersOfMercury) { return 51.7149 * millimetersOfMercury; }
    public double convertMillimeterOfMercuryToKilopascal(double millimetersOfMercury) { return 7.50062 * millimetersOfMercury; }
}
