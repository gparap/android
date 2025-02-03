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

public class PressureConverterUnitTest {

    @Test
    public void isCorrect_convertPascalToBar() {
        double pascals = 1;
        double barsExpected = 100000;
        assert barsExpected == 100000 * pascals;
    }

    @Test
    public void isCorrect_convertPascalToAtmosphere() {
        double pascals = 1;
        double atmospheresExpected = 101325;
        assert atmospheresExpected == 101325 * pascals;
    }

    @Test
    public void isCorrect_convertPascalToTorr() {
        double pascals = 1;
        double torrExpected = 133.322;
        assert torrExpected == 133.322 * pascals;
    }

    @Test
    public void isCorrect_convertPascalToPoundPerSquareInch() {
        double pascals = 1;
        double poundsPerSquareInchExpected = 6894.76;
        assert poundsPerSquareInchExpected == 6894.76 * pascals;
    }

    @Test
    public void isCorrect_convertPascalToKilopascal() {
        double pascals = 1;
        double kilopascalsExpected = 1000;
        assert kilopascalsExpected == 1000 * pascals;
    }

    @Test
    public void isCorrect_convertPascalToMillimeterOfMercury() {
        double pascals = 1;
        double millimetersOfMercuryExpected = 133.322;
        assert millimetersOfMercuryExpected == 133.322 * pascals;
    }

    @Test
    public void isCorrect_convertBarToPascal() {
        double bars = 1;
        double pascalExpected = 1.0E-5;
        assert pascalExpected == 1 * Math.pow(10, -5) * bars;
    }

    @Test
    public void isCorrect_convertBarToAtmosphere() {
        double bars = 1;
        double atmospheresExpected = 1.01325;
        assert atmospheresExpected == 1.01325 * bars;
    }

    @Test
    public void isCorrect_convertBarToTorr() {
        double bars = 1;
        double torrExpected = 0.0013332200000000002;
        assert torrExpected == 1.33322 * Math.pow(10, -3) * bars;
    }

    @Test
    public void isCorrect_convertBarToPoundPerSquareInch() {
        double bars = 1;
        double poundsPerSquareInchExpected = 0.0689476;
        assert poundsPerSquareInchExpected == 6.89476 * Math.pow(10, -2) * bars;
    }

    @Test
    public void isCorrect_convertBarToKilopascal() {
        double bars = 1;
        double kilopascalsExpected = 0.01;
        assert kilopascalsExpected == 0.01 * bars;
    }

    @Test
    public void isCorrect_convertBarToMillimeterOfMercury() {
        double bars = 1;
        double millimetersOfMercuryExpected = 0.0013332200000000002;
        assert millimetersOfMercuryExpected == 1.33322 * Math.pow(10, -3) * bars;
    }

    @Test
    public void isCorrect_convertAtmosphereToPascal() {
        double atmospheres = 1;
        double pascalExpected = 9.869229999999999E-6;
        assert pascalExpected == 9.86923 * Math.pow(10, -6) * atmospheres;
    }

    @Test
    public void isCorrect_convertAtmosphereToBar() {
        double atmospheres = 1;
        double barsExpected = 0.986923;
        assert barsExpected == 0.986923 * atmospheres;
    }

    @Test
    public void isCorrect_convertAtmosphereToTorr() {
        double atmospheres = 1;
        double torrExpected = 0.00131579;
        assert torrExpected == 1.31579 * Math.pow(10, -3) * atmospheres;
    }

    @Test
    public void isCorrect_convertAtmosphereToPoundPerSquareInch() {
        double atmospheres = 1;
        double poundsPerSquareInchExpected = 0.068046;
        assert poundsPerSquareInchExpected == 6.8046 * Math.pow(10, -2) * atmospheres;
    }

    @Test
    public void isCorrect_convertAtmosphereToKilopascal() {
        double atmospheres = 1;
        double kilopascalsExpected = 0.00986923;
        assert kilopascalsExpected == 9.86923 * Math.pow(10, -3) * atmospheres;
    }

    @Test
    public void isCorrect_convertAtmosphereToMillimeterOfMercury() {
        double atmospheres = 1;
        double millimetersOfMercuryExpected = 0.00131579;
        assert millimetersOfMercuryExpected == 1.31579 * Math.pow(10, -3) * atmospheres;
    }

    @Test
    public void isCorrect_convertTorrToPascal() {
        double torr = 1;
        double pascalExpected = 0.0075006199999999995;
        assert pascalExpected == 7.50062 * Math.pow(10, -3) * torr;
    }

    @Test
    public void isCorrect_convertTorrToBar() {
        double torr = 1;
        double barsExpected = 750.062;
        assert barsExpected == 750.062 * torr;
    }

