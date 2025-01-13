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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //!!! temporary view for making the tests
    private TextView testingItemSelected;

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

        //get the testing TextView
        testingItemSelected = findViewById(R.id.testing_item_selected);
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

        //Handle the selected item TODO: replace with actual Unit Converter Category Items
        switch (itemId) {

            //Category Length/Distance
            case R.id.submenu_item_meter:testingItemSelected.setText(R.string.subcategory_meter);
                break;
            case R.id.submenu_item_kilometer:testingItemSelected.setText(R.string.subcategory_kilometer);
                break;
            case R.id.submenu_item_mile:testingItemSelected.setText(R.string.subcategory_mile);
                break;
            case R.id.submenu_item_yard:testingItemSelected.setText(R.string.subcategory_yard);
                break;
            case R.id.submenu_item_foot:testingItemSelected.setText(R.string.subcategory_foot);
                break;
            case R.id.submenu_item_inch:testingItemSelected.setText(R.string.subcategory_inch);
                break;

            //Category Area
            case R.id.submenu_item_square_meter:testingItemSelected.setText(R.string.subcategory_square_meter);
                break;
            case R.id.submenu_item_square_kilometer:testingItemSelected.setText(R.string.subcategory_square_kilometer);
                break;
            case R.id.submenu_item_square_mile:testingItemSelected.setText(R.string.subcategory_square_mile);
                break;
            case R.id.submenu_item_hectare:testingItemSelected.setText(R.string.subcategory_hectare);
                break;
            case R.id.submenu_item_acre:testingItemSelected.setText(R.string.subcategory_acre);
                break;

            //Category Volume
            case R.id.submenu_item_cubic_meter:testingItemSelected.setText(R.string.subcategory_cubic_meter);
                break;
            case R.id.submenu_item_liter:testingItemSelected.setText(R.string.subcategory_liter);
                break;
            case R.id.submenu_item_milliliter:testingItemSelected.setText(R.string.subcategory_milliliter);
                break;
            case R.id.submenu_item_gallon_us:testingItemSelected.setText(R.string.subcategory_gallon_us);
                break;
            case R.id.submenu_item_gallon_imperial:testingItemSelected.setText(R.string.subcategory_gallon_imperial);
                break;
            case R.id.submenu_item_quart_us:testingItemSelected.setText(R.string.subcategory_quart_us);
                break;
            case R.id.submenu_item_quart_imperial:testingItemSelected.setText(R.string.subcategory_quart_imperial);
                break;
            case R.id.submenu_item_pint_us:testingItemSelected.setText(R.string.subcategory_pint_us);
                break;
            case R.id.submenu_item_pint_imperial:testingItemSelected.setText(R.string.subcategory_pint_imperial);
                break;
            case R.id.submenu_item_fluid_ounce_us:testingItemSelected.setText(R.string.subcategory_fluid_ounce_us);
                break;
            case R.id.submenu_item_fluid_ounce_imperial:testingItemSelected.setText(R.string.subcategory_fluid_ounce_imperial);
                break;

            //Category MassWeight
            case R.id.submenu_item_kilogram:testingItemSelected.setText(R.string.subcategory_kilometer);
                break;
            case R.id.submenu_item_gram:testingItemSelected.setText(R.string.subcategory_gram);
                break;
            case R.id.submenu_item_milligram:testingItemSelected.setText(R.string.subcategory_milligram);
                break;
            case R.id.submenu_item_metric_ton:testingItemSelected.setText(R.string.subcategory_metric_ton);
                break;
            case R.id.submenu_item_ton_us:testingItemSelected.setText(R.string.subcategory_ton_us);
                break;
            case R.id.submenu_item_ton_imperial:testingItemSelected.setText(R.string.subcategory_ton_imperial);
                break;
            case R.id.submenu_item_pound:testingItemSelected.setText(R.string.subcategory_pound);
                break;
            case R.id.submenu_item_ounce_us:testingItemSelected.setText(R.string.subcategory_ounce_us);
                break;
            case R.id.submenu_item_ounce_imperial:testingItemSelected.setText(R.string.subcategory_ounce_imperial);
                break;
            case R.id.submenu_item_stone:testingItemSelected.setText(R.string.subcategory_stone);
                break;
            case R.id.submenu_item_carat:testingItemSelected.setText(R.string.subcategory_carat);
                break;

            //Category Temperature
            case R.id.submenu_item_celsius:testingItemSelected.setText(R.string.subcategory_celsius);
                break;
            case R.id.submenu_item_fahrenheit:testingItemSelected.setText(R.string.subcategory_fahrenheit);
                break;
            case R.id.submenu_item_kelvin:testingItemSelected.setText(R.string.subcategory_kelvin);
                break;
            case R.id.submenu_item_rankine:testingItemSelected.setText(R.string.subcategory_rankine);
                break;

            //Category Pressure
            case R.id.submenu_item_pascal:testingItemSelected.setText(R.string.subcategory_pascal);
                break;
            case R.id.submenu_item_bar:testingItemSelected.setText(R.string.subcategory_bar);
                break;
            case R.id.submenu_item_atmosphere:testingItemSelected.setText(R.string.subcategory_atmosphere);
                break;
            case R.id.submenu_item_torr:testingItemSelected.setText(R.string.subcategory_torr);
                break;
            case R.id.submenu_item_pound_per_square_inch:testingItemSelected.setText(R.string.subcategory_pound_per_square_inch);
                break;
            case R.id.submenu_item_kilopascal:testingItemSelected.setText(R.string.subcategory_kilopascal);
                break;
            case R.id.submenu_item_millimeter_of_mercury:testingItemSelected.setText(R.string.subcategory_millimeter_of_mercury);
                break;

            //Category Energy
            case R.id.submenu_item_joule:testingItemSelected.setText(R.string.subcategory_joule);
                break;
            case R.id.submenu_item_calorie:testingItemSelected.setText(R.string.subcategory_calorie);
                break;
            case R.id.submenu_item_kilocalorie:testingItemSelected.setText(R.string.subcategory_kilocalorie);
                break;
            case R.id.submenu_item_electronvolt:testingItemSelected.setText(R.string.subcategory_electronvolt);
                break;
            case R.id.submenu_item_watt_hour:testingItemSelected.setText(R.string.subcategory_watt_hour);
                break;
            case R.id.submenu_item_british_thermal_unit:testingItemSelected.setText(R.string.subcategory_british_thermal_unit);
                break;
            case R.id.submenu_item_kilowatt_hour:testingItemSelected.setText(R.string.subcategory_kilowatt_hour);
                break;

            //Category Power
            case R.id.submenu_item_watt:testingItemSelected.setText(R.string.subcategory_watt);
                break;
            case R.id.submenu_item_kilowatt:testingItemSelected.setText(R.string.subcategory_kilowatt);
                break;
            case R.id.submenu_item_megawatt:testingItemSelected.setText(R.string.subcategory_megawatt);
                break;
            case R.id.submenu_item_horsepower_metric:testingItemSelected.setText(R.string.subcategory_horsepower_metric);
                break;
            case R.id.submenu_item_horsepower_imperial:testingItemSelected.setText(R.string.subcategory_horsepower_imperial);
                break;

            case R.id.submenu_item_volt_ampere:testingItemSelected.setText(R.string.subcategory_volt_ampere);
                break;

            case R.id.submenu_item_kilovolt_ampere:testingItemSelected.setText(R.string.subcategory_kilovolt_ampere);
                break;

                //Category Angles
            case R.id.submenu_item_degree:testingItemSelected.setText(R.string.subcategory_degree);
                break;
            case R.id.submenu_item_radian:testingItemSelected.setText(R.string.subcategory_radian);
                break;
            case R.id.submenu_item_gradian:testingItemSelected.setText(R.string.subcategory_gradian);
                break;

            //Category #10
            case R.id.submenu_item_newton:testingItemSelected.setText(R.string.subcategory_newton);
                break;
            case R.id.submenu_item_pound_force:testingItemSelected.setText(R.string.subcategory_pound_force);
                break;
            case R.id.submenu_item_kilogram_force:testingItemSelected.setText(R.string.subcategory_kilogram_force);
                break;
            case R.id.submenu_item_dyne:testingItemSelected.setText(R.string.subcategory_dyne);
                break;
            case R.id.submenu_item_ton_force_metric:testingItemSelected.setText(R.string.subcategory_ton_force_metric);
                break;
            case R.id.submenu_item_ton_force_us:testingItemSelected.setText(R.string.subcategory_ton_force_us);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}