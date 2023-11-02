/*
 * Copyright 2023 gparap
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
package gparap.apps.selfie_editor

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//!!! important !!!
// Steps to reproduce these tests:
//  1.take a picture using the app
//  2.print the uri of the picture
//  3.use that uri for the intent
//  4.do not delete the picture from the test device (!)

@RunWith(AndroidJUnit4::class)
class EditorActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        //create an intent to pass the category id needed for this activity and launch activity
        val intent = Intent(ApplicationProvider.getApplicationContext(), EditorActivity::class.java)
        intent.putExtra(URI_NAME, URI_VALUE)
        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun isVisible_topContainer() {
        onView(withId(R.id.constraint_layout_container_top)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_selfieImage() {
        onView(withId(R.id.selfie_canvas)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_bottomContainer() {
        onView(withId(R.id.constraint_layout_container_bottom)).check(matches(isDisplayed()))
    }

    companion object {
        const val URI_NAME = "selfie_saved_uri"
        const val URI_VALUE = "content://media/external/images/media/1000000067"
    }
}