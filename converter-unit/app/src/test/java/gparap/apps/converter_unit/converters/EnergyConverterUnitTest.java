/*
 * Copyright 2025 gparap
 *
 * Licensed under the Apache License Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing software
 * distributed under the License is distributed on an "AS IS" BASIS
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.converter_unit.converters;

import org.junit.Test;

public class EnergyConverterUnitTest {
    private final EnergyConverter converter = new EnergyConverter();

    @Test
    public void isCorrect_convertJouleToCalorie() {
        int joules = 1;
        double caloriesExpected = 0.2390057361376673;
        assert caloriesExpected == converter.convertJouleToCalorie(joules);
    }

    @Test
    public void isCorrect_convertJouleToKilocalorie() {
        int joules = 1;
        double kilocalorieExpected = 2.390057361376673E-4;
        assert kilocalorieExpected == converter.convertJouleToKilocalorie(joules);
    }

    @Test
    public void isCorrect_convertJouleToElectronvolt() {
        int joules = 1;
        double electronvoltExpected = 6.241495961752112E-20;
        assert electronvoltExpected == converter.convertJouleToElectronvolt(joules);
    }

    @Test
    public void isCorrect_convertJouleToWattHour() {
        int joules = 1;
        double wattHoursExpected = 2.777777777777778E-4;
        assert wattHoursExpected == converter.convertJouleToWattHour(joules);
    }

    @Test
    public void isCorrect_convertJouleToBritishThermalUnit() {
        int joules = 1;
        double britishThermalUnitExpected = 9.478133944988911E-4;
        assert britishThermalUnitExpected == converter.convertJouleToBritishThermalUnit(joules);
    }

    @Test
    public void isCorrect_convertJouleToKilowattHour() {
        int joules = 1;
        double kilowattHoursExpected = 3600000;
        assert kilowattHoursExpected == converter.convertJouleToKilowattHour(joules);
    }

    @Test
    public void isCorrect_convertCalorieToJoule() {
        int calories = 1;
        double joulesExpected = 4.184;
        assert joulesExpected == converter.convertCalorieToJoule(calories);
    }

    @Test
    public void isCorrect_convertCalorieToKilocalorie() {
        int calories = 1;
        double kiloCaloriesExpected = 0.001;
        assert kiloCaloriesExpected == converter.convertCalorieToKilocalorie(calories);
    }

    @Test
    public void isCorrect_convertCalorieToElectronvolt() {
        int calories = 1;
        double electronvoltExpected = 2.611441910397084E-19;
        assert electronvoltExpected == converter.convertCalorieToElectronvolt(calories);
    }

    @Test
    public void isCorrect_convertCalorieToWattHour() {
        int calories = 1;
        double wattHoursExpected = 0.0011622217495853772;
        assert wattHoursExpected == converter.convertCalorieToWattHour(calories);
    }

    @Test
    public void isCorrect_convertCalorieToBritishThermalUnit() {
        int calories = 1;
        double britishThermalUnitsExpected = 0.003965673133357656;
        assert britishThermalUnitsExpected == converter.convertCalorieToBritishThermalUnit(calories);
    }

    @Test
    public void isCorrect_convertCalorieToKilowattHour() {
        int calories = 1;
        double kilowattHoursExpected = 1.1622217495853774E-6;
        assert kilowattHoursExpected == converter.convertCalorieToKilowattHour(calories);
    }

    @Test
    public void isCorrect_convertKilocalorieToJoule() {
        int kilocalories = 1;
        double joulesExpected = 4184.0;
        assert joulesExpected == converter.convertKilocalorieToJoule(kilocalories);
    }

    @Test
    public void isCorrect_convertKilocalorieToCalorie() {
        int kilocalories = 1;
        double caloriesExpected = 0.001;
        assert caloriesExpected == converter.convertKilocalorieToCalorie(kilocalories);
    }

    @Test
    public void isCorrect_convertKilocalorieToElectronvolt() {
        int kilocalories = 1;
        double electronvoltExpected = 2.611441910397084E-16;
        assert electronvoltExpected == converter.convertKilocalorieToElectronvolt(kilocalories);
    }

    @Test
    public void isCorrect_convertKilocalorieToWattHour() {
        int kilocalories = 1;
        double wattHoursExpected = 0.0011622217495853772;
        assert wattHoursExpected == converter.convertKilocalorieToWattHour(kilocalories);
    }

    @Test
    public void isCorrect_convertKilocalorieToBritishThermalUnit() {
        int kilocalories = 1;
        double britishThermalUnitExpected = 0.947813394498891;
        assert britishThermalUnitExpected == converter.convertKilocalorieToBritishThermalUnit(kilocalories);
    }

    @Test
    public void isCorrect_convertKilocalorieToKilowattHour() {
        int kilocalories = 1;
        double kilowattHoursExpected = 1.1622217495853774E-6;
        assert kilowattHoursExpected == converter.convertKilocalorieToKilowattHour(kilocalories);
    }

    @Test
    public void isCorrect_convertElectronvoltToJoule() {
        int electronvolts = 1;
        double joulesExpected = 1.60218E-19;
        assert joulesExpected == converter.convertElectronvoltToJoule(electronvolts);
    }

    @Test
    public void isCorrect_convertElectronvoltToCalorie() {
        int electronvolts = 1;
        double caloriesExpected = 6.135231243785849E-20;
        assert caloriesExpected == converter.convertElectronvoltToCalorie(electronvolts);
    }

    @Test
    public void isCorrect_convertElectronvoltToKilocalorie() {
        int electronvolts = 1;
        double kilocalorieExpected = 3.829302103250478E-23;
        assert kilocalorieExpected == converter.convertElectronvoltToKilocalorie(electronvolts);
    }

    @Test
    public void isCorrect_convertElectronvoltToWattHour() {
        int electronvolts = 1;
        double wattHoursExpected = 4.4505E-23;
        assert wattHoursExpected == converter.convertElectronvoltToWattHour(electronvolts);
    }

    @Test
    public void isCorrect_convertElectronvoltToBritishThermalUnit() {
        int electronvolts = 1;
        double britishThermalUnitExpected = 1.5185676643982333E-22;
        assert britishThermalUnitExpected == converter.convertElectronvoltToBritishThermalUnit(electronvolts);
    }

    @Test
    public void isCorrect_convertElectronvoltToKilowattHour() {
        int electronvolts = 1;
        double kilowattHoursExpected = 4.4504999999999997E-26;
        assert kilowattHoursExpected == converter.convertElectronvoltToKilowattHour(electronvolts);
    }

    @Test
    public void isCorrect_convertWattHourToJoule() {
        int wattHours = 1;
        double joulesExpected = 3600.0;
        assert joulesExpected == converter.convertWattHourToJoule(wattHours);
    }

    @Test
    public void isCorrect_convertWattHourToCalorie() {
        int wattHours = 1;
        double caloriesExpected = 860.421;
        assert caloriesExpected == converter.convertWattHourToCalorie(wattHours);
    }

    @Test
    public void isCorrect_convertWattHourToKilocalorie() {
        int wattHours = 1;
        double kilocalorieExpected = 0.0011622217495853772;
        assert kilocalorieExpected == converter.convertWattHourToKilocalorie(wattHours);
    }

    @Test
    public void isCorrect_convertWattHourToElectronvolt() {
        int wattHours = 1;
        double electronvoltExpected = 2.2469385462307605E-16;
        assert electronvoltExpected == converter.convertWattHourToElectronvolt(wattHours);
    }

    @Test
    public void isCorrect_convertWattHourToBritishThermalUnit() {
        int wattHours = 1;
        double britishThermalUnitExpected = 0.947813394498891;
        assert britishThermalUnitExpected == converter.convertWattHourToBritishThermalUnit(wattHours);
    }

    @Test
    public void isCorrect_convertWattHourToKilowattHour() {
        int wattHours = 1;
        double kilowattHoursExpected = 0.001;
        assert kilowattHoursExpected == converter.convertWattHourToKilowattHour(wattHours);
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToJoule() {
        int britishThermalUnit = 1;
        double joulesExpected = 1055.06;
        assert joulesExpected == converter.convertBritishThermalUnitToJoule(britishThermalUnit);
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToCalorie() {
        int britishThermalUnit = 1;
        double calories = 252.164;
        assert calories == converter.convertBritishThermalUnitToCalorie(britishThermalUnit);
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToKilocalorie() {
        int britishThermalUnit = 1;
        double kilocalorieExpected = 0.947813394498891;
        assert kilocalorieExpected == converter.convertBritishThermalUnitToKilocalorie(britishThermalUnit);
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToElectronvolt() {
        int britishThermalUnit = 1;
        double electronvoltExpected = 6.585152729406184E-17;
        assert electronvoltExpected == converter.convertBritishThermalUnitToElectronvolt(britishThermalUnit);
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToWattHour() {
        int britishThermalUnit = 1;
        double wattHoursExpected = 0.2930712104427134;
        assert wattHoursExpected == converter.convertBritishThermalUnitToWattHour(britishThermalUnit);
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToKilowattHour() {
        int britishThermalUnit = 1;
        double kilowattHoursExpected = 2.930712104427134E-4;
        assert kilowattHoursExpected == converter.convertBritishThermalUnitToKilowattHour(britishThermalUnit);
    }

    @Test
    public void isCorrect_convertKilowattHourToJoule() {
        int kilowattHours = 1;
        double joulesExpected = 3600000.0;
        assert joulesExpected == converter.convertKilowattHourToJoule(kilowattHours);
    }

    @Test
    public void isCorrect_convertKilowattHourToCalorie() {
        int kilowattHours = 1;
        double caloriesExpected = 860421.0;
        assert caloriesExpected == converter.convertKilowattHourToCalorie(kilowattHours);
    }

    @Test
    public void isCorrect_convertKilowattHourToKilocalorie() {
        int kilowattHours = 1;
        double kilocalorieExpected = 860.421;
        assert kilocalorieExpected == converter.convertKilowattHourToKilocalorie(kilowattHours);
    }

    @Test
    public void isCorrect_convertKilowattHourToElectronvolt() {
        int kilowattHours = 1;
        double electronvoltExpected = 2.246938546230761E-13;
        assert electronvoltExpected == converter.convertKilowattHourToElectronvolt(kilowattHours);
    }

    @Test
    public void isCorrect_convertKilowattHourToWattHour() {
        int kilowattHours = 1;
        double wattHoursExpected = 1000.0;
        assert wattHoursExpected == converter.convertKilowattHourToWattHour(kilowattHours);
    }

    @Test
    public void isCorrect_convertKilowattHourToBritishThermalUnit() {
        int kilowattHours = 1;
        double britishThermalUnitExpected = 3412.14;
        assert britishThermalUnitExpected == converter.convertKilowattHourToBritishThermalUnit(kilowattHours);
    }
}
