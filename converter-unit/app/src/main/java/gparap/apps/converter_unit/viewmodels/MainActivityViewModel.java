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
}
