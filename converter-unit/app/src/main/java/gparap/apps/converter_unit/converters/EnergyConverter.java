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

public class EnergyConverter {

    public double convertJouleToCalorie(double joules) { return joules / 4.184; }
    public double convertJouleToKilocalorie(double joules) { return joules / (double)4184; }
    public double convertJouleToElectronvolt(double joules) { return joules / 1.60218 * 1.0e-19; }
    public double convertJouleToWattHour(double joules) { return joules / (double)3600; }
    public double convertJouleToBritishThermalUnit(double joules) { return joules / 1055.06; }
    public double convertJouleToKilowattHour(double joules) { return joules * (double)3600000; }

    public double convertCalorieToJoule(double calories) { return calories * 4.184; }
    public double convertCalorieToKilocalorie(double calories) { return calories / (double)1000; }
    public double convertCalorieToElectronvolt(double calories) { return calories * 4.184 / 1.60218 * 1.0e-19; }
    public double convertCalorieToWattHour(double calories) { return calories / 860.421; }
    public double convertCalorieToBritishThermalUnit(double calories) { return calories / 252.164; }
    public double convertCalorieToKilowattHour(double calories) { return calories / (double)860421; }

    public double convertKilocalorieToJoule(double kilocalories) { return kilocalories * (double)4184; }
    public double convertKilocalorieToCalorie(double kilocalories) { return kilocalories / (double)1000; }
    public double convertKilocalorieToElectronvolt(double kilocalories) { return kilocalories * 4184 / 1.60218 * 1.0e-19; }
    public double convertKilocalorieToWattHour(double kilocalories) { return kilocalories / 860.421; }
    public double convertKilocalorieToBritishThermalUnit(double kilocalories) { return kilocalories / 1.05506; }
    public double convertKilocalorieToKilowattHour(double kilocalories) { return kilocalories / (double)860421; }

    public double convertElectronvoltToJoule(double electronvolts) { return electronvolts * 1.60218 * 1.0e-19; }
    public double convertElectronvoltToCalorie(double electronvolts) { return electronvolts * 1.60218 * 1.60218 * 1.0e-19 / 4.184; }
    public double convertElectronvoltToKilocalorie(double electronvolts) { return electronvolts * 1.60218 * 1.0e-19 / 4184; }
    public double convertElectronvoltToWattHour(double electronvolts) { return electronvolts * 1.60218 * 1.0e-19 / 3600; }
    public double convertElectronvoltToBritishThermalUnit(double electronvolts) { return electronvolts * 1.60218 * 1.0e-19 / 1055.06; }
    public double convertElectronvoltToKilowattHour(double electronvolts) { return electronvolts * 1.60218 * 1.0e-19 / 3600000; }

    public double convertWattHourToJoule(double wattHours) { return wattHours * (double)3600; }
    public double convertWattHourToCalorie(double wattHours) { return wattHours * 860.421; }
    public double convertWattHourToKilocalorie(double wattHours) { return wattHours / 860.421; }
    public double convertWattHourToElectronvolt(double wattHours) { return wattHours * 3600 / 1.60218 * 1.0e-19; }
    public double convertWattHourToBritishThermalUnit(double wattHours) { return wattHours / 1.05506; }
    public double convertWattHourToKilowattHour(double wattHours) { return wattHours / (double)1000; }

    public double convertBritishThermalUnitToJoule(double britishThermalUnits) { return britishThermalUnits * 1055.06; }
    public double convertBritishThermalUnitToCalorie(double britishThermalUnits) { return britishThermalUnits * 252.164; }
    public double convertBritishThermalUnitToKilocalorie(double britishThermalUnits) { return britishThermalUnits / 1.05506; }
    public double convertBritishThermalUnitToElectronvolt(double britishThermalUnits) { return britishThermalUnits * 1055.06 / 1.60218 * 1.0e-19; }
    public double convertBritishThermalUnitToWattHour(double britishThermalUnits) { return britishThermalUnits / 3.41214; }
    public double convertBritishThermalUnitToKilowattHour(double britishThermalUnits) { return britishThermalUnits / 3412.14; }

    public double convertKilowattHourToJoule(double kilowattHours) { return kilowattHours * (double)3600000; }
    public double convertKilowattHourToCalorie(double kilowattHours) { return kilowattHours * (double)860421; }
    public double convertKilowattHourToKilocalorie(double kilowattHours) { return kilowattHours * 860.421; }
    public double convertKilowattHourToElectronvolt(double kilowattHours) { return kilowattHours * 3600000 / 1.60218 * 1.0e-19; }
    public double convertKilowattHourToWattHour(double kilowattHours) { return kilowattHours * (double)1000; }
    public double convertKilowattHourToBritishThermalUnit(double kilowattHours) { return kilowattHours * 3412.14; }
}