    @Test
    public void isCorrect_convertTorrToAtmosphere() {
        double torr = 1;
        double atmospheresExpected = 760;
        assert atmospheresExpected == 760 * torr;
    }

    @Test
    public void isCorrect_convertTorrToPoundPerSquareInch() {
        double torr = 1;
        double poundsPerSquareInchExpected = 51.7149;
        assert poundsPerSquareInchExpected == 51.7149 * torr;
    }

    @Test
    public void isCorrect_convertTorrToKilopascal() {
        double torr = 1;
        double kilopascalsExpected = 7.50062;
        assert kilopascalsExpected == 7.50062 * torr;
    }

    @Test
    public void isCorrect_convertTorrToMillimeterOfMercury() {
        double torr = 1;
        double millimetersOfMercuryExpected = 1;
        assert millimetersOfMercuryExpected == 1 * torr;
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToPascal() {
        double poundsPerSquareInch = 1;
        double pascalExpected = 1.45038E-4;
        assert pascalExpected == 1.45038 * Math.pow(10, -4) * poundsPerSquareInch;
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToBar() {
        double poundsPerSquareInch = 1;
        double barsExpected = 14.5038;
        assert barsExpected == 14.5038 * poundsPerSquareInch;
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToAtmosphere() {
        double poundsPerSquareInch = 1;
        double atmospheresExpected = 14.6959;
        assert atmospheresExpected == 14.6959 * poundsPerSquareInch;
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToTorr() {
        double poundsPerSquareInch = 1;
        double torrExpected = 0.0193368;
        assert torrExpected == 1.93368 * Math.pow(10, -2) * poundsPerSquareInch;
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToKilopascal() {
        double poundsPerSquareInch = 1;
        double kilopascalsExpected = 0.145038;
        assert kilopascalsExpected == 0.145038 * poundsPerSquareInch;
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToMillimeterOfMercury() {
        double poundsPerSquareInch = 1;
        double millimetersOfMercuryExpected = 0.0193368;
        assert millimetersOfMercuryExpected == 1.93368 * Math.pow(10, -2) * poundsPerSquareInch;
    }

    @Test
    public void isCorrect_convertKilopascalToPascal() {
        double kilopascals = 1;
        double pascalExpected = 0.001;
        assert pascalExpected == 0.001 * kilopascals;
    }

    @Test
    public void isCorrect_convertKilopascalToBar() {
        double kilopascals = 1;
        double barsExpected = 100;
        assert barsExpected == 100 * kilopascals;
    }

    @Test
    public void isCorrect_convertKilopascalToAtmosphere() {
        double kilopascals = 1;
        double atmospheresExpected = 101.325;
        assert atmospheresExpected == 101.325 * kilopascals;
    }

    @Test
    public void isCorrect_convertKilopascalToTorr() {
        double kilopascals = 1;
        double torrExpected = 0.133322;
        assert torrExpected == 0.133322 * kilopascals;
    }

    @Test
    public void isCorrect_convertKilopascalToPoundPerSquareInch() {
        double kilopascals = 1;
        double poundsPerSquareInchExpected = 6.89476;
        assert poundsPerSquareInchExpected == 6.89476 * kilopascals;
    }

    @Test
    public void isCorrect_convertKilopascalToMillimeterOfMercury() {
        double kilopascals = 1;
        double millimetersOfMercuryExpected = 0.133322;
        assert millimetersOfMercuryExpected == 0.133322 * kilopascals;
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToPascal() {
        double millimetersOfMercury = 1;
        double pascalExpected = 0.0075006199999999995;
        assert pascalExpected == 7.50062 * Math.pow(10, -3) * millimetersOfMercury;
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToBar() {
        double millimetersOfMercury = 1;
        double barsExpected = 750.062;
        assert barsExpected == 750.062 * millimetersOfMercury;
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToAtmosphere() {
        double millimetersOfMercury = 1;
        double atmospheresExpected = 760;
        assert atmospheresExpected == 760 * millimetersOfMercury;
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToTorr() {
        double millimetersOfMercury = 1;
        double torrExpected = 1;
        assert torrExpected == 1 * millimetersOfMercury;
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToPoundPerSquareInch() {
        double millimetersOfMercury = 1;
        double poundsPerSquareInchExpected = 51.7149;
        assert poundsPerSquareInchExpected == 51.7149 * millimetersOfMercury;
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToKilopascal() {
        double millimetersOfMercury = 1;
        double kilopascalsExpected = 7.50062;
        assert kilopascalsExpected == 7.50062 * millimetersOfMercury;
    }
}
