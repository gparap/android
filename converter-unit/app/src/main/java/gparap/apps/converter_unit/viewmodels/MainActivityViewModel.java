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
}
