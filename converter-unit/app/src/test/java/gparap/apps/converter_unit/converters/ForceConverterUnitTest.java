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

public class ForceConverterUnitTest {
    private final ForceConverter converter = new ForceConverter();

    @Test
    public void isCorrect_convertNewtonToPoundForce() {
        int newtons = 1;
        double poundsForceExpected = 0.2248090247334889;
        assert poundsForceExpected == converter.convertNewtonToPoundForce(newtons);
    }

    @Test
    public void isCorrect_convertNewtonToKilogramForce() {
        int newtons = 1;
        double kilogramsForceExpected = 0.10197162129779283;
        assert kilogramsForceExpected == converter.convertNewtonToKilogramForce(newtons);
    }

    @Test
    public void isCorrect_convertNewtonToDyne() {
        int newtons = 1;
        int dynesExpected = 100000;
        assert dynesExpected == converter.convertNewtonToDyne(newtons);
    }

    @Test
    public void isCorrect_convertNewtonToMetricTonForce() {
        int newtons = 1;
        double metricTonForceExpected = 1.0197162129779283E-4;
        assert metricTonForceExpected == converter.convertNewtonToMetricTonForce(newtons);
    }

    @Test
    public void isCorrect_convertNewtonToUSTonForce() {
        int newtons = 1;
        double usTonForceExpected = 1.1240451236674444E-4;
        assert usTonForceExpected == converter.convertNewtonToUSTonForce(newtons);
    }

    @Test
    public void isCorrect_convertPoundForceToNewton() {
        int poundsForce = 1;
        double newtonsExpected = 4.44822;
        assert newtonsExpected == converter.convertPoundForceToNewton(poundsForce);
    }

    @Test
    public void isCorrect_convertPoundForceToKilogramForce() {
        int poundsForce = 1;
        double kilogramsForceExpected = 0.453592;
        assert kilogramsForceExpected == converter.convertPoundForceToKilogramForce(poundsForce);
    }

    @Test
    public void isCorrect_convertPoundForceToDyne() {
        int poundsForce = 1;
        int dynesExpected = 444822;
        assert dynesExpected == converter.convertPoundForceToDyne(poundsForce);
    }

    @Test
    public void isCorrect_convertPoundForceToMetricTonForce() {
        int poundsForce = 1;
        double metricTonForceExpected = 4.53592E-4;
        assert metricTonForceExpected == converter.convertPoundForceToMetricTonForce(poundsForce);
    }

    @Test
    public void isCorrect_convertPoundForceToUSTonForce() {
        int poundsForce = 1;
        double usTonForceExpected = 5.0E-4;
        assert usTonForceExpected == converter.convertPoundForceToUSTonForce(poundsForce);
    }

    @Test
    public void isCorrect_convertKilogramForceToNewton() {
        int kilogramsForce = 1;
        double newtonsExpected = 9.80665;
        assert newtonsExpected == converter.convertKilogramForceToNewton(kilogramsForce);
    }

    @Test
    public void isCorrect_convertKilogramForceToPoundForce() {
        int kilogramsForce = 1;
        double poundsForceExpected = 2.2046244201837775;
        assert poundsForceExpected == converter.convertKilogramForceToPoundForce(kilogramsForce);
    }

    @Test
    public void isCorrect_convertKilogramForceToDyne() {
        int kilogramsForce = 1;
        int dynesExpected = 980665;
        assert dynesExpected == converter.convertKilogramForceToDyne(kilogramsForce);
    }

    @Test
    public void isCorrect_convertKilogramForceToMetricTonForce() {
        int kilogramsForce = 1;
        double metricTonForceExpected = 0.001;
        assert metricTonForceExpected == converter.convertKilogramForceToMetricTonForce(kilogramsForce);
    }

    @Test
    public void isCorrect_convertKilogramForceToUSTonForce() {
        int kilogramsForce = 1;
        double usTonForceExpected = 0.001102311359527999;
        assert usTonForceExpected == converter.convertKilogramForceToUSTonForce(kilogramsForce);
    }

