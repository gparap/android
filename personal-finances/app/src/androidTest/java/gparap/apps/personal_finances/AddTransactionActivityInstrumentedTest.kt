/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_finances

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import gparap.apps.personal_finances.ui.AddTransactionActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddTransactionActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<AddTransactionActivity>
    private lateinit var rootView: View

    @Before
    fun setUp() {
        //get the scenario for this activity
        activityScenario = ActivityScenario.launch(AddTransactionActivity::class.java)

        //get the top view for this activity
        activityScenario.onActivity { activity ->
            rootView = activity.window.decorView
        }
    }

    @Test
    fun isVisible_imageView_topBackgroundGradient() {
        onView(withId(R.id.imageView_topBackgroundGradient)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_appLogo() {
        onView(withId(R.id.imageView_appLogo)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_appTitle() {
        onView(withId(R.id.textView_appTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_transaction_type() {
        onView(withId(R.id.editText_transaction_type)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_transaction_quantity() {
        onView(withId(R.id.editText_transaction_quantity)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_transaction_date() {
        onView(withId(R.id.editText_transaction_date)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_transaction_details() {
        onView(withId(R.id.editText_transaction_details)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_add_transaction() {
        onView(withId(R.id.button_add_transaction)).check(matches(isDisplayed()))
    }

    @Test
    fun isCorrect_addTransaction() {
        onView(withId(R.id.editText_transaction_type)).perform(typeText("test transaction"))
        onView(withId(R.id.editText_transaction_quantity)).perform(typeText("100"))
        onView(withId(R.id.editText_transaction_date)).perform(typeText("2024/10/14"))
        onView(withId(R.id.editText_transaction_details)).perform(typeText("test details"))
        closeSoftKeyboard()
        onView(withId(R.id.button_add_transaction)).perform(click())
        onView(withText("Transaction added successfully..."))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        Thread.sleep(2000L) //wait for the Toast to disappear
    }
}