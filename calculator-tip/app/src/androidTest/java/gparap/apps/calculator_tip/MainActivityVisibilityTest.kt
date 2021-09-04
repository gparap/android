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

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

class MainActivityVisibilityTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }
    
    @Test
    fun isVisible_buttonMinusFive(){
        onView(withId(R.id.buttonMinusFive)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonMinusOne(){
        onView(withId(R.id.buttonMinusOne)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonPlusOne(){
        onView(withId(R.id.buttonPlusOne)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonPlusFive(){
        onView(withId(R.id.buttonPlusFive)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextBill(){
        onView(withId(R.id.editTextBill)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewBillLabel(){
        onView(withId(R.id.textViewBillLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewTipPercentageLabel(){
        onView(withId(R.id.textViewTipPercentageLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAmountTipLabel(){
        onView(withId(R.id.textViewAmountTipLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAmountTotalLabel(){
        onView(withId(R.id.textViewAmountTotalLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAmountPerPersonLabel(){
        onView(withId(R.id.textViewAmountPerPersonLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAmountTip(){
        onView(withId(R.id.textViewAmountTip)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAmountTotal(){
        onView(withId(R.id.textViewAmountTotal)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewTipPercentage(){
        onView(withId(R.id.textViewTipPercentage)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewPersonsToSplitLabel(){
        onView(withId(R.id.textViewPersonsToSplitLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewPersonsToSplit(){
        onView(withId(R.id.textViewPersonsToSplit)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAmountPerPerson(){
        onView(withId(R.id.textViewAmountPerPerson)).check(matches(isDisplayed()))
    }
}