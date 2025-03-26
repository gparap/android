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
package gparap.apps.converter_unit.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import gparap.apps.converter_unit.R;
import gparap.apps.converter_unit.converters.AnglesConverter;
import gparap.apps.converter_unit.converters.AreaConverter;
import gparap.apps.converter_unit.converters.EnergyConverter;
import gparap.apps.converter_unit.converters.ForceConverter;
import gparap.apps.converter_unit.converters.LengthDistanceConverter;
import gparap.apps.converter_unit.converters.MassWeightConverter;
import gparap.apps.converter_unit.converters.PowerConverter;
import gparap.apps.converter_unit.converters.PressureConverter;
import gparap.apps.converter_unit.converters.TemperatureConverter;
import gparap.apps.converter_unit.converters.VolumeConverter;
import gparap.apps.converter_unit.utils.UnitCategory;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<UnitCategory> selectedUnitCategoryLiveData = new MutableLiveData<>(UnitCategory.NONE);
    private MutableLiveData<Integer> selectedSpinnerFromUnitItemLiveData = new MutableLiveData<>(0);
    private MutableLiveData<Integer> selectedSpinnerToUnitItemLiveData = new MutableLiveData<>(0);

    public UnitCategory getSelectedUnitCategory() {
        return selectedUnitCategoryLiveData.getValue();
    }

    public void setSelectedUnitCategory(UnitCategory value) {
        selectedUnitCategoryLiveData.setValue(value);
    }

    public int getSelectedSpinnerFromUnitItemLiveData() {
        return selectedSpinnerFromUnitItemLiveData.getValue();
    }

    public void setSelectedSpinnerFromUnitItemLiveData(int value) {
        selectedSpinnerFromUnitItemLiveData.setValue(value);
    }

    public int getSelectedSpinnerToUnitItemLiveData() {
        return selectedSpinnerToUnitItemLiveData.getValue();
    }

    public void setSelectedSpinnerToUnitItemLiveData(int value) {
        selectedSpinnerToUnitItemLiveData.setValue(value);
    }

    public int getSpinnerItemsResIdByCategory(UnitCategory unitCategory) {
        int spinnerItemResId = R.array.spinner_items_empty;

        switch (unitCategory) {
            case LENGTH_DISTANCE:
                spinnerItemResId = R.array.spinner_items_length_distance;
                break;
            case AREA:
                spinnerItemResId = R.array.spinner_items_area;
                break;
            case VOLUME:
                spinnerItemResId = R.array.spinner_items_volume;
                break;
            case MASS_WEIGHT:
                spinnerItemResId = R.array.spinner_items_mass_weight;
                break;
            case TEMPERATURE:
                spinnerItemResId = R.array.spinner_items_temperature;
                break;
            case PRESSURE:
                spinnerItemResId = R.array.spinner_items_pressure;
                break;
            case ENERGY:
                spinnerItemResId = R.array.spinner_items_energy;
                break;
            case POWER:
                spinnerItemResId = R.array.spinner_items_power;
                break;
            case ANGLES:
                spinnerItemResId = R.array.spinner_items_angle;
                break;
            case FORCE:
                spinnerItemResId = R.array.spinner_items_force;
                break;
        }

        return spinnerItemResId;
    }

    public double calculateConversionValueForAnglesCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        AnglesConverter anglesConverter = new AnglesConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Degrees
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = anglesConverter.convertDegreeToRadian(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = anglesConverter.convertDegreeToGradian(inputValue);
                }
                break;

            //Radians
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = anglesConverter.convertRadianToDegree(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = anglesConverter.convertRadianToGradian(inputValue);
                }
                break;

            //Gradians
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = anglesConverter.convertGradianToDegree(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = anglesConverter.convertGradianToRadian(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForAreaCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        AreaConverter areaConverter = new AreaConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //SquareMeter
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = areaConverter.convertSquareMeterToSquareKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = areaConverter.convertSquareMeterToSquareMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = areaConverter.convertSquareMeterToHectare(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = areaConverter.convertSquareMeterToAcre(inputValue);
                }
                break;

            //SquareKilometer
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = areaConverter.convertSquareKilometerToSquareMeter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = areaConverter.convertSquareKilometerToSquareMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = areaConverter.convertSquareKilometerToHectare(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = areaConverter.convertSquareKilometerToAcre(inputValue);
                }
                break;

            //SquareMile
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = areaConverter.convertSquareMileToSquareMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = areaConverter.convertSquareMileToSquareKilometer(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = areaConverter.convertSquareMileToHectare(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = areaConverter.convertSquareMileToAcre(inputValue);
                }
                break;

            //Hectare
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = areaConverter.convertHectareToSquareMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = areaConverter.convertHectareToSquareKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = areaConverter.convertHectareToSquareMile(inputValue);
                }  else if (spinnerToItemPosition == 4) {
                    conversionResult = areaConverter.convertHectareToAcre(inputValue);
                }
                break;

            //Acre
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = areaConverter.convertAcreToSquareMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = areaConverter.convertAcreToSquareKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = areaConverter.convertAcreToSquareMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = areaConverter.convertAcreToHectare(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForEnergyCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        EnergyConverter energyConverter = new EnergyConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Joule
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = energyConverter.convertJouleToCalorie(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = energyConverter.convertJouleToKilocalorie(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = energyConverter.convertJouleToElectronvolt(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = energyConverter.convertJouleToWattHour(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = energyConverter.convertJouleToBritishThermalUnit(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = energyConverter.convertJouleToKilowattHour(inputValue);
                }
                break;

            //Calorie
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = energyConverter.convertCalorieToJoule(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = energyConverter.convertCalorieToKilocalorie(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = energyConverter.convertCalorieToElectronvolt(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = energyConverter.convertCalorieToWattHour(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = energyConverter.convertCalorieToBritishThermalUnit(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = energyConverter.convertCalorieToKilowattHour(inputValue);
                }
                break;

            //Kilocalorie
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = energyConverter.convertKilocalorieToJoule(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = energyConverter.convertKilocalorieToCalorie(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = energyConverter.convertKilocalorieToElectronvolt(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = energyConverter.convertKilocalorieToWattHour(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = energyConverter.convertKilocalorieToBritishThermalUnit(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = energyConverter.convertKilocalorieToKilowattHour(inputValue);
                }
                break;

            //Electronvolt
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = energyConverter.convertElectronvoltToJoule(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = energyConverter.convertElectronvoltToCalorie(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = energyConverter.convertElectronvoltToKilocalorie(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = energyConverter.convertElectronvoltToWattHour(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = energyConverter.convertElectronvoltToBritishThermalUnit(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = energyConverter.convertElectronvoltToKilowattHour(inputValue);
                }
                break;

            //WattHour
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = energyConverter.convertWattHourToJoule(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = energyConverter.convertWattHourToCalorie(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = energyConverter.convertWattHourToKilocalorie(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = energyConverter.convertWattHourToElectronvolt(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = energyConverter.convertWattHourToBritishThermalUnit(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = energyConverter.convertWattHourToKilowattHour(inputValue);
                }
                break;

            //BritishThermalUnit
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = energyConverter.convertBritishThermalUnitToJoule(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = energyConverter.convertBritishThermalUnitToCalorie(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = energyConverter.convertBritishThermalUnitToKilocalorie(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = energyConverter.convertBritishThermalUnitToElectronvolt(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = energyConverter.convertBritishThermalUnitToWattHour(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = energyConverter.convertBritishThermalUnitToKilowattHour(inputValue);
                }
                break;

            //KiloWattHour
            case 6:
                if (spinnerToItemPosition == 0) {
                    conversionResult = energyConverter.convertKilowattHourToJoule(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = energyConverter.convertKilowattHourToCalorie(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = energyConverter.convertKilowattHourToKilocalorie(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = energyConverter.convertKilowattHourToElectronvolt(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = energyConverter.convertKilowattHourToWattHour(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = energyConverter.convertKilowattHourToBritishThermalUnit(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForForceCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        ForceConverter forceConverter = new ForceConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Newton
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = forceConverter.convertNewtonToPoundForce(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = forceConverter.convertNewtonToKilogramForce(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = forceConverter.convertNewtonToDyne(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = forceConverter.convertNewtonToMetricTonForce(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = forceConverter.convertNewtonToUSTonForce(inputValue);
                }
                break;

            //PoundForce
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = forceConverter.convertPoundForceToNewton(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = forceConverter.convertPoundForceToKilogramForce(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = forceConverter.convertPoundForceToDyne(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = forceConverter.convertPoundForceToMetricTonForce(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = forceConverter.convertPoundForceToUSTonForce(inputValue);
                }
                break;

            //KilogramForce
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = forceConverter.convertKilogramForceToNewton(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = forceConverter.convertKilogramForceToPoundForce(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = forceConverter.convertKilogramForceToDyne(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = forceConverter.convertKilogramForceToMetricTonForce(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = forceConverter.convertKilogramForceToUSTonForce(inputValue);
                }
                break;

            //Dyne
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = forceConverter.convertDyneToNewton(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = forceConverter.convertDyneToPoundForce(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = forceConverter.convertDyneToKilogramForce(inputValue);
                }  else if (spinnerToItemPosition == 4) {
                    conversionResult = forceConverter.convertDyneToMetricTonForce(inputValue);
                }  else if (spinnerToItemPosition == 5) {
                    conversionResult = forceConverter.convertDyneToUSTonForce(inputValue);
                }
                break;

            //MetricTonForce
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = forceConverter.convertMetricTonForceToNewton(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = forceConverter.convertMetricTonForceToPoundForce(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = forceConverter.convertMetricTonForceToKilogramForce(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = forceConverter.convertMetricTonForceToDyne(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = forceConverter.convertMetricTonForceToUSTonForce(inputValue);
                }
                break;

            //USTonForce
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = forceConverter.convertUSTonForceToNewton(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = forceConverter.convertUSTonForceToPoundForce(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = forceConverter.convertUSTonForceToKilogramForce(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = forceConverter.convertUSTonForceToDyne(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = forceConverter.convertUSTonForceToMetricTonForce(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForLengthDistanceCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        LengthDistanceConverter lengthDistanceConverter = new LengthDistanceConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Meter
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = lengthDistanceConverter.convertMeterToKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = lengthDistanceConverter.convertMeterToMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = lengthDistanceConverter.convertMeterToYard(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = lengthDistanceConverter.convertMeterToFoot(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = lengthDistanceConverter.convertMeterToInch(inputValue);
                }
                break;

            //Kilometer
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = lengthDistanceConverter.convertKilometerToMeter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = lengthDistanceConverter.convertKilometerToMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = lengthDistanceConverter.convertKilometerToYard(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = lengthDistanceConverter.convertKilometerToFoot(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = lengthDistanceConverter.convertKilometerToInch(inputValue);
                }
                break;

            //Mile
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = lengthDistanceConverter.convertMileToMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = lengthDistanceConverter.convertMileToKilometer(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = lengthDistanceConverter.convertMileToYard(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = lengthDistanceConverter.convertMileToFoot(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = lengthDistanceConverter.convertMileToInch(inputValue);
                }
                break;

            //Yard
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = lengthDistanceConverter.convertYardToMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = lengthDistanceConverter.convertYardToKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = lengthDistanceConverter.convertYardToMile(inputValue);
                }  else if (spinnerToItemPosition == 4) {
                    conversionResult = lengthDistanceConverter.convertYardToFoot(inputValue);
                }  else if (spinnerToItemPosition == 5) {
                    conversionResult = lengthDistanceConverter.convertYardToInch(inputValue);
                }
                break;

            //Foot
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = lengthDistanceConverter.convertFootToMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = lengthDistanceConverter.convertFootToKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = lengthDistanceConverter.convertFootToMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = lengthDistanceConverter.convertFootToYard(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = lengthDistanceConverter.convertFootToInch(inputValue);
                }
                break;

            //Inch
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = lengthDistanceConverter.convertInchToMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = lengthDistanceConverter.convertInchToKilometer(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = lengthDistanceConverter.convertInchToMile(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = lengthDistanceConverter.convertInchToYard(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = lengthDistanceConverter.convertInchToFoot(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForMassWeightCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        MassWeightConverter massWeightConverter = new MassWeightConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Kilogram
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertKilogramToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertKilogramToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertKilogramToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertKilogramToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertKilogramToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertKilogramToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertKilogramToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertKilogramToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertKilogramToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertKilogramToCarat(inputValue);
                }
                break;

            //Gram
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertGramToKilogram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertGramToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertGramToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertGramToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertGramToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertGramToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertGramToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertGramToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertGramToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertGramToCarat(inputValue);
                }
                break;

            //Milligram
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertMilligramToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertMilligramToGram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertMilligramToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertMilligramToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertMilligramToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertMilligramToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertMilligramToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertMilligramToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertMilligramToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertMilligramToCarat(inputValue);
                }
                break;

            //MetricTon
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertMetricTonToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertMetricTonToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertMetricTonToMilligram(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertMetricTonToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertMetricTonToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertMetricTonToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertMetricTonToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertMetricTonToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertMetricTonToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertMetricTonToCarat(inputValue);
                }
                break;

            //USTon
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertUSTonToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertUSTonToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertUSTonToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertUSTonToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertUSTonToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertUSTonToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertUSTonToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertUSTonToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertUSTonToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertUSTonToCarat(inputValue);
                }
                break;

            //ImperialTon
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertImperialTonToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertImperialTonToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertImperialTonToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertImperialTonToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertImperialTonToUSTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertImperialTonToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertImperialTonToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertImperialTonToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertImperialTonToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertImperialTonToCarat(inputValue);
                }
                break;

            //Pound
            case 6:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertPoundToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertPoundToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertPoundToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertPoundToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertPoundToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertPoundToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertPoundToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertPoundToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertPoundToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertPoundToCarat(inputValue);
                }
                break;

            //USOunce
            case 7:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertUSOunceToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertUSOunceToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertUSOunceToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertUSOunceToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertUSOunceToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertUSOunceToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertUSOunceToPound(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertUSOunceToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertUSOunceToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertUSOunceToCarat(inputValue);
                }
                break;

            //ImperialOunce
            case 8:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertImperialOunceToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertImperialOunceToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertImperialOunceToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertImperialOunceToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertImperialOunceToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertImperialOunceToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertImperialOunceToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertImperialOunceToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertImperialOunceToStone(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertImperialOunceToCarat(inputValue);
                }
                break;

            //Stone
            case 9:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertStoneToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertStoneToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertStoneToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertStoneToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertStoneToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertStoneToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertStoneToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertStoneToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertStoneToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = massWeightConverter.convertStoneToCarat(inputValue);
                }
                break;

            //Carat
            case 10:
                if (spinnerToItemPosition == 0) {
                    conversionResult = massWeightConverter.convertCaratToKilogram(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = massWeightConverter.convertCaratToGram(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = massWeightConverter.convertCaratToMilligram(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = massWeightConverter.convertCaratToMetricTon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = massWeightConverter.convertCaratToUSTon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = massWeightConverter.convertCaratToImperialTon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = massWeightConverter.convertCaratToPound(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = massWeightConverter.convertCaratToUSOunce(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = massWeightConverter.convertCaratToImperialOunce(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = massWeightConverter.convertCaratToStone(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForPowerCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        PowerConverter powerConverter = new PowerConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Watt
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = powerConverter.convertWattToKilowatt(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = powerConverter.convertWattToMegawatt(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = powerConverter.convertWattToMetricHorsepower(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = powerConverter.convertWattToImperialHorsepower(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = powerConverter.convertWattToVoltAmpere(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = powerConverter.convertWattToKilovoltAmpere(inputValue);
                }
                break;

            //Kilowatt
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = powerConverter.convertKilowattToWatt(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = powerConverter.convertKilowattToMegawatt(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = powerConverter.convertKilowattToMetricHorsepower(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = powerConverter.convertKilowattToImperialHorsepower(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = powerConverter.convertKilowattToVoltAmpere(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = powerConverter.convertKilowattToKilovoltAmpere(inputValue);
                }
                break;

            //Megawatt
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = powerConverter.convertMegawattToWatt(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = powerConverter.convertMegawattToKilowatt(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = powerConverter.convertMegawattToMetricHorsepower(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = powerConverter.convertMegawattToImperialHorsepower(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = powerConverter.convertMegawattToVoltAmpere(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = powerConverter.convertMegawattToKilovoltAmpere(inputValue);
                }
                break;

            //MetricHorsepower
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = powerConverter.convertMetricHorsepowerToWatt(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = powerConverter.convertMetricHorsepowerToKilowatt(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = powerConverter.convertMetricHorsepowerToMegawatt(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = powerConverter.convertMetricHorsepowerToImperialHorsepower(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = powerConverter.convertMetricHorsepowerToVoltAmpere(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = powerConverter.convertMetricHorsepowerToKilovoltAmpere(inputValue);
                }
                break;

            //ImperialHorsepower
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = powerConverter.convertImperialHorsepowerToWatt(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = powerConverter.convertImperialHorsepowerToKilowatt(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = powerConverter.convertImperialHorsepowerToMegawatt(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = powerConverter.convertImperialHorsepowerToMetricHorsepower(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = powerConverter.convertImperialHorsepowerToVoltAmpere(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = powerConverter.convertImperialHorsepowerToKilovoltAmpere(inputValue);
                }
                break;

            //VoltAmpere
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = powerConverter.convertVoltAmpereToWatt(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = powerConverter.convertVoltAmpereToKilowatt(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = powerConverter.convertVoltAmpereToMegawatt(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = powerConverter.convertVoltAmpereToMetricHorsepower(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = powerConverter.convertVoltAmpereToImperialHorsepower(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = powerConverter.convertVoltAmpereToKilovoltAmpere(inputValue);
                }
                break;

            //KilovoltAmpere
            case 6:
                if (spinnerToItemPosition == 0) {
                    conversionResult = powerConverter.convertKilovoltAmpereToWatt(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = powerConverter.convertKilovoltAmpereToKilowatt(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = powerConverter.convertKilovoltAmpereToMegawatt(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = powerConverter.convertKilovoltAmpereToMetricHorsepower(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = powerConverter.convertKilovoltAmpereToImperialHorsepower(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = powerConverter.convertKilovoltAmpereToVoltAmpere(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForPressureCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        PressureConverter pressureConverter = new PressureConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Pascal
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = pressureConverter.convertPascalToBar(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = pressureConverter.convertPascalToAtmosphere(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = pressureConverter.convertPascalToTorr(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = pressureConverter.convertPascalToPoundPerSquareInch(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = pressureConverter.convertPascalToKilopascal(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = pressureConverter.convertPascalToMillimeterOfMercury(inputValue);
                }
                break;

            //Bar
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = pressureConverter.convertBarToPascal(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = pressureConverter.convertBarToAtmosphere(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = pressureConverter.convertBarToTorr(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = pressureConverter.convertBarToPoundPerSquareInch(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = pressureConverter.convertBarToKilopascal(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = pressureConverter.convertBarToMillimeterOfMercury(inputValue);
                }
                break;

            //Atmosphere
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = pressureConverter.convertAtmosphereToPascal(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = pressureConverter.convertAtmosphereToBar(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = pressureConverter.convertAtmosphereToTorr(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = pressureConverter.convertAtmosphereToPoundPerSquareInch(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = pressureConverter.convertAtmosphereToKilopascal(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = pressureConverter.convertAtmosphereToMillimeterOfMercury(inputValue);
                }
                break;

            //Torr
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = pressureConverter.convertTorrToPascal(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = pressureConverter.convertTorrToBar(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = pressureConverter.convertTorrToAtmosphere(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = pressureConverter.convertTorrToPoundPerSquareInch(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = pressureConverter.convertTorrToKilopascal(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = pressureConverter.convertTorrToMillimeterOfMercury(inputValue);
                }
                break;

            //PoundPerSquareInch
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = pressureConverter.convertPoundPerSquareInchToPascal(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = pressureConverter.convertPoundPerSquareInchToBar(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = pressureConverter.convertPoundPerSquareInchToAtmosphere(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = pressureConverter.convertPoundPerSquareInchToTorr(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = pressureConverter.convertPoundPerSquareInchToKilopascal(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = pressureConverter.convertPoundPerSquareInchToMillimeterOfMercury(inputValue);
                }
                break;

            //Kilopascal
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = pressureConverter.convertKilopascalToPascal(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = pressureConverter.convertKilopascalToBar(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = pressureConverter.convertKilopascalToAtmosphere(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = pressureConverter.convertKilopascalToTorr(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = pressureConverter.convertKilopascalToPoundPerSquareInch(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = pressureConverter.convertKilopascalToMillimeterOfMercury(inputValue);
                }
                break;

            //MillimeterOfMercury
            case 6:
                if (spinnerToItemPosition == 0) {
                    conversionResult = pressureConverter.convertMillimeterOfMercuryToPascal(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = pressureConverter.convertMillimeterOfMercuryToBar(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = pressureConverter.convertMillimeterOfMercuryToAtmosphere(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = pressureConverter.convertMillimeterOfMercuryToTorr(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = pressureConverter.convertMillimeterOfMercuryToPoundPerSquareInch(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = pressureConverter.convertMillimeterOfMercuryToKilopascal(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForTemperatureCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        TemperatureConverter temperatureConverter = new TemperatureConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //Celsius
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = temperatureConverter.convertCelsiusToFahrenheit(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = temperatureConverter.convertCelsiusToKelvin(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = temperatureConverter.convertCelsiusToRankine(inputValue);
                }
                break;

            //Fahrenheit
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = temperatureConverter.convertFahrenheitToCelsius(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = temperatureConverter.convertFahrenheitToKelvin(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = temperatureConverter.convertFahrenheitToRankine(inputValue);
                }
                break;

            //Kelvin
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = temperatureConverter.convertKelvinToCelsius(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = temperatureConverter.convertKelvinToFahrenheit(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = temperatureConverter.convertKelvinToRankine(inputValue);
                }
                break;

            //Rankine
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = temperatureConverter.convertRankineToCelsius(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = temperatureConverter.convertRankineToFahrenheit(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = temperatureConverter.convertRankineToKelvin(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }

    public double calculateConversionValueForVolumeCategory(int spinnerFromItemPosition, int spinnerToItemPosition, double inputValue) {
        //create a converter object
        VolumeConverter volumeConverter = new VolumeConverter();

        //init the conversion result
        double conversionResult = 0;

        //!!! always check the 1st spinner for conversions (aka "convert from")
        switch (spinnerFromItemPosition) {
            //CubicMeter
            case 0:
                if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertCubicMeterToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertCubicMeterToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertCubicMeterToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertCubicMeterToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertCubicMeterToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertCubicMeterToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertCubicMeterToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertCubicMeterToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertCubicMeterToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertCubicMeterToImperialFluidOunce(inputValue);
                }
                break;

            //Liter
            case 1:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertLiterToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertLiterToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertLiterToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertLiterToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertLiterToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertLiterToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertLiterToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertLiterToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertLiterToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertLiterToImperialFluidOunce(inputValue);
                }
                break;

            //Milliliter
            case 2:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertMilliliterToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertMilliliterToLiter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertMilliliterToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertMilliliterToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertMilliliterToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertMilliliterToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertMilliliterToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertMilliliterToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertMilliliterToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertMilliliterToImperialFluidOunce(inputValue);
                }
                break;

            //USGallon
            case 3:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertUSGallonToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertUSGallonToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertUSGallonToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertUSGallonToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertUSGallonToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertUSGallonToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertUSGallonToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertUSGallonToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertUSGallonToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertUSGallonToImperialFluidOunce(inputValue);
                }
                break;

            //ImperialGallon
            case 4:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertImperialGallonToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertImperialGallonToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertImperialGallonToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertImperialGallonToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertImperialGallonToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertImperialGallonToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertImperialGallonToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertImperialGallonToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertImperialGallonToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertImperialGallonToImperialFluidOunce(inputValue);
                }
                break;

            //USQuart
            case 5:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertUSQuartToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertUSQuartToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertUSQuartToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertUSQuartToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertUSQuartToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertUSQuartToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertUSQuartToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertUSQuartToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertUSQuartToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertUSQuartToImperialFluidOunce(inputValue);
                }
                break;

            //ImperialQuart
            case 6:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertImperialQuartToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertImperialQuartToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertImperialQuartToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertImperialQuartToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertImperialQuartToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertImperialQuartToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertImperialQuartToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertImperialQuartToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertImperialQuartToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertImperialQuartToImperialFluidOunce(inputValue);
                }
                break;

            //USPint
            case 7:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertUSPintToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertUSPintToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertUSPintToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertUSPintToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertUSPintToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertUSPintToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertUSPintToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertUSPintToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertUSPintToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertUSPintToImperialFluidOunce(inputValue);
                }
                break;

            //ImperialPint
            case 8:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertImperialPintToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertImperialPintToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertImperialPintToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertImperialPintToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertImperialPintToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertImperialPintToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertImperialPintToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertImperialPintToUSPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertImperialPintToUSFluidOunce(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertImperialPintToImperialFluidOunce(inputValue);
                }
                break;

            //USFluidOunce
            case 9:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertUSFluidOunceToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertUSFluidOunceToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertUSFluidOunceToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertUSFluidOunceToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertUSFluidOunceToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertUSFluidOunceToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertUSFluidOunceToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertUSFluidOunceToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertUSFluidOunceToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 10) {
                    conversionResult = volumeConverter.convertUSFluidOunceToImperialFluidOunce(inputValue);
                }
                break;

            //ImperialFluidOunce
            case 10:
                if (spinnerToItemPosition == 0) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToCubicMeter(inputValue);
                } else if (spinnerToItemPosition == 1) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToLiter(inputValue);
                } else if (spinnerToItemPosition == 2) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToMilliliter(inputValue);
                } else if (spinnerToItemPosition == 3) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToUSGallon(inputValue);
                } else if (spinnerToItemPosition == 4) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToImperialGallon(inputValue);
                } else if (spinnerToItemPosition == 5) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToUSQuart(inputValue);
                } else if (spinnerToItemPosition == 6) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToImperialQuart(inputValue);
                } else if (spinnerToItemPosition == 7) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToUSPint(inputValue);
                } else if (spinnerToItemPosition == 8) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToImperialPint(inputValue);
                } else if (spinnerToItemPosition == 9) {
                    conversionResult = volumeConverter.convertImperialFluidOunceToUSFluidOunce(inputValue);
                }
                break;
        }

        //return the result of the unit conversion
        return conversionResult;
    }
}