    @Test
    public void isCorrect_convertDyneToNewton() {
        int dynes = 1;
        double newtonsExpected = 1.0E-5;
        assert newtonsExpected == converter.convertDyneToNewton(dynes);
    }

    @Test
    public void isCorrect_convertDyneToPoundForce() {
        int dynes = 1;
        double poundsForceExpected = 2.248090247334889E-6;
        assert poundsForceExpected == converter.convertDyneToPoundForce(dynes);
    }

    @Test
    public void isCorrect_convertDyneToKilogramForce() {
        int dynes = 1;
        double kilogramsForceExpected = 1.0197162129779282E-6;
        assert kilogramsForceExpected == converter.convertDyneToKilogramForce(dynes);
    }

    @Test
    public void isCorrect_convertDyneToMetricTonForce() {
        int dynes = 1;
        double metricTonForceExpected = 1.0197162129779283E-9;
        assert metricTonForceExpected == converter.convertDyneToMetricTonForce(dynes);
    }

    @Test
    public void isCorrect_convertDyneToUSTonForce() {
        int dynes = 1;
        double usTonForceExpected = 1.1240451236674445E-9;
        assert usTonForceExpected == converter.convertDyneToUSTonForce(dynes);
    }

    @Test
    public void isCorrect_convertMetricTonForceToNewton() {
        int metricTonsForce = 1;
        double newtonsExpected = 9806.65;
        assert newtonsExpected == converter.convertMetricTonForceToNewton(metricTonsForce);
    }

    @Test
    public void isCorrect_convertMetricTonForceToPoundForce() {
        int metricTonsForce = 1;
        double poundsForceExpected = 2204.6244201837776;
        assert poundsForceExpected == converter.convertMetricTonForceToPoundForce(metricTonsForce);
    }

    @Test
    public void isCorrect_convertMetricTonForceToKilogramForce() {
        int metricTonsForce = 1;
        int kilogramsForceExpected = 1000;
        assert kilogramsForceExpected == converter.convertMetricTonForceToKilogramForce(metricTonsForce);
    }

    @Test
    public void isCorrect_convertMetricTonForceToDyne() {
        int metricTonsForce = 1;
        double dynesExpected = 9.80665E8;
        assert dynesExpected == converter.convertMetricTonForceToDyne(metricTonsForce);
    }

    @Test
    public void isCorrect_convertMetricTonForceToUSTonForce() {
        int metricTonsForce = 1;
        double usTonForceExpected = 1.10231;
        assert usTonForceExpected == converter.convertMetricTonForceToUSTonForce(metricTonsForce);
    }

    @Test
    public void isCorrect_convertUSTonForceToNewton() {
        int usTonsForce = 1;
        double newtonsExpected = 8896.44;
        assert newtonsExpected == converter.convertUSTonForceToNewton(usTonsForce);
    }

    @Test
    public void isCorrect_convertUSTonForceToPoundForce() {
        int usTonsForce = 1;
        int poundsForceExpected = 2000;
        assert poundsForceExpected == converter.convertUSTonForceToPoundForce(usTonsForce);
    }

    @Test
    public void isCorrect_convertUSTonForceToKilogramForce() {
        int usTonsForce = 1;
        double kilogramsForceExpected = 907.1847;
        assert kilogramsForceExpected == converter.convertUSTonForceToKilogramForce(usTonsForce);
    }

    @Test
    public void isCorrect_convertUSTonForceToDyne() {
        int usTonsForce = 1;
        double dynesExpected = 8.89644E8;
        assert dynesExpected == converter.convertUSTonForceToDyne(usTonsForce);
    }

    @Test
    public void isCorrect_convertUSTonForceToMetricTonForce() {
        int usTonsForce = 1;
        double metricTonForceExpected = 0.9071858188712795;
        assert metricTonForceExpected == converter.convertUSTonForceToMetricTonForce(usTonsForce);
    }
}
