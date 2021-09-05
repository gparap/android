/*
 * Copyright 2021 gparap
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
package gparap.apps.calculator_tip

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onClickButtonTipPlusFive_addFiveToTip() {
        //get the old value of textViewTipPercentage
        val tipPercentageOld = getTextViewValueById(R.id.textViewTipPercentage)

        //add +5 to tip percentage
        onView(withId(R.id.buttonPlusFive)).perform(click())

        //get the new value of textViewTipPercentage
        val tipPercentageNew = getTextViewValueById(R.id.textViewTipPercentage)

        //test
        assert(tipPercentageNew.toInt() > tipPercentageOld.toInt()
        ) { "new value should be greater than old one when adding tip percentage" }
    }

    @Test
    fun onClickButtonTipMinusFive_subtractFiveFromTip() {
        //add some value to tip percentage
        onView(withId(R.id.buttonPlusFive)).perform(click())
        onView(withId(R.id.buttonPlusFive)).perform(click())
        onView(withId(R.id.buttonPlusFive)).perform(click())

        //get the old value of textViewTipPercentage
        val tipPercentageOld = getTextViewValueById(R.id.textViewTipPercentage)

        //subtract -5 to tip percentage
        onView(withId(R.id.buttonMinusFive)).perform(click())

        //get the new value of textViewTipPercentage
        val tipPercentageNew = getTextViewValueById(R.id.textViewTipPercentage)

        //test
        assert(tipPercentageNew.toInt() < tipPercentageOld.toInt()
        ) { "new value should be lesser than old one when subtracting tip percentage" }
    }

    @Test
    fun onClickButtonPersonsPlusOne_addOneToPersons() {
        //get the old value of textViewPersonsToSplit
        val personsToSlitOld = getTextViewValueById(R.id.textViewPersonsToSplit)

        //add 1 person
        onView(withId(R.id.buttonPlusOne)).perform(click())

        //get the new value of textViewPersonsToSplit
        val personsToSlitNew = getTextViewValueById(R.id.textViewPersonsToSplit)

        //test
        assert(personsToSlitNew.toInt() > personsToSlitOld.toInt()
        ) { "new value should be greater than old one when adding persons" }
    }

    @Test
    fun onClickButtonPersonsMinusOne_subtractOneFromPersons() {
        //add some persons
        onView(withId(R.id.buttonMinusOne)).perform(click())
        onView(withId(R.id.buttonMinusOne)).perform(click())
        onView(withId(R.id.buttonMinusOne)).perform(click())

        //get the old value of textViewPersonsToSplit
        val personsToSlitOld = getTextViewValueById(R.id.textViewPersonsToSplit)

        //subtract 1 person
        onView(withId(R.id.buttonMinusOne)).perform(click())

        //get the new value of textViewPersonsToSplit
        val personsToSlitNew = getTextViewValueById(R.id.textViewTipPercentage)

        //test
        assert(personsToSlitNew.toInt() < personsToSlitOld.toInt()
        ) { "new value should be lesser than old one when subtracting persons" }
    }

    @Test
    fun onInsertTextToInitialBill_changeValueOfTotalAmount() {
        //get the old value of total amount
        val totalAmountOld = getTextViewValueById(R.id.textViewAmountTotal)

        //insert value for initial bill
        onView(withId(R.id.editTextBill)).perform(typeText("100"))
        closeSoftKeyboard()

        //get the new value of total amount
        val totalAmountNew = getTextViewValueById(R.id.textViewAmountTotal)

        //test
        assert(
            totalAmountOld != totalAmountNew
        ) { "total amount should be different when initial bill changes" }
    }

    @Test
    fun onInsertTextToInitialBill_changeValueOfAmountPerPerson() {
        //get the old value of amount per person
        val amountPerPersonOld = getTextViewValueById(R.id.textViewAmountPerPerson)

        //insert value for initial bill
        onView(withId(R.id.editTextBill)).perform(typeText("100"))
        closeSoftKeyboard()

        //get the new value of amount per person
        val amountPerPersonNew = getTextViewValueById(R.id.textViewAmountPerPerson)

        //test
        assert(
            amountPerPersonOld != amountPerPersonNew
        ) { "amount per person should be different when initial bill changes" }
    }

    @Test
    fun onInsertTextToInitialBillAndAddTip_changeValueOfTipAmount() {
        //insert value for initial bill
        onView(withId(R.id.editTextBill)).perform(typeText("100"))
        closeSoftKeyboard()

        //get the old value of tip amount
        val tipAmountOld = getTextViewValueById(R.id.textViewAmountTip)

        //add some tip percentage
        onView(withId(R.id.buttonPlusFive)).perform(click())

        //get the new value of tip amount
        val tipAmountNew = getTextViewValueById(R.id.textViewAmountTip)

        //test
        assert(
            tipAmountOld != tipAmountNew
        ) { "tip amount should be different when tip percentage changes" }
    }

    @Test
    fun onInsertTextToInitialBillAndSubtractTip_changeValueOfTipAmount() {
        //insert value for initial bill
        onView(withId(R.id.editTextBill)).perform(typeText("100"))
        closeSoftKeyboard()

        //add same tip percentage so that we can subtract later
        onView(withId(R.id.buttonPlusFive)).perform(click())
        onView(withId(R.id.buttonPlusFive)).perform(click())
        onView(withId(R.id.buttonPlusFive)).perform(click())

        //get the old value of tip amount
        val tipAmountOld = getTextViewValueById(R.id.textViewAmountTip)

        //subtract some tip percentage
        onView(withId(R.id.buttonMinusFive)).perform(click())

        //get the new value of tip amount
        val tipAmountNew = getTextViewValueById(R.id.textViewAmountTip)

        //test
        assert(
            tipAmountOld != tipAmountNew
        ) { "tip amount should be different when tip percentage changes" }
    }

    @Test
    fun onInsertTextToInitialBillAndAddPerson_changeValueOfAmountPerPerson() {
        //insert value for initial bill
        onView(withId(R.id.editTextBill)).perform(typeText("100"))
        closeSoftKeyboard()

        //get the old value of amount per person
        val amountPerPersonOld = getTextViewValueById(R.id.textViewAmountPerPerson)

        //add some persons
        onView(withId(R.id.buttonPlusOne)).perform(click())
        onView(withId(R.id.buttonPlusOne)).perform(click())

        //get the new value of amount per person
        val amountPerPersonNew = getTextViewValueById(R.id.textViewAmountPerPerson)

        //test
        assert(
            amountPerPersonOld != amountPerPersonNew
        ) { "amount per person should be different when persons to split changes" }
    }

    @Test
    fun onInsertTextToInitialBillAndSubtractPerson_changeValueOfAmountPerPerson() {
        //insert value for initial bill
        onView(withId(R.id.editTextBill)).perform(typeText("100"))
        closeSoftKeyboard()

        //add some persons so that we can subtract later
        onView(withId(R.id.buttonPlusOne)).perform(click())
        onView(withId(R.id.buttonPlusOne)).perform(click())
        onView(withId(R.id.buttonPlusOne)).perform(click())

        //get the old value of amount per person
        val amountPerPersonOld = getTextViewValueById(R.id.textViewAmountPerPerson)

        //subtract a person
        onView(withId(R.id.buttonMinusOne)).perform(click())

        //get the new value of amount per person
        val amountPerPersonNew = getTextViewValueById(R.id.textViewAmountPerPerson)

        //test
        assert(
            amountPerPersonOld != amountPerPersonNew
        ) { "amount per person should be different when persons to split change" }
    }

    private fun getTextViewValueById(id: Int) : String {
        var textView: TextView? = null
        activityScenario.onActivity {
            textView = it.findViewById<TextView>(id)
        }
        return textView?.text.toString()
    }

}