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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerConvertFromUnit, spinnerConvertToUnit;
    ArrayAdapter<CharSequence> spinnerItemsAdapter;

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

        //get spinner widgets
        spinnerConvertFromUnit = findViewById(R.id.spinner_convertFromUnit);
        spinnerConvertToUnit = findViewById(R.id.spinner_convertToUnit);

        //init spinners with an empty array
        spinnerItemsAdapter = new ArrayAdapter<>(this,R.layout.layout_spinner_item);
        setSpinnerAdapterItems(R.array.spinner_items_empty);
        spinnerConvertFromUnit.setAdapter(spinnerItemsAdapter);
        spinnerConvertToUnit.setAdapter(spinnerItemsAdapter);
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
            case R.id.menu_item_length_distance: setSpinnerAdapterItems(R.array.spinner_items_length_distance);
            break;

            //Category Area
            case R.id.menu_item_area: setSpinnerAdapterItems(R.array.spinner_items_area);
                break;

            //Category Volume
            case R.id.menu_item_volume: setSpinnerAdapterItems(R.array.spinner_items_volume);
                break;

            //Category MassWeight
            case R.id.menu_item_mass_weight: setSpinnerAdapterItems(R.array.spinner_items_mass_weight);
                break;

            //Category Temperature
            case R.id.menu_item_temperature: setSpinnerAdapterItems(R.array.spinner_items_temperature);
                break;

            //Category Pressure
            case R.id.menu_item_pressure: setSpinnerAdapterItems(R.array.spinner_items_pressure);
                break;

            //Category Energy
            case R.id.menu_item_energy: setSpinnerAdapterItems(R.array.spinner_items_energy);
                break;

            //Category Power
            case R.id.menu_item_power: setSpinnerAdapterItems(R.array.spinner_items_power);
                break;

            //Category Angles
            case R.id.menu_item_angles: setSpinnerAdapterItems(R.array.spinner_items_angle);
                break;

            //Category Force
            case R.id.menu_item_force: setSpinnerAdapterItems(R.array.spinner_items_force);
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