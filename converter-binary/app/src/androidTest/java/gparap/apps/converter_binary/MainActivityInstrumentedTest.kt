package gparap.apps.converter_binary

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by gparap on 2021-02-23.
 */
class MainActivityInstrumentedTest {
    @get:Rule
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    private var rootView: View? = null

    @Before
    fun setUp() {
        //get main activity decor view
        rule.scenario.onActivity { activity -> rootView = activity.window.decorView }

        //start main activity
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isWidgetVisible_editTextResult() {
        onView(withId(R.id.editTextResult)).check(matches(isDisplayed()))
    }

    @Test
    fun isWidgetVisible_buttonConvertToBinary() {
        onView(withId(R.id.buttonConvertToBinary)).check(matches(isDisplayed()))
    }

    @Test
    fun isWidgetVisible_buttonConvertToText() {
        onView(withId(R.id.buttonConvertToText)).check(matches(isDisplayed()))
    }

    @Test
    fun isWidgetVisible_buttonClear() {
        onView(withId(R.id.buttonClear)).check(matches(isDisplayed()))
    }

    @Test
    fun isInputEmpty_Click_buttonConvertToText() {
        onView(withId(R.id.buttonClear)).perform(click())
        onView(withId(R.id.buttonConvertToText)).perform(click())
        onView(withText(R.string.toast_EmptyInput))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isInputEmpty_Click_buttonConvertToBinary() {
        onView(withId(R.id.buttonClear)).perform(click())
        onView(withId(R.id.buttonConvertToBinary)).perform(click())
        onView(withText(R.string.toast_EmptyInput))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun inputIsNotBinary() {
        onView(withId(R.id.editTextResult)).perform(
            typeText("!1100111"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.buttonConvertToText)).perform(click())
        onView(withText(R.string.toast_WrongInput))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }
}