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

public class PowerConverterUnitTest {
    private final PowerConverter converter = new PowerConverter();

    @Test
    public void isCorrect_convertWattToKilowatt() {
        int watts = 1;
        double kilowattsExpected = 1000;
        assert kilowattsExpected == converter.convertWattToKilowatt(watts);
    }

    @Test
    public void isCorrect_convertWattToMegawatt() {
        int watts = 1;
        double megawattExpected = 1000000;
        assert megawattExpected == converter.convertWattToMegawatt(watts);
    }

    @Test
    public void isCorrect_convertWattToMetricHorsepower() {
        int watts = 1;
        double metricHorsepowerExpected = 735.49875;
        assert metricHorsepowerExpected == converter.convertWattToMetricHorsepower(watts);
    }

    @Test
    public void isCorrect_convertWattToImperialHorsepower() {
        int watts = 1;
        double imperialHorsepowerExpected = 745.69987;
        assert imperialHorsepowerExpected == converter.convertWattToImperialHorsepower(watts);
    }

    @Test
    public void isCorrect_convertWattToVoltAmpere() {
        int watts = 1;
        double voltAmperesExpected = 1;
        assert voltAmperesExpected == converter.convertWattToVoltAmpere(watts);
    }

    @Test
    public void isCorrect_convertWattToKilovoltAmpere() {
        int watts = 1;
        double kilovoltAmperesExpected = 1000;
        assert kilovoltAmperesExpected == converter.convertWattToKilovoltAmpere(watts);
    }

    @Test
    public void isCorrect_convertKilowattToWatt() {
        int kilowatts = 1;
        double wattsExpected = 0.001;
        assert wattsExpected == converter.convertKilowattToWatt(kilowatts);
    }

    @Test
    public void isCorrect_convertKilowattToMegawatt() {
        int kilowatts = 1;
        double megawattsExpected = 1000;
        assert megawattsExpected == converter.convertKilowattToMegawatt(kilowatts);
    }

    @Test
    public void isCorrect_convertKilowattToMetricHorsepower() {
        int kilowatts = 1;
        double metricHorsepowerExpected = 0.73549875;
        assert metricHorsepowerExpected == converter.convertKilowattToMetricHorsepower(kilowatts);
    }

    @Test
    public void isCorrect_convertKilowattToImperialHorsepower() {
        int kilowatts = 1;
        double imperialHorsepowerExpected = 0.74569987;
        assert imperialHorsepowerExpected == converter.convertKilowattToImperialHorsepower(kilowatts);
    }

    @Test
    public void isCorrect_convertKilowattToVoltAmpere() {
        int kilowatts = 1;
        double voltAmperesExpected = 0.001;
        assert voltAmperesExpected == converter.convertKilowattToVoltAmpere(kilowatts);
    }

    @Test
    public void isCorrect_convertKilowattToKilovoltAmpere() {
        int kilowatts = 1;
        double kilovoltAmperesExpected = 1;
        assert kilovoltAmperesExpected == converter.convertKilowattToKilovoltAmpere(kilowatts);
    }

    @Test
    public void isCorrect_convertMegawattToWatt() {
        int megawatts = 1;
        double wattsExpected = 1.0E-6;
        assert wattsExpected == converter.convertMegawattToWatt(megawatts);
    }

    @Test
    public void isCorrect_convertMegawattToKilowatt() {
        int megawatts = 1;
        double kilowattsExpected = 0.001;
        assert kilowattsExpected == converter.convertMegawattToKilowatt(megawatts);
    }

    @Test
    public void isCorrect_convertMegawattToMetricHorsepower() {
        int megawatts = 1;
        double metricHorsepowerExpected = 7.3549875E-4;
        assert metricHorsepowerExpected == converter.convertMegawattToMetricHorsepower(megawatts);
    }

    @Test
    public void isCorrect_convertMegawattToImperialHorsepower() {
        int megawatts = 1;
        double imperialHorsepowerExpected = 7.4569987E-4;
        assert imperialHorsepowerExpected == converter.convertMegawattToImperialHorsepower(megawatts);
    }

    @Test
    public void isCorrect_convertMegawattToVoltAmpere() {
        int megawatts = 1;
        double voltAmperesExpected = 1.0E-6;
        assert voltAmperesExpected == converter.convertMegawattToVoltAmpere(megawatts);
    }

