package gparap.games.pong

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
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
        assertEquals("gparap.games.pong", appContext.packageName)
    }

    @Test
    fun isVisible_buttonPlay() {
        onView(withId(R.id.buttonPlay)).check(matches(isDisplayed()))
    }

    @Test
    fun onButtonPlayClick_gotoPongActivity() {
        onView(withId(R.id.buttonPlay)).perform(click())
        onView(withId(R.id.layout_activity_pong)).check(matches(isDisplayed()))
    }
}