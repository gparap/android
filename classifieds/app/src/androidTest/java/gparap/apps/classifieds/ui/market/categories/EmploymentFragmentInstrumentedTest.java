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
import gparap.apps.classifieds.ui.market.categories.employment.EmploymentFragment;

@RunWith(AndroidJUnit4.class)
public class EmploymentFragmentInstrumentedTest {
    private final ArrayMap<String, String> categoryNames = new ArrayMap<>();
    private final ArrayMap<String, String> categoryDescriptions = new ArrayMap<>();

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(EmploymentFragment.class);

        //init category actual values
        categoryNames.put("Freelance", "Freelance");
        categoryNames.put("FullTime", "FullTime");
        categoryNames.put("Internship", "Internship");
        categoryNames.put("Other", "Other");
        categoryNames.put("PartTime", "PartTime");
        categoryNames.put("Remote", "Remote");
        categoryDescriptions.put("Freelance", "Short-term and contract work.");
        categoryDescriptions.put("FullTime", "Permanent full-time job openings.");
        categoryDescriptions.put("Internship", "Internships and training positions.");
        categoryDescriptions.put("Other", "Miscellaneous job opportunities.");
        categoryDescriptions.put("PartTime", "Part-time positions and shifts.");
        categoryDescriptions.put("Remote", "Work-from-home opportunities.");
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_Freelance() {
        onView(withId(R.id.textView_marketCategory_Employment_Freelance)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_freelanceDetails() {
        onView(withId(R.id.textView_marketCategory_Employment_freelanceDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Employment_Freelance() {
        onView(withId(R.id.imageButton_marketCategory_Employment_Freelance)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryFreelance() {
        onView(withText(R.string.text_category_employment_freelance)).check(matches(withText(categoryNames.get("Freelance"))));
        onView(withText(R.string.text_category_employment_freelance_details)).check(matches(withText(categoryDescriptions.get("Freelance"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_FullTime() {
        onView(withId(R.id.textView_marketCategory_Employment_FullTime)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_fullTimeDetails() {
        onView(withId(R.id.textView_marketCategory_Employment_fullTimeDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Employment_FullTime() {
        onView(withId(R.id.imageButton_marketCategory_Employment_FullTime)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryFullTime() {
        onView(withText(R.string.text_category_employment_fullTime)).check(matches(withText(categoryNames.get("FullTime"))));
        onView(withText(R.string.text_category_employment_fullTime_details)).check(matches(withText(categoryDescriptions.get("FullTime"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_Internship() {
        onView(withId(R.id.textView_marketCategory_Employment_Internship)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_internshipDetails() {
        onView(withId(R.id.textView_marketCategory_Employment_internshipDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Employment_Internship() {
        onView(withId(R.id.imageButton_marketCategory_Employment_Internship)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryInternship() {
        onView(withText(R.string.text_category_employment_internship)).check(matches(withText(categoryNames.get("Internship"))));
        onView(withText(R.string.text_category_employment_internship_details)).check(matches(withText(categoryDescriptions.get("Internship"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_Other() {
        onView(withId(R.id.textView_marketCategory_Employment_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_otherDetails() {
        onView(withId(R.id.textView_marketCategory_Employment_otherDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Employment_Other() {
        onView(withId(R.id.imageButton_marketCategory_Employment_Other)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryOther() {
        onView(withText(R.string.text_category_employment_other)).check(matches(withText(categoryNames.get("Other"))));
        onView(withText(R.string.text_category_employment_other_details)).check(matches(withText(categoryDescriptions.get("Other"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_PartTime() {
        onView(withId(R.id.textView_marketCategory_Employment_PartTime)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_partTimeDetails() {
        onView(withId(R.id.textView_marketCategory_Employment_partTimeDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Employment_PartTime() {
        onView(withId(R.id.imageButton_marketCategory_Employment_PartTime)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_categoryPartTime() {
        onView(withText(R.string.text_category_employment_partTime)).check(matches(withText(categoryNames.get("PartTime"))));
        onView(withText(R.string.text_category_employment_partTime_details)).check(matches(withText(categoryDescriptions.get("PartTime"))));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_remote() {
        onView(withId(R.id.textView_marketCategory_Employment_Remote)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_marketCategory_Employment_remoteDetails() {
        onView(withId(R.id.textView_marketCategory_Employment_remoteDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_imageButton_marketCategory_Employment_remote() {
        onView(withId(R.id.imageButton_marketCategory_Employment_Remote)).check(matches(isDisplayed()));
    }

    @Test
    public void isCorrect_textWithDetails_category_remote() {
        onView(withText(R.string.text_category_employment_remote)).check(matches(withText(categoryNames.get("Remote"))));
        onView(withText(R.string.text_category_employment_remote_details)).check(matches(withText(categoryDescriptions.get("Remote"))));
    }
}
