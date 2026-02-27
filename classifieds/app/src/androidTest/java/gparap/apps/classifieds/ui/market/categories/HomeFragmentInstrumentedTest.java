/*
 * Copyright 2026 gparap
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
package gparap.apps.classifieds.ui.market.categories;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.ArrayMap;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.ui.market.categories.home.HomeFragment;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        //init category actual values
        categoryNames.put("Appliances", "Appliances");
        categoryNames.put("Decor", "Decor");
        categoryNames.put("Furniture", "Furniture");
        categoryNames.put("Garden", "Garden");
        categoryNames.put("Kitchen", "Kitchen");
        categoryNames.put("Other", "Other");
        categoryDescriptions.put("Appliances", "Refrigerators, ovens, washers, and more.");
        categoryDescriptions.put("Decor", "Home decoration items and accessories.");
        categoryDescriptions.put("Furniture", "Sofas, beds, tables, and home furnishings.");
        categoryDescriptions.put("Garden", "Outdoor furniture, plants, and tools.");
        categoryDescriptions.put("Kitchen", "Cookware, utensils, and kitchen essentials.");
        categoryDescriptions.put("Other", "Miscellaneous home items.");
    }

    @Test
    public void isVisible_textView_marketCategory_Home_Appliances() {
        onView(withId(R.id.textView_marketCategory_Home_Appliances)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_appliancesDetails() {
        onView(withId(R.id.textView_marketCategory_Home_appliancesDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Home_Appliances() {
        onView(withId(R.id.imageButton_marketCategory_Home_Appliances)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryAppliances() {
        onView(withText(R.string.text_category_home_appliances)).check(matches(withText(categoryNames.get("Appliances"))));
        onView(withText(R.string.text_category_home_appliances_details)).check(matches(withText(categoryDescriptions.get("Appliances"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_Decor() {
        onView(withId(R.id.textView_marketCategory_Home_Decor)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_decorDetails() {
        onView(withId(R.id.textView_marketCategory_Home_decorDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Home_Decor() {
        onView(withId(R.id.imageButton_marketCategory_Home_Decor)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryDecor() {
        onView(withText(R.string.text_category_home_decor)).check(matches(withText(categoryNames.get("Decor"))));
        onView(withText(R.string.text_category_home_decor_details)).check(matches(withText(categoryDescriptions.get("Decor"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_Furniture() {
        onView(withId(R.id.textView_marketCategory_Home_Furniture)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_furnitureDetails() {
        onView(withId(R.id.textView_marketCategory_Home_furnitureDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Home_Furniture() {
        onView(withId(R.id.imageButton_marketCategory_Home_Furniture)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryFurniture() {
        onView(withText(R.string.text_category_home_furniture)).check(matches(withText(categoryNames.get("Furniture"))));
        onView(withText(R.string.text_category_home_furniture_details)).check(matches(withText(categoryDescriptions.get("Furniture"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_Garden() {
        onView(withId(R.id.textView_marketCategory_Home_Garden)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_gardenDetails() {
        onView(withId(R.id.textView_marketCategory_Home_gardenDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Home_Garden() {
        onView(withId(R.id.imageButton_marketCategory_Home_Garden)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryGarden() {
        onView(withText(R.string.text_category_home_garden)).check(matches(withText(categoryNames.get("Garden"))));
        onView(withText(R.string.text_category_home_garden_details)).check(matches(withText(categoryDescriptions.get("Garden"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_Kitchen() {
        onView(withId(R.id.textView_marketCategory_Home_Kitchen)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_kitchenDetails() {
        onView(withId(R.id.textView_marketCategory_Home_kitchenDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Home_Kitchen() {
        onView(withId(R.id.imageButton_marketCategory_Home_Kitchen)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryKitchen() {
        onView(withText(R.string.text_category_home_kitchen)).check(matches(withText(categoryNames.get("Kitchen"))));
        onView(withText(R.string.text_category_home_kitchen_details)).check(matches(withText(categoryDescriptions.get("Kitchen"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_other() {
        onView(withId(R.id.textView_marketCategory_Home_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home_otherDetails() {
        onView(withId(R.id.textView_marketCategory_Home_otherDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Home_other() {
        onView(withId(R.id.imageButton_marketCategory_Home_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_other() {
        onView(withText(R.string.text_category_home_other)).check(matches(withText(categoryNames.get("Other"))));
        onView(withText(R.string.text_category_home_other_details)).check(matches(withText(categoryDescriptions.get("Other"))));
    }
}
