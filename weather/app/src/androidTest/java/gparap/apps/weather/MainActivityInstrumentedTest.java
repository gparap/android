/*
 * Copyright 2021 gparap
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
package gparap.apps.weather;

import android.view.View;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

public class MainActivityInstrumentedTest {
    private View rootView;

    @Before
    public void setUp() {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        //retrieve the top-level window decor view
        activityScenario.onActivity(activity ->
                rootView = activity.getWindow().getDecorView()
        );
    }

    @Test
    public void isCitySearchEmpty_displayToastMessage() {
        onView(withId(R.id.imageViewSearchCityIcon)).perform(click());

        //test toast message
        onView(withText(R.string.toast_empty_search))
                .inRoot(withDecorView(not(rootView)))
                .check(matches(isDisplayed()));
    }
}