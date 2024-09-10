package gparap.apps.memory_matcher

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_grid() {
        onView(withId(R.id.layout_memory_grid)).check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Run only in portrait mode")
    fun areVisible_gridCards_2x4() {
        onView(withId(R.id.grid_row_0_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_2_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_2_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_3_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_3_col_1)).check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Run only in landscape mode")
    fun areVisible_gridCards_4x2_land() {
        onView(withId(R.id.grid_row_0_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_2)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_3)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_2)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_3)).check(matches(isDisplayed()))
    }
}