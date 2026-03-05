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
import gparap.apps.classifieds.ui.market.categories.sports.SportsFragment;

@RunWith(AndroidJUnit4.class)
public class SportsFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(SportsFragment.class);

        //init category actual values
        categoryNames.put("Apparel", "Apparel");
        categoryNames.put("Equipment", "Equipment");
        categoryNames.put("Fitness", "Fitness");
        categoryNames.put("Indoors", "Indoors");
        categoryNames.put("Other", "Other");
        categoryNames.put("Outdoors", "Outdoors");
        categoryDescriptions.put("Apparel", "Sportswear and shoes.");
        categoryDescriptions.put("Equipment", "Gear and accessories for all sports.");
        categoryDescriptions.put("Fitness", "Gym equipment and fitness accessories.");
        categoryDescriptions.put("Indoors", "Table tennis, chess, and indoor games.");
        categoryDescriptions.put("Other", "Miscellaneous sports items.");
        categoryDescriptions.put("Outdoors", "Camping, cycling, and adventure sports items.");
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_Apparel() {
        onView(withId(R.id.textView_marketCategory_Sports_Apparel)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_apparelDetails() {
        onView(withId(R.id.textView_marketCategory_Sports_apparelDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Sports_Apparel() {
        onView(withId(R.id.imageButton_marketCategory_Sports_Apparel)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryApparel() {
        onView(withText(R.string.text_category_sports_apparel)).check(matches(withText(categoryNames.get("Apparel"))));
        onView(withText(R.string.text_category_sports_apparel_details)).check(matches(withText(categoryDescriptions.get("Apparel"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_Equipment() {
        onView(withId(R.id.textView_marketCategory_Sports_Equipment)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_equipmentDetails() {
        onView(withId(R.id.textView_marketCategory_Sports_equipmentDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Sports_Equipment() {
        onView(withId(R.id.imageButton_marketCategory_Sports_Equipment)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryEquipment() {
        onView(withText(R.string.text_category_sports_equipment)).check(matches(withText(categoryNames.get("Equipment"))));
        onView(withText(R.string.text_category_sports_equipment_details)).check(matches(withText(categoryDescriptions.get("Equipment"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_Fitness() {
        onView(withId(R.id.textView_marketCategory_Sports_Fitness)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_fitnessDetails() {
        onView(withId(R.id.textView_marketCategory_Sports_fitnessDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Sports_Fitness() {
        onView(withId(R.id.imageButton_marketCategory_Sports_Fitness)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryFitness() {
        onView(withText(R.string.text_category_sports_fitness)).check(matches(withText(categoryNames.get("Fitness"))));
        onView(withText(R.string.text_category_sports_fitness_details)).check(matches(withText(categoryDescriptions.get("Fitness"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_Indoors() {
        onView(withId(R.id.textView_marketCategory_Sports_Indoors)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_indoorsDetails() {
        onView(withId(R.id.textView_marketCategory_Sports_indoorsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Sports_Indoors() {
        onView(withId(R.id.imageButton_marketCategory_Sports_Indoors)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryIndoors() {
        onView(withText(R.string.text_category_sports_indoors)).check(matches(withText(categoryNames.get("Indoors"))));
        onView(withText(R.string.text_category_sports_indoors_details)).check(matches(withText(categoryDescriptions.get("Indoors"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_Other() {
        onView(withId(R.id.textView_marketCategory_Sports_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_otherDetails() {
        onView(withId(R.id.textView_marketCategory_Sports_otherDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Sports_Other() {
        onView(withId(R.id.imageButton_marketCategory_Sports_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryOther() {
        onView(withText(R.string.text_category_sports_other)).check(matches(withText(categoryNames.get("Other"))));
        onView(withText(R.string.text_category_sports_other_details)).check(matches(withText(categoryDescriptions.get("Other"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_outdoors() {
        onView(withId(R.id.textView_marketCategory_Sports_Outdoors)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports_outdoorsDetails() {
        onView(withId(R.id.textView_marketCategory_Sports_outdoorsDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Sports_outdoors() {
        onView(withId(R.id.imageButton_marketCategory_Sports_Outdoors)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_outdoors() {
        onView(withText(R.string.text_category_sports_outdoors)).check(matches(withText(categoryNames.get("Outdoors"))));
        onView(withText(R.string.text_category_sports_outdoors_details)).check(matches(withText(categoryDescriptions.get("Outdoors"))));
    }
}
