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
import gparap.apps.classifieds.ui.details.DetailsFragment;

@RunWith(AndroidJUnit4.class)
public class DetailsFragmentInstrumentedTest {
    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(DetailsFragment.class);
    }

    @Test
    public void isVisible_imageView_classified_details() {
        onView(withId(R.id.imageView_classified_details)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_classified_details_description_label() {
        onView(withId(R.id.textView_classified_details_description_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_classified_details_description() {
        onView(withId(R.id.textView_classified_details_description)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_classified_details_price_label() {
        onView(withId(R.id.textView_classified_details_price_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_classified_details_price() {
        onView(withId(R.id.textView_classified_details_price)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_classified_details_contact_label() {
        onView(withId(R.id.textView_classified_details_contact_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_classified_details_contact() {
        onView(withId(R.id.textView_classified_details_contact)).check(matches(isDisplayed()));
    }
}
