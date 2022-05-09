package gparap.apps.horoscope.ui

import android.content.Context
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.horoscope.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //get context
        activityScenario.onActivity {
            context = it.baseContext
        }
    }

    @Test
    @SmallTest
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.horoscope", appContext.packageName)
    }

    @Test
    @SmallTest
    fun isVisible_spinner_zodiac_signs() {
        onView(withId(R.id.spinner_zodiac_signs)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_text_view_prompt_select_sign() {
        onView(withId(R.id.text_view_prompt_select_sign)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
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
    @SmallTest
    fun spinnerSelectZodiacSign_correctSelected() {
        //Aries
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Aries))).perform(click())
        onView(withText(R.string.date_range_Aries)).check(matches(isDisplayed()))
        //Taurus
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Taurus))).perform(click())
        onView(withText(R.string.date_range_Taurus)).check(matches(isDisplayed()))
        //Gemini
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Gemini))).perform(click())
        onView(withText(R.string.date_range_Gemini)).check(matches(isDisplayed()))
        //Cancer
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Cancer))).perform(click())
        onView(withText(R.string.date_range_Cancer)).check(matches(isDisplayed()))
        //Leo
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Leo))).perform(click())
        onView(withText(R.string.date_range_Leo)).check(matches(isDisplayed()))
        //Virgo
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Virgo))).perform(click())
        onView(withText(R.string.date_range_Virgo)).check(matches(isDisplayed()))
        //Libra
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Libra))).perform(click())
        onView(withText(R.string.date_range_Libra)).check(matches(isDisplayed()))
        //Scorpio
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Scorpio))).perform(click())
        onView(withText(R.string.date_range_Scorpio)).check(matches(isDisplayed()))
        //Sagittarius
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Sagittarius))).perform(click())
        onView(withText(R.string.date_range_Sagittarius)).check(matches(isDisplayed()))
        //Capricorn
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Capricorn))).perform(click())
        onView(withText(R.string.date_range_Capricorn)).check(matches(isDisplayed()))
        //Aquarius
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Aquarius))).perform(click())
        onView(withText(R.string.date_range_Aquarius)).check(matches(isDisplayed()))
        //Pisces
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Pisces))).perform(click())
        onView(withText(R.string.date_range_Pisces)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun spinnerNothingSelected_doNothing() {
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_prompt_select_spinner))).perform(click())
        onView(withText(R.string.text_prompt_select)).check(matches(isDisplayed()))
    }

    @Test
    @LargeTest
    fun spinnerSelectZodiacSign_horoscopeDetailsAreNotNull() {
        //choose whatever zodiac sign
        onView(withId(R.id.spinner_zodiac_signs)).perform(click())
        onData(`is`(context.resources.getString(R.string.text_zodiac_Capricorn))).perform(click())
        Thread.sleep(1667)

        //test if data are fetched from web service
        activityScenario.onActivity {
            val date = it.findViewById<TextView>(R.id.text_view_date)
            assert(!date.text.isNullOrEmpty())
            val horoscope = it.findViewById<TextView>(R.id.text_view_horoscope)
            assert(!horoscope.text.isNullOrEmpty())
            val number = it.findViewById<TextView>(R.id.text_view_lucky_number)
            assert(!number.text.isNullOrEmpty())
            val time = it.findViewById<TextView>(R.id.text_view_lucky_time)
            assert(!time.text.isNullOrEmpty())
            val color = it.findViewById<TextView>(R.id.text_view_lucky_color)
            assert(!color.text.isNullOrEmpty())
            val pair = it.findViewById<TextView>(R.id.text_view_pair_sign)
            assert(!pair.text.isNullOrEmpty())
        }
    }
}