package gparap.apps.horoscope.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.horoscope.R
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.horoscope", appContext.packageName)
    }

    @Test
    fun isVisible_spinner_zodiac_signs() {
        onView(withId(R.id.spinner_zodiac_signs)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_prompt_select_sign() {
        onView(withId(R.id.text_view_prompt_select_sign)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotVisible_scroll_view_horoscope() {
        onView(withId(R.id.scroll_view_horoscope)).check(matches(not(isDisplayed())))

        //!!! should put all tests of the scrollView's child views here for convenience
        onView(withId(R.id.image_view_zodiac_sign)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_date_range)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_horoscope)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_date)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_horoscope)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_luck)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_lucky_number)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_lucky_number)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_lucky_time)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_lucky_time)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_lucky_color)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_lucky_color)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_pair_sign)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_pair_sign)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isNotVisible_button_set_zodiac() {
        onView(withId(R.id.button_set_zodiac)).check(matches(not(isDisplayed())))
    }
}