package gparap.apps.piano

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    @SmallTest
    fun isVisible_constraintLayoutPianoTop() {
        onView(withId(R.id.constraintLayoutPianoTop)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_constraintLayoutPianoBottom() {
        onView(withId(R.id.constraintLayoutPianoBottom)).check(matches(isDisplayed()))
    }
}