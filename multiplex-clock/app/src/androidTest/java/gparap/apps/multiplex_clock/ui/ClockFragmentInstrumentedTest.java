/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.multiplex_clock.ui;

import android.os.Build;
import android.widget.TextView;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.filters.SdkSuppress;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ClockFragmentInstrumentedTest {
    FragmentScenario<ClockFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(ClockFragment.class);
    }

    @Test
    public void isVisible_AnalogClock() {
        onView(withId(R.id.analogClock)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_TextClock() {
        onView(withId(R.id.textClock)).check(matches(isDisplayed()));
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void isVisible_ShortTimeZone() {
        onView(withId(R.id.textViewTimeZoneShort)).check(matches(isDisplayed()));
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void isVisible_LongTimeZone() {
        onView(withId(R.id.textViewTimeZoneLong)).check(matches(isDisplayed()));
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void isValueCorrect_ShortTimeZone() {
        String expectedTimeZone = TimeZone.getDefault()
                .getDisplayName(false, TimeZone.SHORT, Locale.getDefault());

        AtomicReference<String> actualTimeZone = new AtomicReference<>();
        fragmentScenario.onFragment(fragment -> {
            TextView timeZoneShort = fragment.getActivity().findViewById(R.id.textViewTimeZoneShort);
            actualTimeZone.set(timeZoneShort.getText().toString());
        });

        assert expectedTimeZone.equals(actualTimeZone.get());
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void isValueCorrect_LongTimeZone() {
        String expectedTimeZone = TimeZone.getDefault()
                .getDisplayName(false, TimeZone.LONG, Locale.getDefault());

        AtomicReference<String> actualTimeZone = new AtomicReference<>();
        fragmentScenario.onFragment(fragment -> {
            TextView timeZoneShort = fragment.getActivity().findViewById(R.id.textViewTimeZoneLong);
            actualTimeZone.set(timeZoneShort.getText().toString());
        });

        assert expectedTimeZone.equals(actualTimeZone.get());
    }
}