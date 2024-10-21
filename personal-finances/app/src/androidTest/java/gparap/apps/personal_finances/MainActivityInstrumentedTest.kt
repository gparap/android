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

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import gparap.apps.personal_finances.ui.AllTransactionsActivity
import junit.framework.TestCase.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var activityScenarioAllTransactions: ActivityScenario<AllTransactionsActivity>

    @Before
    fun setUp() {
        //get the scenario for this activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
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
    fun isVisible_textView_sectionTitle_current() {
        onView(withId(R.id.textView_sectionTitle_current)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_1_title() {
        onView(withId(R.id.textView_section_1_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_1_background() {
        onView(withId(R.id.imageView_section_1_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_2_title() {
        onView(withId(R.id.textView_section_2_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_2_background() {
        onView(withId(R.id.imageView_section_2_background`)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_3_title() {
        onView(withId(R.id.textView_section_3_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_3_background() {
        onView(withId(R.id.imageView_section_3_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_4_title() {
        onView(withId(R.id.textView_section_4_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_4_background() {
        onView(withId(R.id.imageView_section_4_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isCorrect_redirectToAddTransactionActivity() {
        onView(withId(R.id.fab_addTransaction)).perform(click())
        onView(withId(R.id.layout_activity_add_transaction)).check(matches(isDisplayed()))
    }

    @Test
    fun isCorrect_addTransaction() {
        var transactionsCountBefore = 0
        var transactionsCountAfter = 0

        //get all the transactions before adding a new one
        onView(withId(R.id.imageView_all_transactions_background)).perform(click())
        activityScenarioAllTransactions = ActivityScenario.launch(AllTransactionsActivity::class.java)
        activityScenarioAllTransactions.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_allTransactions)
            transactionsCountBefore = recyclerView.adapter?.itemCount ?: 0
        }

        //add a new transaction
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab_addTransaction)).perform(click())
        onView(withId(R.id.editText_transaction_type)).perform(typeText("test transaction"))
        onView(withId(R.id.editText_transaction_quantity)).perform(typeText("100"))
        onView(withId(R.id.editText_transaction_date)).perform(typeText("2024-12-31"))
        onView(withId(R.id.editText_transaction_details)).perform(typeText("test details"))
        closeSoftKeyboard()
        onView(withId(R.id.button_add_transaction)).perform(click())

        //get all the transactions after adding a new one
        activityScenarioAllTransactions = ActivityScenario.launch(AllTransactionsActivity::class.java)
        activityScenarioAllTransactions.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_allTransactions)
            transactionsCountAfter = recyclerView.adapter?.itemCount ?: 0
        }

        //assert that recycler view has one more transaction
        assertFalse(transactionsCountBefore == 0 && transactionsCountAfter == 0)
        assert(transactionsCountBefore != transactionsCountAfter)
    }
}