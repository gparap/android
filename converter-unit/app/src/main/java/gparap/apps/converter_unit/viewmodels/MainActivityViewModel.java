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
                spinnerItemResId = R.array.spinner_items_length_distance; break;
            case AREA:
                spinnerItemResId = R.array.spinner_items_area; break;
            case VOLUME:
                spinnerItemResId = R.array.spinner_items_volume; break;
            case MASS_WEIGHT:
                spinnerItemResId = R.array.spinner_items_mass_weight; break;
            case TEMPERATURE:
                spinnerItemResId = R.array.spinner_items_temperature; break;
            case PRESSURE:
                spinnerItemResId = R.array.spinner_items_pressure; break;
            case ENERGY:
                spinnerItemResId = R.array.spinner_items_energy; break;
            case POWER:
                spinnerItemResId = R.array.spinner_items_power; break;
            case ANGLES:
                spinnerItemResId = R.array.spinner_items_angle; break;
            case FORCE:
                spinnerItemResId = R.array.spinner_items_force; break;
        }

        return spinnerItemResId;
    }
}
