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

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> selectedUnitCategoryIdLiveData = new MutableLiveData<>(-1);
    private MutableLiveData<Integer> selectedSpinnerFromUnitItemLiveData = new MutableLiveData<>(0);
    private MutableLiveData<Integer> selectedSpinnerToUnitItemLiveData = new MutableLiveData<>(0);

    public int getSelectedUnitCategoryId() {
        return selectedUnitCategoryIdLiveData.getValue();
    }

    public void setSelectedUnitCategoryId(int value) {
        selectedUnitCategoryIdLiveData.setValue(value);
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
}
