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
package gparap.apps.classifieds;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isLaunched_MarketFragment() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.layout_fragment_market)).check(matches(isDisplayed()));
    }

    @Test
    public void isLaunched_DashboardFragment() {
        onView(withId(R.id.navigation_dashboard)).perform(click());
        onView(withId(R.id.layout_fragment_dashboard)).check(matches(isDisplayed()));
    }

    @Test
    public void isLaunched_NotificationsFragment() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.layout_fragment_notifications)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Animals() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Animals)).perform(click());
        onView(withId(R.id.layout_fragment_animals)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Automobiles() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Automobiles)).perform(click());
        onView(withId(R.id.layout_fragment_automobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Clothing() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Clothing)).perform(click());
        onView(withId(R.id.layout_fragment_clothing)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Electronics() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Electronics)).perform(click());
        onView(withId(R.id.layout_fragment_electronics)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Employment() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Employment)).perform(click());
        onView(withId(R.id.layout_fragment_employment)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Home() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Home)).perform(click());
        onView(withId(R.id.layout_fragment_home)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Property() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Property)).perform(click());
        onView(withId(R.id.layout_fragment_property)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Services() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Services)).perform(click());
        onView(withId(R.id.layout_fragment_services)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigationTo_Sports() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Sports)).perform(click());
        onView(withId(R.id.layout_fragment_sports)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigation_AnimalsCategoryToSubCategories() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Animals)).perform(click());
        onView(withText(R.string.text_category_animals_birds)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_animals_cats)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_animals_dogs)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_animals_fish)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_animals_food)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_animals_reptiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigation_AutomobilesCategoryToSubCategories() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Automobiles)).perform(click());
        onView(withText(R.string.text_category_automobiles_accessories)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_automobiles_bicycles)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_automobiles_bikes)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_automobiles_cars)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_automobiles_parts)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_automobiles_utility)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigation_ClothingCategoryToSubCategories() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Clothing)).perform(click());
        onView(withText(R.string.text_category_clothing_accessories)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_clothing_kids)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_clothing_men)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_clothing_shoes)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_clothing_sportswear)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_clothing_women)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigation_ElectronicsCategoryToSubCategories() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Electronics)).perform(click());
        onView(withText(R.string.text_category_electronics_audio)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_electronics_cameras)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_electronics_computers)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_electronics_gadgets)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_electronics_mobiles)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_electronics_tvs)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigation_EmploymentCategoryToSubCategories() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Employment)).perform(click());
        onView(withText(R.string.text_category_employment_freelance)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_employment_fullTime)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_employment_internship)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_employment_other)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_employment_partTime)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_employment_remote)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_marketNavigation_HomeCategoryToSubCategories() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.imageView_marketCategory_Home)).perform(click());
        onView(withText(R.string.text_category_home_appliances)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_home_decor)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_home_furniture)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_home_garden)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_home_kitchen)).check(matches(isDisplayed()));
        onView(withText(R.string.text_category_home_other)).check(matches(isDisplayed()));
    }
}