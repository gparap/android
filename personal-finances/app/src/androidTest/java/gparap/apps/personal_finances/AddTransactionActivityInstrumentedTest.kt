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

import android.content.Intent
import android.view.View
import androidx.room.Ignore
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
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.personal_finances.ui.AddTransactionActivity
import gparap.apps.personal_finances.utils.AppConstants
import gparap.apps.personal_finances.utils.TransactionType
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
    fun isVisible_textView_transaction_date() {
        onView(withId(R.id.textView_transaction_date)).check(matches(isDisplayed()))
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
    @Ignore
    fun isCorrect_addTransaction() {
        enterTestTransaction(isQuantityPositive = true)
        onView(withText(R.string.toast_add_transaction_success))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        Thread.sleep(2000L) //wait for the Toast to disappear
    }

    @Test
    @Ignore
    fun isErroneous_addTopUpTransaction() {
        //get the context of this activity
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        //start a new scenario with intent
        activityScenario.close()
        val intent = Intent(context, AddTransactionActivity::class.java)
        intent.putExtra(AppConstants.INTENT_EXTRA_TRANSACTION_TYPE, TransactionType.TOP_UP)
        ActivityScenario.launch<AddTransactionActivity>(intent)

        //add transaction
        enterTestTransaction(isQuantityPositive = false)
        onView(withText(R.string.toast_add_transaction_require_positive))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        Thread.sleep(2000L) //wait for the Toast to disappear
    }

    @Test
    @Ignore
    fun isErroneous_addTExpenseTransaction() {
        //get the context of this activity
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        //start a new scenario with intent
        activityScenario.close()
        val intent = Intent(context, AddTransactionActivity::class.java)
        intent.putExtra(AppConstants.INTENT_EXTRA_TRANSACTION_TYPE, TransactionType.EXPENSES)
        ActivityScenario.launch<AddTransactionActivity>(intent)

        //add transaction
        enterTestTransaction(isQuantityPositive = true)
        onView(withText(R.string.toast_add_transaction_require_negative))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        Thread.sleep(2000L) //wait for the Toast to disappear

    }

    private fun enterTestTransaction(isQuantityPositive: Boolean) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var quantity = "-100"
        if (isQuantityPositive) {
            quantity = "100"
        }
        onView(withId(R.id.editText_transaction_type)).perform(typeText("test transaction"))
        onView(withId(R.id.editText_transaction_quantity)).perform(typeText(quantity))
        onView(withId(R.id.textView_transaction_date)).perform(click())
        onView(withText(context.resources.getString(android.R.string.ok))).perform(click())
        onView(withId(R.id.editText_transaction_details)).perform(typeText("test details"))
        closeSoftKeyboard()
        onView(withId(R.id.button_add_transaction)).perform(click())
    }
}