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

public class MassWeightConverterUnitTest {
    private final MassWeightConverter converter = new MassWeightConverter();

    @Test
    public void isCorrect_convertKilogramToGram() {
        int kilograms = 1;
        double gramsExpected = 1000;
        System.out.println(kilograms * 1000);
        assert gramsExpected == converter.convertKilogramToGram(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToMilligram() {
        int kilograms = 1;
        double milligramsExpected = 1000000;
        System.out.println(kilograms * 1000000);
        assert milligramsExpected == converter.convertKilogramToMilligram(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToMetricTon() {
        int kilograms = 1;
        double metricTonsExpected = 0.001;
        System.out.println(kilograms * 0.001);
        assert metricTonsExpected == converter.convertKilogramToMetricTon(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToUSTon() {
        int kilograms = 1;
        double usTonsExpected = 0.00110231;
        System.out.println(kilograms * 0.00110231);
        assert usTonsExpected == converter.convertKilogramToUSTon(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToImperialTon() {
        int kilograms = 1;
        double imperialTonsExpected = 9.84207E-4;
        System.out.println(kilograms * 0.000984207);
        assert imperialTonsExpected == converter.convertKilogramToImperialTon(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToPound() {
        int kilograms = 1;
        double poundsExpected = 2.20462;
        System.out.println(kilograms * 2.20462);
        assert poundsExpected == converter.convertKilogramToPound(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToUSOunce() {
        int kilograms = 1;
        double usOuncesExpected = 35.274;
        System.out.println(kilograms * 35.274);
        assert usOuncesExpected == converter.convertKilogramToUSOunce(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToImperialOunce() {
        int kilograms = 1;
        double imperialOuncesExpected = 35.195;
        System.out.println(kilograms * 35.195);
        assert imperialOuncesExpected == converter.convertKilogramToImperialOunce(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToStone() {
        int kilograms = 1;
        double stonesExpected = 0.157473;
        System.out.println(kilograms * 0.157473);
        assert stonesExpected == converter.convertKilogramToStone(kilograms);
    }

    @Test
    public void isCorrect_convertKilogramToCarat() {
        int kilograms = 1;
        double caratsExpected = 5000;
        System.out.println(kilograms * 5000);
        assert caratsExpected == converter.convertKilogramToCarat(kilograms);
    }

    @Test
    public void isCorrect_convertGramToKilogram() {
        int grams = 1;
        double kilogramsExpected = 0.001;
        System.out.println(grams * 0.001);
        assert kilogramsExpected == converter.convertGramToKilogram(grams);
    }

    @Test
    public void isCorrect_convertGramToMilligram() {
        int grams = 1;
        double milligramsExpected = 1000;
        System.out.println(grams * 1000);
        assert milligramsExpected == converter.convertGramToMilligram(grams);
    }

    @Test
    public void isCorrect_convertGramToMetricTon() {
        int grams = 1;
        double metricTonsExpected = 1.0E-6;
        System.out.println(grams * 0.000001);
        assert metricTonsExpected == converter.convertGramToMetricTon(grams);
    }

    @Test
    public void isCorrect_convertGramToUSTon() {
        int grams = 1;
        double usTonsExpected = 1.10231E-6;
        System.out.println(grams * 0.00000110231);
        assert usTonsExpected == converter.convertGramToUSTon(grams);
    }

    @Test
    public void isCorrect_convertGramToImperialTon() {
        int grams = 1;
        double imperialTonsExpected = 9.84207E-7;
        System.out.println(grams * 0.000000984207);
        assert imperialTonsExpected == converter.convertGramToImperialTon(grams);
    }

    @Test
    public void isCorrect_convertGramToPound() {
        int grams = 1;
        double poundsExpected = 0.00220462;
        System.out.println(grams * 0.00220462);
        assert poundsExpected == converter.convertGramToPound(grams);
    }

    @Test
    public void isCorrect_convertGramToUSOunce() {
        int grams = 1;
        double usOuncesExpected = 0.035274;
        System.out.println(grams * 0.035274);
        assert usOuncesExpected == converter.convertGramToUSOunce(grams);
    }

    @Test
    public void isCorrect_convertGramToImperialOunce() {
        int grams = 1;
        double imperialOuncesExpected = 0.035195;
        System.out.println(grams * 0.035195);
        assert imperialOuncesExpected == converter.convertGramToImperialOunce(grams);
    }

    @Test
    public void isCorrect_convertGramToStone() {
        int grams = 1;
        double stonesExpected = 1.57473E-4;
        System.out.println(grams * 0.000157473);
        assert stonesExpected == converter.convertGramToStone(grams);
    }

    @Test
    public void isCorrect_convertGramToCarat() {
        int grams = 1;
        double caratsExpected = 5;
        System.out.println(grams * 5);
        assert caratsExpected == converter.convertGramToCarat(grams);
    }

    @Test
    public void isCorrect_convertMilligramToKilogram() {
        int milligrams = 1;
        double kilogramsExpected = 1.0E-6;
        System.out.println(milligrams * 0.000001);
        assert kilogramsExpected == converter.convertMilligramToKilogram(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToGram() {
        int milligrams = 1;
        double gramsExpected = 0.001;
        System.out.println(milligrams * 0.001);
        assert gramsExpected == converter.convertMilligramToGram(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToMetricTon() {
        int milligrams = 1;
        double metricTonsExpected = 1.0E-9;
        System.out.println(milligrams * 0.000000001);
        assert metricTonsExpected == converter.convertMilligramToMetricTon(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToUSTon() {
        int milligrams = 1;
        double usTonsExpected = 1.10231E-9;
        System.out.println(milligrams * 0.00000000110231);
        assert usTonsExpected == converter.convertMilligramToUSTon(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToImperialTon() {
        int milligrams = 1;
        double imperialTonsExpected = 9.84207E-10;
        System.out.println(milligrams * 0.000000000984207);
        assert imperialTonsExpected == converter.convertMilligramToImperialTon(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToPound() {
        int milligrams = 1;
        double poundsExpected = 2.20462E-6;
        System.out.println(milligrams * 0.00000220462);
        assert poundsExpected == converter.convertMilligramToPound(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToUSOunce() {
        int milligrams = 1;
        double usOuncesExpected = 3.5274E-5;
        System.out.println(milligrams * 0.000035274);
        assert usOuncesExpected == converter.convertMilligramToUSOunce(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToImperialOunce() {
        int milligrams = 1;
        double imperialOuncesExpected = 3.5195E-5;
        System.out.println(milligrams * 0.000035195);
        assert imperialOuncesExpected == converter.convertMilligramToImperialOunce(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToStone() {
        int milligrams = 1;
        double stonesExpected = 1.57473E-7;
        System.out.println(milligrams * 0.000000157473);
        assert stonesExpected == converter.convertMilligramToStone(milligrams);
    }

    @Test
    public void isCorrect_convertMilligramToCarat() {
        int milligrams = 1;
        double caratsExpected = 0.005;
        System.out.println(milligrams * 0.005);
        assert caratsExpected == converter.convertMilligramToCarat(milligrams);
    }

    @Test
    public void isCorrect_convertMetricTonToKilogram() {
        int metricTons = 1;
        double kilogramsExpected = 1000;
        System.out.println(metricTons * 1000);
        assert kilogramsExpected == converter.convertMetricTonToKilogram(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToGram() {
        int metricTons = 1;
        double gramsExpected = 1000000;
        System.out.println(metricTons * 1000000);
        assert gramsExpected == converter.convertMetricTonToGram(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToMilligram() {
        int metricTons = 1;
        double milligramsExpected = 1000000000;
        System.out.println(metricTons * 1000000000);
        assert milligramsExpected == converter.convertMetricTonToMilligram(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToUSTon() {
        int metricTons = 1;
        double usTonsExpected = 1.10231;
        System.out.println(metricTons * 1.10231);
        assert usTonsExpected == converter.convertMetricTonToUSTon(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToImperialTon() {
        int metricTons = 1;
        double imperialTonsExpected = 0.984207;
        System.out.println(metricTons * 0.984207);
        assert imperialTonsExpected == converter.convertMetricTonToImperialTon(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToPound() {
        int metricTons = 1;
        double poundsExpected = 2204.62;
        System.out.println(metricTons * 2204.62);
        assert poundsExpected == converter.convertMetricTonToPound(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToUSOunce() {
        int metricTons = 1;
        double usOuncesExpected = 35274;
        System.out.println(metricTons * 35274);
        assert usOuncesExpected == converter.convertMetricTonToUSOunce(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToImperialOunce() {
        int metricTons = 1;
        double imperialOuncesExpected = 35195;
        System.out.println(metricTons * 35195);
        assert imperialOuncesExpected == converter.convertMetricTonToImperialOunce(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToStone() {
        int metricTons = 1;
        double stonesExpected = 157.473;
        System.out.println(metricTons * 157.473);
        assert stonesExpected == converter.convertMetricTonToStone(metricTons);
    }

    @Test
    public void isCorrect_convertMetricTonToCarat() {
        int metricTons = 1;
        double caratsExpected = 5000000;
        System.out.println(metricTons * 5000000);
        assert caratsExpected == converter.convertMetricTonToCarat(metricTons);
    }

    @Test
    public void isCorrect_convertUSTonToKilogram() {
        int usTons = 1;
        double kilogramsExpected = 907.18474;
        System.out.println(usTons * 907.18474);
        assert kilogramsExpected == converter.convertUSTonToKilogram(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToGram() {
        int usTons = 1;
        double gramsExpected = 907184.74;
        System.out.println(usTons * 907184.74);
        assert gramsExpected == converter.convertUSTonToGram(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToMilligram() {
        int usTons = 1;
        double milligramsExpected = 907184740;
        System.out.println(usTons * 907184740);
        assert milligramsExpected == converter.convertUSTonToMilligram(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToMetricTon() {
        int usTons = 1;
        double metricTonsExpected = 0.90718474;
        System.out.println(usTons * 0.90718474);
        assert metricTonsExpected == converter.convertUSTonToMetricTon(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToImperialTon() {
        int usTons = 1;
        double imperialTonsExpected = 0.892857;
        System.out.println(usTons * 0.892857);
        assert imperialTonsExpected == converter.convertUSTonToImperialTon(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToPound() {
        int usTons = 1;
        double poundsExpected = 2000;
        System.out.println(usTons * 2000);
        assert poundsExpected == converter.convertUSTonToPound(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToUSOunce() {
        int usTons = 1;
        double usOuncesExpected = 32000;
        System.out.println(usTons * 32000);
        assert usOuncesExpected == converter.convertUSTonToUSOunce(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToImperialOunce() {
        int usTons = 1;
        double imperialOuncesExpected = 31999.93;
        System.out.println(usTons * 31999.93);
        assert imperialOuncesExpected == converter.convertUSTonToImperialOunce(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToStone() {
        int usTons = 1;
        double stonesExpected = 142.857;
        System.out.println(usTons * 142.857);
        assert stonesExpected == converter.convertUSTonToStone(usTons);
    }

    @Test
    public void isCorrect_convertUSTonToCarat() {
        int usTons = 1;
        double caratsExpected = 4535923.7;
        System.out.println(usTons * 4535923.7);
        assert caratsExpected == converter.convertUSTonToCarat(usTons);
    }

    @Test
    public void isCorrect_convertImperialTonToKilogram() {
        int imperialTons = 1;
        double kilogramsExpected = 1016.04691;
        System.out.println(imperialTons * 1016.04691);
        assert kilogramsExpected == converter.convertImperialTonToKilogram(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToGram() {
        int imperialTons = 1;
        double gramsExpected = 1016046.91;
        System.out.println(imperialTons * 1016046.91);
        assert gramsExpected == converter.convertImperialTonToGram(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToMilligram() {
        int imperialTons = 1;
        double milligramsExpected = 1016046910;
        System.out.println(imperialTons * 1016046910);
        assert milligramsExpected == converter.convertImperialTonToMilligram(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToMetricTon() {
        int imperialTons = 1;
        double metricTonsExpected = 1.01605;
        System.out.println(imperialTons * 1.01605);
        assert metricTonsExpected == converter.convertImperialTonToMetricTon(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToUSTon() {
        int imperialTons = 1;
        double usTonsExpected = 1.12;
        System.out.println(imperialTons * 1.12);
        assert usTonsExpected == converter.convertImperialTonToUSTon(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToPound() {
        int imperialTons = 1;
        double poundsExpected = 2240;
        System.out.println(imperialTons * 2240);
        assert poundsExpected == converter.convertImperialTonToPound(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToUSOunce() {
        int imperialTons = 1;
        double usOuncesExpected = 35840;
        System.out.println(imperialTons * 35840);
        assert usOuncesExpected == converter.convertImperialTonToUSOunce(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToImperialOunce() {
        int imperialTons = 1;
        double imperialOuncesExpected = 35839.92;
        System.out.println(imperialTons * 35839.92);
        assert imperialOuncesExpected == converter.convertImperialTonToImperialOunce(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToStone() {
        int imperialTons = 1;
        double stonesExpected = 160;
        System.out.println(imperialTons * 160);
        assert stonesExpected == converter.convertImperialTonToStone(imperialTons);
    }

    @Test
    public void isCorrect_convertImperialTonToCarat() {
        int imperialTons = 1;
        double caratsExpected = 5080234.55;
        System.out.println(imperialTons * 5080234.55);
        assert caratsExpected == converter.convertImperialTonToCarat(imperialTons);
    }

    @Test
    public void isCorrect_convertPoundToKilogram() {
        int pounds = 1;
        double kilogramsExpected = 0.453592;
        System.out.println(pounds * 0.453592);
        assert kilogramsExpected == converter.convertPoundToKilogram(pounds);
    }

    @Test
    public void isCorrect_convertPoundToGram() {
        int pounds = 1;
        double gramsExpected = 453.592;
        System.out.println(pounds * 453.592);
        assert gramsExpected == converter.convertPoundToGram(pounds);
    }

    @Test
    public void isCorrect_convertPoundToMilligram() {
        int pounds = 1;
        double milligramsExpected = 453592;
        System.out.println(pounds * 453592);
        assert milligramsExpected == converter.convertPoundToMilligram(pounds);
    }

    @Test
    public void isCorrect_convertPoundToMetricTon() {
        int pounds = 1;
        double metricTonsExpected = 4.53592E-4;
        System.out.println(pounds * 0.000453592);
        assert metricTonsExpected == converter.convertPoundToMetricTon(pounds);
    }

    @Test
    public void isCorrect_convertPoundToUSTon() {
        int pounds = 1;
        double usTonsExpected = 5.0E-4;
        System.out.println(pounds * 0.0005);
        assert usTonsExpected == converter.convertPoundToUSTon(pounds);
    }

    @Test
    public void isCorrect_convertPoundToImperialTon() {
        int pounds = 1;
        double imperialTonsExpected = 4.46429E-4;
        System.out.println(pounds * 0.000446429);
        assert imperialTonsExpected == converter.convertPoundToImperialTon(pounds);
    }

    @Test
    public void isCorrect_convertPoundToUSOunce() {
        int pounds = 1;
        double usOuncesExpected = 16;
        System.out.println(pounds * 16);
        assert usOuncesExpected == converter.convertPoundToUSOunce(pounds);
    }

    @Test
    public void isCorrect_convertPoundToImperialOunce() {
        int pounds = 1;
        double imperialOuncesExpected = 15.999;
        System.out.println(pounds * 15.999);
        assert imperialOuncesExpected == converter.convertPoundToImperialOunce(pounds);
    }

    @Test
    public void isCorrect_convertPoundToStone() {
        int pounds = 1;
        double stonesExpected = 0.0714286;
        System.out.println(pounds * 0.0714286);
        assert stonesExpected == converter.convertPoundToStone(pounds);
    }

    @Test
    public void isCorrect_convertPoundToCarat() {
        int pounds = 1;
        double caratsExpected = 2267.96;
        System.out.println(pounds * 2267.96);
        assert caratsExpected == converter.convertPoundToCarat(pounds);
    }

    @Test
    public void isCorrect_convertUSOunceToKilogram() {
        int usOunces = 1;
        double kilogramsExpected = 0.0283495;
        System.out.println(usOunces * 0.0283495);
        assert kilogramsExpected == converter.convertUSOunceToKilogram(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToGram() {
        int usOunces = 1;
        double gramsExpected = 28.3495;
        System.out.println(usOunces * 28.3495);
        assert gramsExpected == converter.convertUSOunceToGram(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToMilligram() {
        int usOunces = 1;
        double milligramsExpected = 28349.5;
        System.out.println(usOunces * 28349.5);
        assert milligramsExpected == converter.convertUSOunceToMilligram(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToMetricTon() {
        int usOunces = 1;
        double metricTonsExpected = 2.83495E-5;
        System.out.println(usOunces * 0.0000283495);
        assert metricTonsExpected == converter.convertUSOunceToMetricTon(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToUSTon() {
        int usOunces = 1;
        double usTonsExpected = 3.125E-5;
        System.out.println(usOunces * 0.00003125);
        assert usTonsExpected == converter.convertUSOunceToUSTon(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToImperialTon() {
        int usOunces = 1;
        double imperialTonsExpected = 2.79018E-5;
        System.out.println(usOunces * 0.0000279018);
        assert imperialTonsExpected == converter.convertUSOunceToImperialTon(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToPound() {
        int usOunces = 1;
        double poundsExpected = 0.0625;
        System.out.println(usOunces * 0.0625);
        assert poundsExpected == converter.convertUSOunceToPound(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToImperialOunce() {
        int usOunces = 1;
        double imperialOuncesExpected = 0.911458;
        System.out.println(usOunces * 0.911458);
        assert imperialOuncesExpected == converter.convertUSOunceToImperialOunce(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToStone() {
        int usOunces = 1;
        double stonesExpected = 0.00446429;
        System.out.println(usOunces * 0.00446429);
        assert stonesExpected == converter.convertUSOunceToStone(usOunces);
    }

    @Test
    public void isCorrect_convertUSOunceToCarat() {
        int usOunces = 1;
        double caratsExpected = 141.748;
        System.out.println(usOunces * 141.748);
        assert caratsExpected == converter.convertUSOunceToCarat(usOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToKilogram() {
        int imperialOunces = 1;
        double kilogramsExpected = 0.0283495;
        System.out.println(imperialOunces * 0.0283495);
        assert kilogramsExpected == converter.convertImperialOunceToKilogram(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToGram() {
        int imperialOunces = 1;
        double gramsExpected = 28.3495;
        System.out.println(imperialOunces * 28.3495);
        assert gramsExpected == converter.convertImperialOunceToGram(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToMilligram() {
        int imperialOunces = 1;
        double milligramsExpected = 28349.5;
        System.out.println(imperialOunces * 28349.5);
        assert milligramsExpected == converter.convertImperialOunceToMilligram(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToMetricTon() {
        int imperialOunces = 1;
        double metricTonsExpected = 2.83495E-5;
        System.out.println(imperialOunces * 0.0000283495);
        assert metricTonsExpected == converter.convertImperialOunceToMetricTon(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToUSTon() {
        int imperialOunces = 1;
        double usTonsExpected = 3.125E-5;
        System.out.println(imperialOunces * 0.00003125);
        assert usTonsExpected == converter.convertImperialOunceToUSTon(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToImperialTon() {
        int imperialOunces = 1;
        double imperialTonsExpected = 2.7902E-5;
        System.out.println(imperialOunces * 0.000027902);
        assert imperialTonsExpected == converter.convertImperialOunceToImperialTon(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToPound() {
        int imperialOunces = 1;
        double poundsExpected = 0.0625;
        System.out.println(imperialOunces * 0.0625);
        assert poundsExpected == converter.convertImperialOunceToPound(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToUSOunce() {
        int imperialOunces = 1;
        double usOuncesExpected = 1.04084;
        System.out.println(imperialOunces * 1.04084);
        assert usOuncesExpected == converter.convertImperialOunceToUSOunce(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToStone() {
        int imperialOunces = 1;
        double stonesExpected = 0.00446429;
        System.out.println(imperialOunces * 0.00446429);
        assert stonesExpected == converter.convertImperialOunceToStone(imperialOunces);
    }

    @Test
    public void isCorrect_convertImperialOunceToCarat() {
        int imperialOunces = 1;
        double caratsExpected = 141.748;
        System.out.println(imperialOunces * 141.748);
        assert caratsExpected == converter.convertImperialOunceToCarat(imperialOunces);
    }

    @Test
    public void isCorrect_convertStoneToKilogram() {
        int stones = 1;
        double kilogramsExpected = 6.35029;
        System.out.println(stones * 6.35029);
        assert kilogramsExpected == converter.convertStoneToKilogram(stones);
    }

    @Test
    public void isCorrect_convertStoneToGram() {
        int stones = 1;
        double gramsExpected = 6350.29;
        System.out.println(stones * 6350.29);
        assert gramsExpected == converter.convertStoneToGram(stones);
    }

    @Test
    public void isCorrect_convertStoneToMilligram() {
        int stones = 1;
        double milligramsExpected = 6350290;
        System.out.println(stones * 6350290);
        assert milligramsExpected == converter.convertStoneToMilligram(stones);
    }

    @Test
    public void isCorrect_convertStoneToMetricTon() {
        int stones = 1;
        double metricTonsExpected = 0.00635029;
        System.out.println(stones * 0.00635029);
        assert metricTonsExpected == converter.convertStoneToMetricTon(stones);
    }

    @Test
    public void isCorrect_convertStoneToUSTon() {
        int stones = 1;
        double usTonsExpected = 0.007;
        System.out.println(stones * 0.007);
        assert usTonsExpected == converter.convertStoneToUSTon(stones);
    }

    @Test
    public void isCorrect_convertStoneToImperialTon() {
        int stones = 1;
        double imperialTonsExpected = 0.00625;
        System.out.println(stones * 0.00625);
        assert imperialTonsExpected == converter.convertStoneToImperialTon(stones);
    }

    @Test
    public void isCorrect_convertStoneToPound() {
        int stones = 1;
        double poundsExpected = 14;
        System.out.println(stones * 14);
        assert poundsExpected == converter.convertStoneToPound(stones);
    }

    @Test
    public void isCorrect_convertStoneToUSOunce() {
        int stones = 1;
        double usOuncesExpected = 224;
        System.out.println(stones * 224);
        assert usOuncesExpected == converter.convertStoneToUSOunce(stones);
    }

    @Test
    public void isCorrect_convertStoneToImperialOunce() {
        int stones = 1;
        double imperialOuncesExpected = 224;
        System.out.println(stones * 224);
        assert imperialOuncesExpected == converter.convertStoneToImperialOunce(stones);
    }

    @Test
    public void isCorrect_convertStoneToCarat() {
        int stones = 1;
        double caratsExpected = 31751.5;
        System.out.println(stones * 31751.5);
        assert caratsExpected == converter.convertStoneToCarat(stones);
    }

    @Test
    public void isCorrect_convertCaratToKilogram() {
        int carats = 1;
        double kilogramsExpected = 2.0E-4;
        System.out.println(carats * 0.0002);
        assert kilogramsExpected == converter.convertCaratToKilogram(carats);
    }

    @Test
    public void isCorrect_convertCaratToGram() {
        int carats = 1;
        double gramsExpected = 0.2;
        System.out.println(carats * 0.2);
        assert gramsExpected == converter.convertCaratToGram(carats);
    }

    @Test
    public void isCorrect_convertCaratToMilligram() {
        int carats = 1;
        double milligramsExpected = 200;
        System.out.println(carats * 200);
        assert milligramsExpected == converter.convertCaratToMilligram(carats);
    }

    @Test
    public void isCorrect_convertCaratToMetricTon() {
        int carats = 1;
        double metricTonsExpected = 2.0E-7;
        System.out.println(carats * 0.0000002);
        assert metricTonsExpected == converter.convertCaratToMetricTon(carats);
    }

    @Test
    public void isCorrect_convertCaratToUSTon() {
        int carats = 1;
        double usTonsExpected = 2.20462E-7;
        System.out.println(carats * 0.000000220462);
        assert usTonsExpected == converter.convertCaratToUSTon(carats);
    }

    @Test
    public void isCorrect_convertCaratToImperialTon() {
        int carats = 1;
        double imperialTonsExpected = 1.96841E-7;
        System.out.println(carats * 0.000000196841);
        assert imperialTonsExpected == converter.convertCaratToImperialTon(carats);
    }

    @Test
    public void isCorrect_convertCaratToPound() {
        int carats = 1;
        double poundsExpected = 4.40925E-4;
        System.out.println(carats * 0.000440925);
        assert poundsExpected == converter.convertCaratToPound(carats);
    }

    @Test
    public void isCorrect_convertCaratToUSOunce() {
        int carats = 1;
        double usOuncesExpected = 0.00705479;
        System.out.println(carats * 0.00705479);
        assert usOuncesExpected == converter.convertCaratToUSOunce(carats);
    }

    @Test
    public void isCorrect_convertCaratToImperialOunce() {
        int carats = 1;
        double imperialOuncesExpected = 0.0070557;
        System.out.println(carats * 0.0070557);
        assert imperialOuncesExpected == converter.convertCaratToImperialOunce(carats);
    }

    @Test
    public void isCorrect_convertCaratToStone() {
        int carats = 1;
        double stonesExpected = 3.14961E-5;
        System.out.println(carats * 0.0000314961);
        assert stonesExpected == converter.convertCaratToStone(carats);
    }
}
