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
package gparap.apps.classifieds.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.ui.home.HomeFragment;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentInstrumentedTest {
    @Before
    public void setUp() {
        FragmentScenario.launchInContainer (HomeFragment.class);
    }

    @Test
    public void isVisible_textViewHome() {
        onView(withId(R.id.text_view_home)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_recyclerViewHomeFeed() {
        onView(withId(R.id.recycler_view_home_feed)).check(matches(isDisplayed()));
    }
}