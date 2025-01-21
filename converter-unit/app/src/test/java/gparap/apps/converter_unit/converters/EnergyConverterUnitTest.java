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
    @Test
    public void isCorrect_convertJouleToCalorie() {
        int joules = 1;
        double caloriesExpected = 0.2390057361376673;
        assert caloriesExpected == joules / 4.184;
    }
    @Test
    public void isCorrect_convertJouleToKilocalorie() {
        int joules = 1;
        double kilocalorieExpected = 2.390057361376673E-4;
        assert kilocalorieExpected == joules / (double)4184;
    }
    @Test
    public void isCorrect_convertJouleToElectronvolt() {
        int joules = 1;
        double electronvoltExpected = 6.241495961752112E-20;
        assert electronvoltExpected == joules / 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertJouleToWattHour() {
        int joules = 1;
        double wattHoursExpected = 2.777777777777778E-4;
        assert wattHoursExpected == joules / (double)3600;
    }
    @Test
    public void isCorrect_convertJouleToBritishThermalUnit() {
        int joules = 1;
        double britishThermalUnitExpected = 9.478133944988911E-4;
        assert britishThermalUnitExpected == joules / 1055.06;
    }
    @Test
    public void isCorrect_convertJouleToKilowattHour() {
        int joules = 1;
        double kilowattHoursExpected = 3600000;
        assert kilowattHoursExpected == joules * 3600000;
    }

    @Test
    public void isCorrect_convertCalorieToJoule() {
        int calories = 1;
        double joulesExpected = 4.184;
        assert joulesExpected == calories * 4.184;
    }
    @Test
    public void isCorrect_convertCalorieToKilocalorie() {
        int calories = 1;
        double kiloCaloriesExpected = 0.001;
        assert kiloCaloriesExpected == calories / (double)1000;
    }
    @Test
    public void isCorrect_convertCalorieToElectronvolt() {
        int calories = 1;
        double electronvoltExpected = 2.611441910397084E-19;
        assert electronvoltExpected == calories * 4.184 / 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertCalorieToWattHour() {
        int calories = 1;
        double wattHoursExpected = 0.0011622217495853772;
        assert wattHoursExpected == calories / 860.421;
    }
    @Test
    public void isCorrect_convertCalorieToBritishThermalUnit() {
        int calories = 1;
        double britishThermalUnitsExpected = 0.003965673133357656;
        assert britishThermalUnitsExpected == calories / 252.164;
    }
    @Test
    public void isCorrect_convertCalorieToKilowattHour() {
        int calories = 1;
        double kilowattHoursExpected = 1.1622217495853774E-6;
        assert kilowattHoursExpected == calories / (double)860421;
    }

    @Test
    public void isCorrect_convertKilocalorieToJoule() {
        int kilocalories = 1;
        double joulesExpected = 4184;
        assert joulesExpected == kilocalories * 4184;
    }
    @Test
    public void isCorrect_convertKilocalorieToCalorie() {
        int kilocalories = 1;
        double caloriesExpected = 0.001;
        assert caloriesExpected == kilocalories / (double)1000;
    }
    @Test
    public void isCorrect_convertKilocalorieToElectronvolt() {
        int kilocalories = 1;
        double electronvoltExpected = 2.611441910397084E-16;
        assert electronvoltExpected == kilocalories * 4184 / 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertKilocalorieToWattHour() {
        int kilocalories = 1;
        double wattHoursExpected = 0.0011622217495853772;
        assert wattHoursExpected == kilocalories / 860.421;
    }
    @Test
    public void isCorrect_convertKilocalorieToBritishThermalUnit() {
        int kilocalories = 1;
        double britishThermalUnitExpected = 0.947813394498891;
        assert britishThermalUnitExpected == kilocalories / 1.05506;
    }
    @Test
    public void isCorrect_convertKilocalorieToKilowattHour() {
        int kilocalories = 1;
        double kilowattHoursExpected = 1.1622217495853774E-6;
        assert kilowattHoursExpected == kilocalories / (double)860421;
    }

    @Test
    public void isCorrect_convertElectronvoltToJoule() {
        int electronvolts = 1;
        double joulesExpected = 1.60218E-19;
        assert joulesExpected == electronvolts * 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertElectronvoltToCalorie() {
        int electronvolts = 1;
        double caloriesExpected = 6.135231243785849E-20;
        assert caloriesExpected == electronvolts * 1.60218 * 1.60218 * 1.0e-19 / 4.184;
    }
    @Test
    public void isCorrect_convertElectronvoltToKilocalorie() {
        int electronvolts = 1;
        double kilocalorieExpected = 3.829302103250478E-23;
        assert kilocalorieExpected == electronvolts * 1.60218 * 1.0e-19 / 4184;
    }
    @Test
    public void isCorrect_convertElectronvoltToWattHour() {
        int electronvolts = 1;
        double wattHoursExpected = 4.4505E-23;
        assert wattHoursExpected == electronvolts * 1.60218 * 1.0e-19 / 3600;
    }
    @Test
    public void isCorrect_convertElectronvoltToBritishThermalUnit() {
        int electronvolts = 1;
        double britishThermalUnitExpected = 1.5185676643982333E-22;
        assert britishThermalUnitExpected == electronvolts * 1.60218 * 1.0e-19 / 1055.06;
    }
    @Test
    public void isCorrect_convertElectronvoltToKilowattHour() {
        int electronvolts = 1;
        double kilowattHoursExpected = 4.4504999999999997E-26;
        assert kilowattHoursExpected == electronvolts * 1.60218 * 1.0e-19 / 3600000;
    }

    @Test
    public void isCorrect_convertWattHourToJoule() {
        int wattHours = 1;
        double joulesExpected = 3600;
        assert joulesExpected == wattHours * 3600;
    }
    @Test
    public void isCorrect_convertWattHourToCalorie() {
        int wattHours = 1;
        double caloriesExpected = 860.421;
        assert caloriesExpected == wattHours * 860.421;
    }
    @Test
    public void isCorrect_convertWattHourToKilocalorie() {
        int wattHours = 1;
        double kilocalorieExpected = 0.0011622217495853772;
        assert kilocalorieExpected == wattHours / 860.421;
    }
    @Test
    public void isCorrect_convertWattHourToElectronvolt() {
        int wattHours = 1;
        double electronvoltExpected = 2.2469385462307605E-16;
        assert electronvoltExpected == wattHours * 3600 / 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertWattHourToBritishThermalUnit() {
        int wattHours = 1;
        double britishThermalUnitExpected = 0.947813394498891;
        assert britishThermalUnitExpected == wattHours / 1.05506;
    }
    @Test
    public void isCorrect_convertWattHourToKilowattHour() {
        int wattHours = 1;
        double kilowattHoursExpected = 0.001;
        assert kilowattHoursExpected == wattHours / (double)1000;
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToJoule() {
        int britishThermalUnit = 1;
        double joulesExpected = 1055.06;
        assert joulesExpected == britishThermalUnit * 1055.06;
    }
    @Test
    public void isCorrect_convertBritishThermalUnitToCalorie() {
        int britishThermalUnit = 1;
        double calories = 252.164;
        assert calories == britishThermalUnit * 252.164;
    }
    @Test
    public void isCorrect_convertBritishThermalUnitToKilocalorie() {
        int britishThermalUnit = 1;
        double kilocalorieExpected = 0.947813394498891;
        assert kilocalorieExpected == britishThermalUnit / 1.05506;
    }
    @Test
    public void isCorrect_convertBritishThermalUnitToElectronvolt() {
        int britishThermalUnit = 1;
        double electronvoltExpected = 6.585152729406184E-17;
        assert electronvoltExpected == britishThermalUnit * 1055.06 / 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertBritishThermalUnitToWattHour() {
        int britishThermalUnit = 1;
        double wattHoursExpected = 0.2930712104427134;
        assert wattHoursExpected == britishThermalUnit / 3.41214;
    }
    @Test
    public void isCorrect_convertBritishThermalUnitToKilowattHour() {
        int britishThermalUnit = 1;
        double kilowattHoursExpected = 2.930712104427134E-4;
        assert kilowattHoursExpected == britishThermalUnit / 3412.14;
    }

    @Test
    public void isCorrect_convertKilowattHourToJoule() {
        int kilowattHours = 1;
        double joulesExpected = 3600000;
        assert joulesExpected == kilowattHours * 3600000;
    }
    @Test
    public void isCorrect_convertKilowattHourToCalorie() {
        int kilowattHours = 1;
        double caloriesExpected = 860421;
        assert caloriesExpected == kilowattHours * 860421;
    }
    @Test
    public void isCorrect_convertKilowattHourToKilocalorie() {
        int kilowattHours = 1;
        double kilocalorieExpected = 860.421;
        assert kilocalorieExpected == kilowattHours * 860.421;
    }
    @Test
    public void isCorrect_convertKilowattHourToElectronvolt() {
        int kilowattHours = 1;
        double electronvoltExpected = 2.246938546230761E-13;
        assert electronvoltExpected == kilowattHours * 3600000 / 1.60218 * 1.0e-19;
    }
    @Test
    public void isCorrect_convertKilowattHourToWattHour() {
        int kilowattHours = 1;
        double wattHoursExpected = 1000;
        assert wattHoursExpected == kilowattHours * 1000;
    }
    @Test
    public void isCorrect_convertKilowattHourToBritishThermalUnit() {
        int kilowattHours = 1;
        double britishThermalUnitExpected = 3412.14;
        assert britishThermalUnitExpected == kilowattHours * 3412.14;
    }
}
