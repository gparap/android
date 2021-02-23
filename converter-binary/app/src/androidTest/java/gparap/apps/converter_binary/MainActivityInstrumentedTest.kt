package gparap.apps.converter_binary

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by gparap on 2021-02-23.
 */
class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)   //start main activity
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
}