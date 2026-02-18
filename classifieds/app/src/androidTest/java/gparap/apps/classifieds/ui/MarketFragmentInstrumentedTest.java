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
import gparap.apps.classifieds.ui.market.MarketFragment;

@RunWith(AndroidJUnit4.class)
public class MarketFragmentInstrumentedTest {
    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(MarketFragment.class);
    }

    @Test
    public void isVisible_textView_marketCategory_Animals() {
        onView(withId(R.id.textView_marketCategory_Animals)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Animals() {
        onView(withId(R.id.imageView_marketCategory_Animals)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Automobiles() {
        onView(withId(R.id.textView_marketCategory_Automobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Automobiles() {
        onView(withId(R.id.imageView_marketCategory_Automobiles)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Clothing() {
        onView(withId(R.id.textView_marketCategory_Clothing)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Clothing() {
        onView(withId(R.id.imageView_marketCategory_Clothing)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Electronics() {
        onView(withId(R.id.textView_marketCategory_Electronics)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Electronics() {
        onView(withId(R.id.imageView_marketCategory_Electronics)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment() {
        onView(withId(R.id.textView_marketCategory_Employment)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Employment() {
        onView(withId(R.id.imageView_marketCategory_Employment)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Home() {
        onView(withId(R.id.textView_marketCategory_Home)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Home() {
        onView(withId(R.id.imageView_marketCategory_Home)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Property() {
        onView(withId(R.id.textView_marketCategory_Property)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Property() {
        onView(withId(R.id.imageView_marketCategory_Property)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Services() {
        onView(withId(R.id.textView_marketCategory_Services)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Services() {
        onView(withId(R.id.imageView_marketCategory_Services)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Sports() {
        onView(withId(R.id.textView_marketCategory_Sports)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageView_marketCategory_Sports() {
        onView(withId(R.id.imageView_marketCategory_Sports)).check(matches(isDisplayed()));
    }
}