    @Test
    public void isCorrect_convertMegawattToKilovoltAmpere() {
        int megawatts = 1;
        double kilovoltAmperesExpected = 0.001;
        assert kilovoltAmperesExpected == converter.convertMegawattToKilovoltAmpere(megawatts);
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToWatt() {
        int metricHorsepower = 1;
        double wattsExpected = 0.0013596216;
        assert wattsExpected == converter.convertMetricHorsepowerToWatt(metricHorsepower);
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToKilowatt() {
        int metricHorsepower = 1;
        double kilowattsExpected = 1.3596216;
        assert kilowattsExpected == converter.convertMetricHorsepowerToKilowatt(metricHorsepower);
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToMegawatt() {
        int metricHorsepower = 1;
        double megawattsExpected = 1359.6216;
        assert megawattsExpected == converter.convertMetricHorsepowerToMegawatt(metricHorsepower);
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToImperialHorsepower() {
        int metricHorsepower = 1;
        double imperialHorsepowerExpected = 1.0138697;
        assert imperialHorsepowerExpected == converter.convertMetricHorsepowerToImperialHorsepower(metricHorsepower);
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToVoltAmpere() {
        int metricHorsepower = 1;
        double voltAmperesExpected = 0.0013596216;
        assert voltAmperesExpected == converter.convertMetricHorsepowerToVoltAmpere(metricHorsepower);
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToKilovoltAmpere() {
        int metricHorsepower = 1;
        double kilovoltAmperesExpected = 1.3596216;
        assert kilovoltAmperesExpected == converter.convertMetricHorsepowerToKilovoltAmpere(metricHorsepower);
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToWatt() {
        int imperialHorsepower = 1;
        double wattsExpected = 0.0013410221;
        assert wattsExpected == converter.convertImperialHorsepowerToWatt(imperialHorsepower);
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToKilowatt() {
        int imperialHorsepower = 1;
        double kilowattsExpected = 1.3410221;
        assert kilowattsExpected == converter.convertImperialHorsepowerToKilowatt(imperialHorsepower);
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToMegawatt() {
        int imperialHorsepower = 1;
        double megawattsExpected = 1341.0221;
        assert megawattsExpected == converter.convertImperialHorsepowerToMegawatt(imperialHorsepower);
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToMetricHorsepower() {
        int imperialHorsepower = 1;
        double metricHorsepowerExpected = 0.9863201;
        assert metricHorsepowerExpected == converter.convertImperialHorsepowerToMetricHorsepower(imperialHorsepower);
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToVoltAmpere() {
        int imperialHorsepower = 1;
        double voltAmperesExpected = 0.0013410221;
        assert voltAmperesExpected == converter.convertImperialHorsepowerToVoltAmpere(imperialHorsepower);
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToKilovoltAmpere() {
        int imperialHorsepower = 1;
        double kilovoltAmperesExpected = 1.3410221;
        assert kilovoltAmperesExpected == converter.convertImperialHorsepowerToKilovoltAmpere(imperialHorsepower);
    }

    @Test
    public void isCorrect_convertVoltAmpereToWatt() {
        int voltAmperes = 1;
        double wattsExpected = 1;
        assert wattsExpected == converter.convertVoltAmpereToWatt(voltAmperes);
    }

    @Test
    public void isCorrect_convertVoltAmpereToKilowatt() {
        int voltAmperes = 1;
        double kilowattsExpected = 1000;
        assert kilowattsExpected == converter.convertVoltAmpereToKilowatt(voltAmperes);
    }

    @Test
    public void isCorrect_convertVoltAmpereToMegawatt() {
        int voltAmperes = 1;
        double megawattsExpected = 1000000;
        assert megawattsExpected == converter.convertVoltAmpereToMegawatt(voltAmperes);
    }

    @Test
    public void isCorrect_convertVoltAmpereToMetricHorsepower() {
        int voltAmperes = 1;
        double metricHorsepowerExpected = 735.49875;
        assert metricHorsepowerExpected == converter.convertVoltAmpereToMetricHorsepower(voltAmperes);
    }

    @Test
    public void isCorrect_convertVoltAmpereToImperialHorsepower() {
        int voltAmperes = 1;
        double imperialHorsepowerExpected = 745.69987;
        assert imperialHorsepowerExpected == converter.convertVoltAmpereToImperialHorsepower(voltAmperes);
    }

    @Test
    public void isCorrect_convertVoltAmpereToKilovoltAmpere() {
        int voltAmperes = 1;
        double imperialHorsepowerExpected = 745.69987;
        assert imperialHorsepowerExpected == converter.convertVoltAmpereToKilovoltAmpere(voltAmperes);
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToWatt() {
        int kilovoltAmperes = 1;
        double wattsExpected = 0.001;
        assert wattsExpected == converter.convertKilovoltAmpereToWatt(kilovoltAmperes);
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToKilowatt() {
        int kilovoltAmperes = 1;
        double kilowattsExpected = 1;
        assert kilowattsExpected == converter.convertKilovoltAmpereToKilowatt(kilovoltAmperes);
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToMegawatt() {
        int kilovoltAmperes = 1;
        double megawattsExpected = 1000;
        assert megawattsExpected == converter.convertKilovoltAmpereToMegawatt(kilovoltAmperes);
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToMetricHorsepower() {
        int kilovoltAmperes = 1;
        double metricHorsepowerExpected = 0.73549875;
        assert metricHorsepowerExpected == converter.convertKilovoltAmpereToMetricHorsepower(kilovoltAmperes);
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToImperialHorsepower() {
        int kilovoltAmperes = 1;
        double imperialHorsepowerExpected = 0.74569987;
        assert imperialHorsepowerExpected == converter.convertKilovoltAmpereToImperialHorsepower(kilovoltAmperes);
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToVoltAmpere() {
        int kilovoltAmperes = 1;
        double voltAmperesExpected = 0.001;
        assert voltAmperesExpected == converter.convertKilovoltAmpereToVoltAmpere(kilovoltAmperes);
    }
}
