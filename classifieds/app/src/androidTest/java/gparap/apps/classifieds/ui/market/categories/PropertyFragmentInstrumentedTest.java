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
import gparap.apps.classifieds.ui.market.categories.property.PropertyFragment;

@RunWith(AndroidJUnit4.class)
public class PropertyFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(PropertyFragment.class);

        //init category actual values
        categoryNames.put("Commercial", "Commercial");
        categoryNames.put("Guest", "Guest");
        categoryNames.put("Land", "Land");
        categoryNames.put("Rent", "Rent");
        categoryNames.put("Sale", "Sale");
        categoryNames.put("Other", "Other");
        categoryDescriptions.put("Commercial", "Shops, offices, and business spaces.");
        categoryDescriptions.put("Guest", "Paying guest accommodations.");
        categoryDescriptions.put("Land", "Plots, farms, and vacant land.");
        categoryDescriptions.put("Rent", "Houses, apartments, and rooms for rent.");
        categoryDescriptions.put("Sale", "Properties available for purchase.");
        categoryDescriptions.put("Other", "Miscellaneous property listings.");
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Commercial() {
        onView(withId(R.id.textView_marketCategory_Property_Commercial)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_commercialDetails() {
        onView(withId(R.id.textView_marketCategory_Property_commercialDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Commercial() {
        onView(withId(R.id.imageButton_marketCategory_Property_Commercial)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryCommercial() {
        onView(withText(R.string.text_category_property_commercial)).check(matches(withText(categoryNames.get("Commercial"))));
        onView(withText(R.string.text_category_property_commercial_details)).check(matches(withText(categoryDescriptions.get("Commercial"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Guest() {
        onView(withId(R.id.textView_marketCategory_Property_Guest)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_guestDetails() {
        onView(withId(R.id.textView_marketCategory_Property_guestDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Guest() {
        onView(withId(R.id.imageButton_marketCategory_Property_Guest)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryGuest() {
        onView(withText(R.string.text_category_property_guest)).check(matches(withText(categoryNames.get("Guest"))));
        onView(withText(R.string.text_category_property_guest_details)).check(matches(withText(categoryDescriptions.get("Guest"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Land() {
        onView(withId(R.id.textView_marketCategory_Property_Land)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_landDetails() {
        onView(withId(R.id.textView_marketCategory_Property_landDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Land() {
        onView(withId(R.id.imageButton_marketCategory_Property_Land)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryLand() {
        onView(withText(R.string.text_category_property_land)).check(matches(withText(categoryNames.get("Land"))));
        onView(withText(R.string.text_category_property_land_details)).check(matches(withText(categoryDescriptions.get("Land"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Rent() {
        onView(withId(R.id.textView_marketCategory_Property_Rent)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_rentDetails() {
        onView(withId(R.id.textView_marketCategory_Property_rentDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Rent() {
        onView(withId(R.id.imageButton_marketCategory_Property_Rent)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryRent() {
        onView(withText(R.string.text_category_property_rent)).check(matches(withText(categoryNames.get("Rent"))));
        onView(withText(R.string.text_category_property_rent_details)).check(matches(withText(categoryDescriptions.get("Rent"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_Sale() {
        onView(withId(R.id.textView_marketCategory_Property_Sale)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_saleDetails() {
        onView(withId(R.id.textView_marketCategory_Property_saleDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_Sale() {
        onView(withId(R.id.imageButton_marketCategory_Property_Sale)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categorySale() {
        onView(withText(R.string.text_category_property_sale)).check(matches(withText(categoryNames.get("Sale"))));
        onView(withText(R.string.text_category_property_sale_details)).check(matches(withText(categoryDescriptions.get("Sale"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_other() {
        onView(withId(R.id.textView_marketCategory_Property_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property_otherDetails() {
        onView(withId(R.id.textView_marketCategory_Property_otherDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Property_other() {
        onView(withId(R.id.imageButton_marketCategory_Property_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_other() {
        onView(withText(R.string.text_category_property_other)).check(matches(withText(categoryNames.get("Other"))));
        onView(withText(R.string.text_category_property_other_details)).check(matches(withText(categoryDescriptions.get("Other"))));
    }
}
