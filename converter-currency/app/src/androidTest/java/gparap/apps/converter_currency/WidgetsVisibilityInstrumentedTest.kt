package gparap.apps.converter_currency

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

/**
 * Created by gparap on 2021-02-28.
 */
class WidgetsVisibilityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(ConverterActivity::class.java)
    }

    @Test
    fun widgetIsDisplayed_textViewLabelFromCurrencyCode() {
        onView(withId(R.id.textViewLabelFromCurrencyCode)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsDisplayed_textViewLabelToCurrencyCode() {
        onView(withId(R.id.textViewLabelToCurrencyCode)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsDisplayed_textViewLabelFromCurrency() {
        onView(withId(R.id.textViewLabelFromCurrency)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsDisplayed_textViewLabelToCurrency() {
        onView(withId(R.id.textViewLabelToCurrency)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsDisplayed_spinnerFromCurrency() {
        onView(withId(R.id.spinnerFromCurrency)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsDisplayed_spinnerToCurrency() {
        onView(withId(R.id.spinnerToCurrency)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsDisplayed_textViewResult() {
        //result has any value
        onView(withId(R.id.buttonConvert)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetNotIsDisplayed_textViewResult() {
        //result is empty on app first load
        onView(withId(R.id.textViewResult)).check(matches(not(isDisplayed())))
    }

    @Test
    fun widgetIsDisplayed_buttonConvert() {
        onView(withId(R.id.buttonConvert)).check(matches(isDisplayed()))
    }

    @Test
    fun widgetIsNotDisplayed_progressBar() {
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }
}