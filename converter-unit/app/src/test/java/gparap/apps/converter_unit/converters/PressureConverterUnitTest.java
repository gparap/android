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
    private final PressureConverter converter = new PressureConverter();

    @Test
    public void isCorrect_convertPascalToBar() {
        double pascals = 1;
        double barsExpected = 100000;
        assert barsExpected == converter.convertPascalToBar(pascals);
    }

    @Test
    public void isCorrect_convertPascalToAtmosphere() {
        double pascals = 1;
        double atmospheresExpected = 101325;
        assert atmospheresExpected == converter.convertPascalToAtmosphere(pascals);
    }

    @Test
    public void isCorrect_convertPascalToTorr() {
        double pascals = 1;
        double torrExpected = 133.322;
        assert torrExpected == converter.convertPascalToTorr(pascals);
    }

    @Test
    public void isCorrect_convertPascalToPoundPerSquareInch() {
        double pascals = 1;
        double poundsPerSquareInchExpected = 6894.76;
        assert poundsPerSquareInchExpected == converter.convertPascalToPoundPerSquareInch(pascals);
    }

    @Test
    public void isCorrect_convertPascalToKilopascal() {
        double pascals = 1;
        double kilopascalsExpected = 1000;
        assert kilopascalsExpected == converter.convertPascalToKilopascal(pascals);
    }

    @Test
    public void isCorrect_convertPascalToMillimeterOfMercury() {
        double pascals = 1;
        double millimetersOfMercuryExpected = 133.322;
        assert millimetersOfMercuryExpected == converter.convertPascalToMillimeterOfMercury(pascals);
    }

    @Test
    public void isCorrect_convertBarToPascal() {
        double bars = 1;
        double pascalExpected = 1.0E-5;
        assert pascalExpected == converter.convertBarToPascal(bars);
    }

    @Test
    public void isCorrect_convertBarToAtmosphere() {
        double bars = 1;
        double atmospheresExpected = 1.01325;
        assert atmospheresExpected == converter.convertBarToAtmosphere(bars);
    }

    @Test
    public void isCorrect_convertBarToTorr() {
        double bars = 1;
        double torrExpected = 0.0013332200000000002;
        assert torrExpected == converter.convertBarToTorr(bars);
    }

    @Test
    public void isCorrect_convertBarToPoundPerSquareInch() {
        double bars = 1;
        double poundsPerSquareInchExpected = 0.0689476;
        assert poundsPerSquareInchExpected == converter.convertBarToPoundPerSquareInch(bars);
    }

    @Test
    public void isCorrect_convertBarToKilopascal() {
        double bars = 1;
        double kilopascalsExpected = 0.01;
        assert kilopascalsExpected == converter.convertBarToKilopascal(bars);
    }

    @Test
    public void isCorrect_convertBarToMillimeterOfMercury() {
        double bars = 1;
        double millimetersOfMercuryExpected = 0.0013332200000000002;
        assert millimetersOfMercuryExpected == converter.convertBarToMillimeterOfMercury(bars);
    }

    @Test
    public void isCorrect_convertAtmosphereToPascal() {
        double atmospheres = 1;
        double pascalExpected = 9.869229999999999E-6;
        assert pascalExpected == converter.convertAtmosphereToPascal(atmospheres);
    }

    @Test
    public void isCorrect_convertAtmosphereToBar() {
        double atmospheres = 1;
        double barsExpected = 0.986923;
        assert barsExpected == converter.convertAtmosphereToBar(atmospheres);
    }

    @Test
    public void isCorrect_convertAtmosphereToTorr() {
        double atmospheres = 1;
        double torrExpected = 0.00131579;
        assert torrExpected == converter.convertAtmosphereToTorr(atmospheres);
    }

    @Test
    public void isCorrect_convertAtmosphereToPoundPerSquareInch() {
        double atmospheres = 1;
        double poundsPerSquareInchExpected = 0.068046;
        assert poundsPerSquareInchExpected == converter.convertAtmosphereToPoundPerSquareInch(atmospheres);
    }

    @Test
    public void isCorrect_convertAtmosphereToKilopascal() {
        double atmospheres = 1;
        double kilopascalsExpected = 0.00986923;
        assert kilopascalsExpected == converter.convertAtmosphereToKilopascal(atmospheres);
    }

    @Test
    public void isCorrect_convertAtmosphereToMillimeterOfMercury() {
        double atmospheres = 1;
        double millimetersOfMercuryExpected = 0.00131579;
        assert millimetersOfMercuryExpected == converter.convertAtmosphereToMillimeterOfMercury(atmospheres);
    }

    @Test
    public void isCorrect_convertTorrToPascal() {
        double torr = 1;
        double pascalExpected = 0.0075006199999999995;
        assert pascalExpected == converter.convertTorrToPascal(torr);
    }

    @Test
    public void isCorrect_convertTorrToBar() {
        double torr = 1;
        double barsExpected = 750.062;
        assert barsExpected == converter.convertTorrToBar(torr);
    }

    @Test
    public void isCorrect_convertTorrToAtmosphere() {
        double torr = 1;
        double atmospheresExpected = 760;
        assert atmospheresExpected == converter.convertTorrToAtmosphere(torr);
    }

    @Test
    public void isCorrect_convertTorrToPoundPerSquareInch() {
        double torr = 1;
        double poundsPerSquareInchExpected = 51.7149;
        assert poundsPerSquareInchExpected == converter.convertTorrToPoundPerSquareInch(torr);
    }

    @Test
    public void isCorrect_convertTorrToKilopascal() {
        double torr = 1;
        double kilopascalsExpected = 7.50062;
        assert kilopascalsExpected == converter.convertTorrToKilopascal(torr);
    }

    @Test
    public void isCorrect_convertTorrToMillimeterOfMercury() {
        double torr = 1;
        double millimetersOfMercuryExpected = 1;
        assert millimetersOfMercuryExpected == converter.convertTorrToMillimeterOfMercury(torr);
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToPascal() {
        double poundsPerSquareInch = 1;
        double pascalExpected = 1.45038E-4;
        assert pascalExpected == converter.convertPoundPerSquareInchToPascal(poundsPerSquareInch);
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToBar() {
        double poundsPerSquareInch = 1;
        double barsExpected = 14.5038;
        assert barsExpected == converter.convertPoundPerSquareInchToBar(poundsPerSquareInch);
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToAtmosphere() {
        double poundsPerSquareInch = 1;
        double atmospheresExpected = 14.6959;
        assert atmospheresExpected == converter.convertPoundPerSquareInchToAtmosphere(poundsPerSquareInch);
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToTorr() {
        double poundsPerSquareInch = 1;
        double torrExpected = 0.0193368;
        assert torrExpected == converter.convertPoundPerSquareInchToTorr(poundsPerSquareInch);
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToKilopascal() {
        double poundsPerSquareInch = 1;
        double kilopascalsExpected = 0.145038;
        assert kilopascalsExpected == converter.convertPoundPerSquareInchToKilopascal(poundsPerSquareInch);
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToMillimeterOfMercury() {
        double poundsPerSquareInch = 1;
        double millimetersOfMercuryExpected = 0.0193368;
        assert millimetersOfMercuryExpected == converter.convertPoundPerSquareInchToMillimeterOfMercury(poundsPerSquareInch);
    }

    @Test
    public void isCorrect_convertKilopascalToPascal() {
        double kilopascals = 1;
        double pascalExpected = 0.001;
        assert pascalExpected == converter.convertKilopascalToPascal(kilopascals);
    }

    @Test
    public void isCorrect_convertKilopascalToBar() {
        double kilopascals = 1;
        double barsExpected = 100;
        assert barsExpected == converter.convertKilopascalToBar(kilopascals);
    }

    @Test
    public void isCorrect_convertKilopascalToAtmosphere() {
        double kilopascals = 1;
        double atmospheresExpected = 101.325;
        assert atmospheresExpected == converter.convertKilopascalToAtmosphere(kilopascals);
    }

    @Test
    public void isCorrect_convertKilopascalToTorr() {
        double kilopascals = 1;
        double torrExpected = 0.133322;
        assert torrExpected == converter.convertKilopascalToTorr(kilopascals);
    }

    @Test
    public void isCorrect_convertKilopascalToPoundPerSquareInch() {
        double kilopascals = 1;
        double poundsPerSquareInchExpected = 6.89476;
        assert poundsPerSquareInchExpected == converter.convertKilopascalToPoundPerSquareInch(kilopascals);
    }

    @Test
    public void isCorrect_convertKilopascalToMillimeterOfMercury() {
        double kilopascals = 1;
        double millimetersOfMercuryExpected = 0.133322;
        assert millimetersOfMercuryExpected == converter.convertKilopascalToMillimeterOfMercury(kilopascals);
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToPascal() {
        double millimetersOfMercury = 1;
        double pascalExpected = 0.0075006199999999995;
        assert pascalExpected == converter.convertMillimeterOfMercuryToPascal(millimetersOfMercury);
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToBar() {
        double millimetersOfMercury = 1;
        double barsExpected = 750.062;
        assert barsExpected == converter.convertMillimeterOfMercuryToBar(millimetersOfMercury);
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToAtmosphere() {
        double millimetersOfMercury = 1;
        double atmospheresExpected = 760;
        assert atmospheresExpected == converter.convertMillimeterOfMercuryToAtmosphere(millimetersOfMercury);
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToTorr() {
        double millimetersOfMercury = 1;
        double torrExpected = 1;
        assert torrExpected == converter.convertMillimeterOfMercuryToTorr(millimetersOfMercury);
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToPoundPerSquareInch() {
        double millimetersOfMercury = 1;
        double poundsPerSquareInchExpected = 51.7149;
        assert poundsPerSquareInchExpected == converter.convertMillimeterOfMercuryToPoundPerSquareInch(millimetersOfMercury);
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToKilopascal() {
        double millimetersOfMercury = 1;
        double kilopascalsExpected = 7.50062;
        assert kilopascalsExpected == converter.convertMillimeterOfMercuryToKilopascal(millimetersOfMercury);
    }
}
