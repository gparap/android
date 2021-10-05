package gparap.apps.pdf_creator

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_editTextInputPDF() {
        onView(withId(R.id.editTextInputPDF)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonCreatePDF() {
        onView(withId(R.id.buttonCreatePDF)).check(matches(isDisplayed()))
    }
}