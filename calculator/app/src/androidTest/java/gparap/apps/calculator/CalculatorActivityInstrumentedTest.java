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
package gparap.apps.calculator;

import android.os.RemoteException;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by gparap on 2021-01-31.
 */
public class CalculatorActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<CalculatorActivity> activityScenarioRule = new ActivityScenarioRule<>(CalculatorActivity.class);
    private View rootView = null;
    UiDevice device;    /* minSdkVersion 18 */

    @Before
    public void setUp() throws Exception {
        //get the top-level window decor view
        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<CalculatorActivity>() {
            @Override
            public void perform(CalculatorActivity activity) {
                rootView = activity.getWindow().getDecorView();
            }
        });

        /* minSdkVersion 18 */
        //get access to the device
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //launch calculator activity
        ActivityScenario.launch(CalculatorActivity.class);
    }

    @Test
    public void onCreate_DisplayResultScreen() {
        onView(withId(R.id.textViewDisplay)).check(matches(isDisplayed()));
    }

    @Test
    public void onCreate_DisplayNumberButtons() {
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
        onView(withId(R.id.button5)).check(matches(isDisplayed()));
        onView(withId(R.id.button6)).check(matches(isDisplayed()));
        onView(withId(R.id.button7)).check(matches(isDisplayed()));
        onView(withId(R.id.button8)).check(matches(isDisplayed()));
        onView(withId(R.id.button9)).check(matches(isDisplayed()));
        onView(withId(R.id.button0)).check(matches(isDisplayed()));
    }

    @Test
    public void onCreate_DisplayOperationButtons() {
        onView(withId(R.id.buttonMulti)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonMinus)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonPlus)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonModulo)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonPower)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonDiv)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonEquals)).check(matches(isDisplayed()));
    }

    @Test
    public void onCreate_Display_DeleteAndClear_Buttons() {
        onView(withId(R.id.buttonDelete)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonClear)).check(matches(isDisplayed()));
    }

    /* minSdkVersion 18 */
    @Test
    public void onSaveInstanceState_ChangeDeviceOrientation() {
        //type anything
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button0)).perform(click());
        onView(withId(R.id.buttonModulo)).perform(click());

        //change device orientation
        try {
            device.setOrientationLeft();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //test
        onView(withId(R.id.textViewDisplay)).check(matches(withText("10%")));
    }

    @Test
    public void onButtonClickNumber_Type1() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1")));
    }

    @Test
    public void onButtonClickNumber_Type2() {
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("2")));
    }

    @Test
    public void onButtonClickNumber_Type3() {
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("3")));
    }

    @Test
    public void onButtonClickNumber_Type4() {
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("4")));
    }

    @Test
    public void onButtonClickNumber_Type5() {
        onView(withId(R.id.button5)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("5")));
    }

    @Test
    public void onButtonClickNumber_Type6() {
        onView(withId(R.id.button6)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("6")));
    }

    @Test
    public void onButtonClickNumber_Type7() {
        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("7")));
    }

    @Test
    public void onButtonClickNumber_Type8() {
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("8")));
    }

    @Test
    public void onButtonClickNumber_Type9() {
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("9")));
    }

    @Test
    public void onButtonClickNumber_Type0_BeforeAnyNumber() {
        onView(withId(R.id.button0)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("0")));
    }

    @Test
    public void onButtonClickNumber_Type0_AfterAnyNumber() {
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.button0)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("90")));
    }

    @Test
    public void onButtonClickOperation_TypeMulptiply() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonMulti)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1*")));
    }

    @Test
    public void onButtonClickOperation_TypeMinus() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonMinus)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1-")));
    }

    @Test
    public void onButtonClickOperation_TypePlus() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonPlus)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1+")));
    }

    @Test
    public void onButtonClickOperation_TypeDivide() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonDiv)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1/")));
    }

    @Test
    public void onButtonClickOperation_DivisionByZero() {
        //divide by zero
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonDiv)).perform(click());
        onView(withId(R.id.button0)).perform(click());
        onView(withId(R.id.buttonEquals)).perform(click());

        //test and expect "0" and a Toast
        onView(withText(R.string.toast_divisionByZero))
                .inRoot(withDecorView(not(rootView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onButtonClickOperation_TypeModulo() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonModulo)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1%")));
    }

    @Test
    public void onButtonClickOperation_ModuloNegativeNumber() {
        //type a negative number
        onView(withId(R.id.buttonMinus)).perform(click());
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonEquals)).perform(click());

        //do the modulo
        onView(withId(R.id.buttonModulo)).perform(click());
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonEquals)).perform(click());

        //test and expect "0" and a Toast
        onView(withId(R.id.textViewDisplay)).check(matches(withText("0")));
        onView(withText(R.string.toast_givePositiveNumbers))
                .inRoot(withDecorView(not(rootView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onButtonClickOperation_TypePower() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonPower)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1^")));
    }

    @Test
    public void onButtonClickOperation_ChangeOperation() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonModulo)).perform(click());
        onView(withId(R.id.buttonDiv)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1/")));
    }

    @Test
    public void onButtonClickDelete() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.buttonDelete)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("12")));
    }

    @Test
    public void onButtonClickClear() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.buttonClear)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("0")));
    }

    @Test
    public void onButtonClickOperation_Equals() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonPlus)).perform(click());
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonEquals)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("2")));
    }

    @Test
    public void onButtonClickOperation_ScientificNumbersOperationsUnsupported() {
        //produce a scientific number
        for (int i=0; i<5; i++) {
            onView(withId(R.id.button9)).perform(click());
        }
        onView(withId(R.id.buttonMulti)).perform(click());
        for (int i=0; i<5; i++) {
            onView(withId(R.id.button9)).perform(click());
        }
        onView(withId(R.id.buttonEquals)).perform(click());

        //perform any operation with the scientific number
        onView(withId(R.id.buttonPlus)).perform(click());
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.buttonEquals)).perform(click());

        //test and expect "0" and a Toast
        onView(withId(R.id.textViewDisplay)).check(matches(withText("0")));
        onView(withText(R.string.toast_unsupportedOperation))
                .inRoot(withDecorView(not(rootView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onButtonClickDot_DisplayOnlyOneDot_FirstOperand() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonDot)).perform(click());

        //test another dot
        onView(withId(R.id.buttonDot)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1.")));
    }

    @Test
    public void onButtonClickDot_DisplayOnlyOneDot_SecondOperand() {
        //first operand is unimportant
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonDot)).perform(click());
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonDiv)).perform(click());

        //second operand
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.buttonDot)).perform(click());

        //test another dot
        onView(withId(R.id.buttonDot)).perform(click());
        onView(withId(R.id.textViewDisplay)).check(matches(withText("1.1/2.")));
    }
}