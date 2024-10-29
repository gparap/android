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

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
    fun isVisible_textView_account_balance_title() {
        onView(withId(R.id.textView_account_balance_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_account_balance_background() {
        onView(withId(R.id.imageView_account_balance_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_all_transactions_title() {
        onView(withId(R.id.textView_all_transactions_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_all_transactions_background() {
        onView(withId(R.id.imageView_all_transactions_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_top_up_transactions_title() {
        onView(withId(R.id.textView_top_up_transactions_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_top_up_transactions_background() {
        onView(withId(R.id.imageView_top_up_transactions_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_expense_transactions_title() {
        onView(withId(R.id.textView_expense_transactions_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_expense_transactions_background() {
        onView(withId(R.id.imageView_expense_transactions_background)).check(matches(isDisplayed()))
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
        transactionsCountBefore = getTransactionsCount()

        //add a new transaction
        ActivityScenario.launch(MainActivity::class.java)
        addTransaction("100")

        //get all the transactions after adding a new one
        activityScenarioAllTransactions = ActivityScenario.launch(AllTransactionsActivity::class.java)
        transactionsCountAfter = getTransactionsCount()

        //assert that recycler view has one more transaction
        assertFalse(transactionsCountBefore == 0 && transactionsCountAfter == 0)
        assert(transactionsCountBefore != transactionsCountAfter)
    }

    @Test
    fun isCorrect_addTopUpTransaction() {
        TODO("Will be implemented after delete transaction functionality completes.")
    }

    @Test
    fun isCorrect_addExpenseTransaction() {
        TODO("Will be implemented after delete transaction functionality completes.")
    }

    @Test
    fun isCorrect_updateAppSectionTitle() {
        //we are in dashboard
        onView(withText(R.string.section_text_dashboard)).check(matches(isDisplayed()))

        //we are in "all transactions" activity
        onView(withText(R.string.desc_all_transactions)).perform(click())
        onView(withText(R.string.desc_all_transactions)).check(matches(isDisplayed()))
        pressBack()

        //we are in "top-up transactions" activity
        onView(withText(R.string.desc_top_up_transactions)).perform(click())
        onView(withText(R.string.desc_top_up_transactions)).check(matches(isDisplayed()))
        pressBack()

        //we are in "expense transactions" activity
        onView(withText(R.string.desc_expense_transactions)).perform(click())
        onView(withText(R.string.desc_expense_transactions)).check(matches(isDisplayed()))
    }

    private fun getTransactionsCount() : Int {
        var count = 0
        activityScenarioAllTransactions.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView_allTransactions)
            count = recyclerView.adapter?.itemCount ?: 0
        }
        return count
    }

    private fun addTransaction(quantity: String) {
        onView(withId(R.id.fab_addTransaction)).perform(click())
        onView(withId(R.id.editText_transaction_type)).perform(typeText("test transaction"))
        onView(withId(R.id.editText_transaction_quantity)).perform(typeText(quantity))
        onView(withId(R.id.editText_transaction_date)).perform(typeText("2024-12-31"))
        onView(withId(R.id.editText_transaction_details)).perform(typeText("test details"))
        closeSoftKeyboard()
        onView(withId(R.id.button_add_transaction)).perform(click())
    }
}