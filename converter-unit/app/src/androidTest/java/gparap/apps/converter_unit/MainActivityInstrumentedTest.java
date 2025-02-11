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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isVisible_textView_unitConversionCategory() {
        onView(withId(R.id.textView_unitConversionCategory)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_convertFromUnitLabel() {
        onView(withId(R.id.textView_convertFromUnitLabel)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_spinner_convertFromUnit() {
        onView(withId(R.id.spinner_convertFromUnit)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_editText_convertFromUnitValue() {
        onView(withId(R.id.editText_convertFromUnitValue)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_convertToUnitLabel() {
        onView(withId(R.id.textView_convertToUnitLabel)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_spinner_convertToUnit() {
        onView(withId(R.id.spinner_convertToUnit)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_editText_convertToUnitValue() {
        onView(withId(R.id.button_convert)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_conversionResult() {
        onView(withId(R.id.textView_conversionResult)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryLengthDistance() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
        onView(withText(R.string.subcategory_meter)).perform(click());
        onView(withText(R.string.subcategory_meter)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
        onView(withText(R.string.subcategory_kilometer)).perform(click());
        onView(withText(R.string.subcategory_kilometer)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
        onView(withText(R.string.subcategory_mile)).perform(click());
        onView(withText(R.string.subcategory_mile)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
        onView(withText(R.string.subcategory_yard)).perform(click());
        onView(withText(R.string.subcategory_yard)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
        onView(withText(R.string.subcategory_foot)).perform(click());
        onView(withText(R.string.subcategory_foot)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
        onView(withText(R.string.subcategory_inch)).perform(click());
        onView(withText(R.string.subcategory_inch)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryArea() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_area)).perform(click());
        onView(withText(R.string.subcategory_square_meter)).perform(click());
        onView(withText(R.string.subcategory_square_meter)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_area)).perform(click());
        onView(withText(R.string.subcategory_square_kilometer)).perform(click());
        onView(withText(R.string.subcategory_square_kilometer)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_area)).perform(click());
        onView(withText(R.string.subcategory_square_mile)).perform(click());
        onView(withText(R.string.subcategory_square_mile)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_area)).perform(click());
        onView(withText(R.string.subcategory_hectare)).perform(click());
        onView(withText(R.string.subcategory_hectare)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_area)).perform(click());
        onView(withText(R.string.subcategory_acre)).perform(click());
        onView(withText(R.string.subcategory_acre)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryVolume() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_cubic_meter)).perform(click());
        onView(withText(R.string.subcategory_cubic_meter)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_liter)).perform(click());
        onView(withText(R.string.subcategory_liter)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_milliliter)).perform(click());
        onView(withText(R.string.subcategory_milliliter)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_gallon_us)).perform(click());
        onView(withText(R.string.subcategory_gallon_us)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_gallon_imperial)).perform(click());
        onView(withText(R.string.subcategory_gallon_imperial)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_quart_us)).perform(click());
        onView(withText(R.string.subcategory_quart_us)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_quart_imperial)).perform(click());
        onView(withText(R.string.subcategory_quart_imperial)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_pint_us)).perform(click());
        onView(withText(R.string.subcategory_pint_us)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_pint_imperial)).perform(click());
        onView(withText(R.string.subcategory_pint_imperial)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_fluid_ounce_us)).perform(click());
        onView(withText(R.string.subcategory_fluid_ounce_us)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
        onView(withText(R.string.subcategory_fluid_ounce_imperial)).perform(click());
        onView(withText(R.string.subcategory_fluid_ounce_imperial)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryMassWeight() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_kilometer)).perform(click());
        onView(withText(R.string.subcategory_kilometer)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_gram)).perform(click());
        onView(withText(R.string.subcategory_gram)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_milligram)).perform(click());
        onView(withText(R.string.subcategory_milligram)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_metric_ton)).perform(click());
        onView(withText(R.string.subcategory_metric_ton)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_ton_us)).perform(click());
        onView(withText(R.string.subcategory_ton_us)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_ton_imperial)).perform(click());
        onView(withText(R.string.subcategory_ton_imperial)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_pound)).perform(click());
        onView(withText(R.string.subcategory_pound)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_ounce_us)).perform(click());
        onView(withText(R.string.subcategory_ounce_us)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_ounce_imperial)).perform(click());
        onView(withText(R.string.subcategory_ounce_imperial)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_stone)).perform(click());
        onView(withText(R.string.subcategory_stone)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
        onView(withText(R.string.subcategory_carat)).perform(click());
        onView(withText(R.string.subcategory_carat)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryTemperature() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_temperature)).perform(click());
        onView(withText(R.string.subcategory_celsius)).perform(click());
        onView(withText(R.string.subcategory_celsius)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_temperature)).perform(click());
        onView(withText(R.string.subcategory_fahrenheit)).perform(click());
        onView(withText(R.string.subcategory_fahrenheit)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_temperature)).perform(click());
        onView(withText(R.string.subcategory_kelvin)).perform(click());
        onView(withText(R.string.subcategory_kelvin)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_temperature)).perform(click());
        onView(withText(R.string.subcategory_rankine)).perform(click());
        onView(withText(R.string.subcategory_rankine)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryPressure() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_pascal)).perform(click());
        onView(withText(R.string.subcategory_pascal)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_bar)).perform(click());
        onView(withText(R.string.subcategory_bar)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_atmosphere)).perform(click());
        onView(withText(R.string.subcategory_atmosphere)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_torr)).perform(click());
        onView(withText(R.string.subcategory_torr)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_pound_per_square_inch)).perform(click());
        onView(withText(R.string.subcategory_pound_per_square_inch)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_kilopascal)).perform(click());
        onView(withText(R.string.subcategory_kilopascal)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
        onView(withText(R.string.subcategory_millimeter_of_mercury)).perform(click());
        onView(withText(R.string.subcategory_millimeter_of_mercury)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryEnergy() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_joule)).perform(click());
        onView(withText(R.string.subcategory_joule)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_calorie)).perform(click());
        onView(withText(R.string.subcategory_calorie)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_kilocalorie)).perform(click());
        onView(withText(R.string.subcategory_kilocalorie)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_electronvolt)).perform(click());
        onView(withText(R.string.subcategory_electronvolt)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_watt_hour)).perform(click());
        onView(withText(R.string.subcategory_watt_hour)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_british_thermal_unit)).perform(click());
        onView(withText(R.string.subcategory_british_thermal_unit)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
        onView(withText(R.string.subcategory_kilowatt_hour)).perform(click());
        onView(withText(R.string.subcategory_kilowatt_hour)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryPower() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_watt)).perform(click());
        onView(withText(R.string.subcategory_watt)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_kilowatt)).perform(click());
        onView(withText(R.string.subcategory_kilowatt)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_megawatt)).perform(click());
        onView(withText(R.string.subcategory_megawatt)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_horsepower_metric)).perform(click());
        onView(withText(R.string.subcategory_horsepower_metric)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_horsepower_imperial)).perform(click());
        onView(withText(R.string.subcategory_horsepower_imperial)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_volt_ampere)).perform(click());
        onView(withText(R.string.subcategory_volt_ampere)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
        onView(withText(R.string.subcategory_kilovolt_ampere)).perform(click());
        onView(withText(R.string.subcategory_kilovolt_ampere)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryAngles() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_angles)).perform(click());
        onView(withText(R.string.subcategory_degree)).perform(click());
        onView(withText(R.string.subcategory_degree)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_angles)).perform(click());
        onView(withText(R.string.subcategory_radian)).perform(click());
        onView(withText(R.string.subcategory_radian)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_angles)).perform(click());
        onView(withText(R.string.subcategory_gradian)).perform(click());
        onView(withText(R.string.subcategory_gradian)).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void areCorrect_onOptionsItemSelected_categoryForce() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_force)).perform(click());
        onView(withText(R.string.subcategory_newton)).perform(click());
        onView(withText(R.string.subcategory_newton)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_force)).perform(click());
        onView(withText(R.string.subcategory_pound_force)).perform(click());
        onView(withText(R.string.subcategory_pound_force)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_force)).perform(click());
        onView(withText(R.string.subcategory_kilogram_force)).perform(click());
        onView(withText(R.string.subcategory_kilogram_force)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_force)).perform(click());
        onView(withText(R.string.subcategory_dyne)).perform(click());
        onView(withText(R.string.subcategory_dyne)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_force)).perform(click());
        onView(withText(R.string.subcategory_ton_force_metric)).perform(click());
        onView(withText(R.string.subcategory_ton_force_metric)).check(matches(isDisplayed()));
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_force)).perform(click());
        onView(withText(R.string.subcategory_ton_force_us)).perform(click());
        onView(withText(R.string.subcategory_ton_force_us)).check(matches(isDisplayed()));
    }
}