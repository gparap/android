/*
 * Copyright 2021-2023 gparap
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
package gparap.apps.converter_currency

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by gparap on 2021-03-01.
 */
class WidgetsFunctionalityInstrumentedTest {
    @get:Rule
    val rule = ActivityScenarioRule(ConverterActivity::class.java)
    private var context: Context? = null
    private val euroIndex = 8
    private val euroName = "Euro"
    private val kunaIndex = 11
    private val roubleIndex = 26

    @Before
    fun setUp() {
        //get base context
        rule.scenario.onActivity { activity -> context = activity.baseContext }

        //launch activity
        ActivityScenario.launch(ConverterActivity::class.java)
    }

    @Test
    fun edit_Amount() {
        val amount = "100"
        onView(withId(R.id.editTextAmount)).perform(typeText(amount))
        onView(withId(R.id.editTextAmount)).check(matches(withText(amount)))
    }

    @Test
    fun displayCurrencyName_FromCurrencySpinner() {
        selectCurrencyOnSpinner(R.id.spinnerFromCurrency, euroIndex)
        onView(withId(R.id.textViewLabelFromCurrency)).check(matches(withText(euroName)))
    }

    @Test
    fun displayCurrencyName_ToCurrencySpinner() {
        selectCurrencyOnSpinner(R.id.spinnerToCurrency, euroIndex)
        onView(withId(R.id.textViewLabelToCurrency)).check(matches(withText(euroName)))
    }

    @Test
    fun onCroatianKunaSelected_showTextMessage() {
        selectCurrencyOnSpinner(R.id.spinnerFromCurrency, euroIndex)    //select 1st currency
        selectCurrencyOnSpinner(R.id.spinnerToCurrency,kunaIndex)       //select 2nd currency
        onView(withId(R.id.buttonConvert)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText(R.string.text_unsupported_currency_HRK)))
        println(R.string.text_unsupported_currency_HRK)
    }

    @Test
    fun onRussianRoubleSelected_showTextMessage() {
        selectCurrencyOnSpinner(R.id.spinnerFromCurrency, euroIndex)    //select 1st currency
        selectCurrencyOnSpinner(R.id.spinnerToCurrency,roubleIndex)     //select 2nd currency
        onView(withId(R.id.buttonConvert)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText(R.string.text_unsupported_currency_RUB)))
    }

    private fun selectCurrencyOnSpinner(spinnerId: Int, currencyIndex: Int) {
        onView(withId(spinnerId)).perform(click())
        onData(`is`(context?.resources?.getStringArray(R.array.currencyCodes)?.get(currencyIndex))).perform(click())
    }
}