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
import gparap.apps.classifieds.ui.market.categories.services.ServicesFragment;

@RunWith(AndroidJUnit4.class)
public class ServicesFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(ServicesFragment.class);

        //init category actual values
        categoryNames.put("Beauty", "Beauty");
        categoryNames.put("Cleaning", "Cleaning");
        categoryNames.put("Events", "Events");
        categoryNames.put("Other", "Other");
        categoryNames.put("Repair", "Repair");
        categoryNames.put("Tutoring", "Tutoring");
        categoryDescriptions.put("Beauty", "Salon, spa, and personal care services.");
        categoryDescriptions.put("Cleaning", "House, office, and commercial cleaning.");
        categoryDescriptions.put("Events", "Event planning, photography, and catering.");
        categoryDescriptions.put("Other", "Miscellaneous service offerings.");
        categoryDescriptions.put("Repair", "Home, appliance, and vehicle repair services.");
        categoryDescriptions.put("Tutoring", "Academic and skill-based tutoring.");
    }

    @Test
    public void isVisible_textView_marketCategory_Services_Beauty() {
        onView(withId(R.id.textView_marketCategory_Services_Beauty)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_beautyDetails() {
        onView(withId(R.id.textView_marketCategory_Services_beautyDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Services_Beauty() {
        onView(withId(R.id.imageButton_marketCategory_Services_Beauty)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryBeauty() {
        onView(withText(R.string.text_category_services_beauty)).check(matches(withText(categoryNames.get("Beauty"))));
        onView(withText(R.string.text_category_services_beauty_details)).check(matches(withText(categoryDescriptions.get("Beauty"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_Cleaning() {
        onView(withId(R.id.textView_marketCategory_Services_Cleaning)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_cleaningDetails() {
        onView(withId(R.id.textView_marketCategory_Services_cleaningDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Services_Cleaning() {
        onView(withId(R.id.imageButton_marketCategory_Services_Cleaning)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryCleaning() {
        onView(withText(R.string.text_category_services_cleaning)).check(matches(withText(categoryNames.get("Cleaning"))));
        onView(withText(R.string.text_category_services_cleaning_details)).check(matches(withText(categoryDescriptions.get("Cleaning"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_Events() {
        onView(withId(R.id.textView_marketCategory_Services_Events)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_eventsDetails() {
        onView(withId(R.id.textView_marketCategory_Services_eventsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Services_Events() {
        onView(withId(R.id.imageButton_marketCategory_Services_Events)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryEvents() {
        onView(withText(R.string.text_category_services_events)).check(matches(withText(categoryNames.get("Events"))));
        onView(withText(R.string.text_category_services_events_details)).check(matches(withText(categoryDescriptions.get("Events"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_Other() {
        onView(withId(R.id.textView_marketCategory_Services_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_otherDetails() {
        onView(withId(R.id.textView_marketCategory_Services_otherDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Services_Other() {
        onView(withId(R.id.imageButton_marketCategory_Services_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryOther() {
        onView(withText(R.string.text_category_services_other)).check(matches(withText(categoryNames.get("Other"))));
        onView(withText(R.string.text_category_services_other_details)).check(matches(withText(categoryDescriptions.get("Other"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_Repair() {
        onView(withId(R.id.textView_marketCategory_Services_Repair)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_repairDetails() {
        onView(withId(R.id.textView_marketCategory_Services_repairDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Services_Repair() {
        onView(withId(R.id.imageButton_marketCategory_Services_Repair)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryRepair() {
        onView(withText(R.string.text_category_services_repair)).check(matches(withText(categoryNames.get("Repair"))));
        onView(withText(R.string.text_category_services_repair_details)).check(matches(withText(categoryDescriptions.get("Repair"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_tutoring() {
        onView(withId(R.id.textView_marketCategory_Services_Tutoring)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services_tutoringDetails() {
        onView(withId(R.id.textView_marketCategory_Services_tutoringDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Services_tutoring() {
        onView(withId(R.id.imageButton_marketCategory_Services_Tutoring)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_tutoring() {
        onView(withText(R.string.text_category_services_tutoring)).check(matches(withText(categoryNames.get("Tutoring"))));
        onView(withText(R.string.text_category_services_tutoring_details)).check(matches(withText(categoryDescriptions.get("Tutoring"))));
    }
}
