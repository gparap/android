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
package gparap.apps.converter_unit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gparap.apps.converter_unit.utils.UnitCategory;
import gparap.apps.converter_unit.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerConvertFromUnit, spinnerConvertToUnit;
    private ArrayAdapter<CharSequence> spinnerItemsAdapter;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //get the ViewModel for this activity
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //get spinner widgets
        spinnerConvertFromUnit = findViewById(R.id.spinner_convertFromUnit);
        spinnerConvertToUnit = findViewById(R.id.spinner_convertToUnit);

        //init spinners with an empty array
        spinnerItemsAdapter = new ArrayAdapter<>(this, R.layout.layout_spinner_item);
        setSpinnerAdapterItems(R.array.spinner_items_empty);
        spinnerConvertFromUnit.setAdapter(spinnerItemsAdapter);
        spinnerConvertToUnit.setAdapter(spinnerItemsAdapter);

        //restore spinner adapter instance state
        if (viewModel.getSelectedUnitCategory() != UnitCategory.NONE) {
            setSpinnerAdapterItems(viewModel.getSpinnerItemsResIdByCategory(viewModel.getSelectedUnitCategory()));
        }

        //observe spinnerFromUnit selected items
        spinnerConvertFromUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setSelectedSpinnerFromUnitItemLiveData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //observe spinnerToUnit selected items
        spinnerConvertToUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setSelectedSpinnerToUnitItemLiveData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //convert units
        findViewById(R.id.button_convert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the input value
                EditText editTextInputValue = findViewById(R.id.editText_conversionInputValue);
                double inputValue = 0;
                if (! editTextInputValue.getText().toString().isEmpty()) {
                    inputValue = Double.parseDouble(editTextInputValue.getText().toString());
                }

                //init the conversion result
                double conversionResult = 0;

                //get spinners' selected item positions
                int spinnerFromItemPosition = viewModel.getSelectedSpinnerFromUnitItemLiveData();
                int spinnerToItemPosition = viewModel.getSelectedSpinnerToUnitItemLiveData();

                //calculate the conversion result
                switch (viewModel.getSelectedUnitCategory()) {

                    case LENGTH_DISTANCE:
                        conversionResult = viewModel.calculateConversionValueForLengthDistanceCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case AREA:
                        conversionResult = viewModel.calculateConversionValueForAreaCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case VOLUME:            // TODO: Not implemented yet.

                    case MASS_WEIGHT:
                        conversionResult = viewModel.calculateConversionValueForMassWeightCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case TEMPERATURE:
                        conversionResult = viewModel.calculateConversionValueForTemperatureCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case PRESSURE:
                        conversionResult = viewModel.calculateConversionValueForPressureCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case ENERGY:
                        conversionResult = viewModel.calculateConversionValueForEnergyCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case POWER:
                        conversionResult = viewModel.calculateConversionValueForPowerCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case ANGLES:
                        conversionResult = viewModel.calculateConversionValueForAnglesCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;

                    case FORCE:
                        conversionResult = viewModel.calculateConversionValueForForceCategory(
                                spinnerFromItemPosition, spinnerToItemPosition, inputValue);
                        break;
                }

                //display the conversion result
                TextView textViewConversionResult = findViewById(R.id.textView_conversionResult);
                textViewConversionResult.setText(String.valueOf(conversionResult));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get the id of the selected item
        int itemId = item.getItemId();

        //Handle the selected item
        switch (itemId) {

            //Category Length/Distance
            case R.id.menu_item_length_distance:
                viewModel.setSelectedUnitCategory(UnitCategory.LENGTH_DISTANCE);
                setSpinnerAdapterItems(R.array.spinner_items_length_distance);
                break;

            //Category Area
            case R.id.menu_item_area:
                viewModel.setSelectedUnitCategory(UnitCategory.AREA);
                setSpinnerAdapterItems(R.array.spinner_items_area);
                break;

            //Category Volume
            case R.id.menu_item_volume:
                viewModel.setSelectedUnitCategory(UnitCategory.VOLUME);
                setSpinnerAdapterItems(R.array.spinner_items_volume);
                break;

            //Category MassWeight
            case R.id.menu_item_mass_weight:
                viewModel.setSelectedUnitCategory(UnitCategory.MASS_WEIGHT);
                setSpinnerAdapterItems(R.array.spinner_items_mass_weight);
                break;

            //Category Temperature
            case R.id.menu_item_temperature:
                viewModel.setSelectedUnitCategory(UnitCategory.TEMPERATURE);
                setSpinnerAdapterItems(R.array.spinner_items_temperature);
                break;

            //Category Pressure
            case R.id.menu_item_pressure:
                viewModel.setSelectedUnitCategory(UnitCategory.PRESSURE);
                setSpinnerAdapterItems(R.array.spinner_items_pressure);
                break;

            //Category Energy
            case R.id.menu_item_energy:
                viewModel.setSelectedUnitCategory(UnitCategory.ENERGY);
                setSpinnerAdapterItems(R.array.spinner_items_energy);
                break;

            //Category Power
            case R.id.menu_item_power:
                viewModel.setSelectedUnitCategory(UnitCategory.POWER);
                setSpinnerAdapterItems(R.array.spinner_items_power);
                break;

            //Category Angles
            case R.id.menu_item_angles:
                viewModel.setSelectedUnitCategory(UnitCategory.ANGLES);
                setSpinnerAdapterItems(R.array.spinner_items_angle);
                break;

            //Category Force
            case R.id.menu_item_force:
                viewModel.setSelectedUnitCategory(UnitCategory.FORCE);
                setSpinnerAdapterItems(R.array.spinner_items_force);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setSpinnerAdapterItems(int spinnerItemsResId) {
        //get items as a string array from resources
        String[] itemsArray = getResources().getStringArray(spinnerItemsResId);

        //convert items array to a fixed-size list
        List<String> itemsList = new ArrayList<>(Arrays.asList(itemsArray));

        //update the adapter with the new items
        spinnerItemsAdapter.clear();
        spinnerItemsAdapter.addAll(itemsList);
        spinnerItemsAdapter.notifyDataSetChanged();
    }
}