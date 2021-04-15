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

import android.content.Context;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gparap.apps.weather.utils.UnitUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

public class WeatherActivityInstrumentedTest {
    private View rootView;
    Context context;

    @Before
    public void setUp() {
        ActivityScenario<WeatherActivity> activityScenario = ActivityScenario.launch(WeatherActivity.class);

        //retrieve the top-level window decor view
        activityScenario.onActivity(activity ->
                rootView = activity.getWindow().getDecorView()
        );

        //get context
        activityScenario.onActivity(activity ->
                context = activity.getApplicationContext()
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

    @Test
    public void onOptionsItemSelected_checkTheSameItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context);
        onView(withText(R.string.item_units_metric)).perform(click());
        assert (UnitUtils.getInstance().getUnit() == UnitUtils.Unit.METRIC);
    }

    @Test
    public void onOptionsItemSelected_checkDifferentItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context);
        onView(withText(R.string.item_units_imperial)).perform(click());
        assert (UnitUtils.getInstance().getUnit() == UnitUtils.Unit.IMPERIAL);
    }

    @After
    public void after() {
        //restore the default unit of measurement
        //  because call to 'getInstance()' in UnitUtils mutates field 'instance'
        UnitUtils.getInstance().setUnit(UnitUtils.Unit.METRIC);
    }
}