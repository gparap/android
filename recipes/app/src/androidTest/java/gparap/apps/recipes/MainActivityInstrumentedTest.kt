package gparap.apps.recipes

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
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
    fun bottomNavigation_navigateToHomeFragment() {
        //navigate to another fragment first
        onView(withId(R.id.categoriesFragment)).perform(click())

        //navigate to home
        onView(withId(R.id.homeFragment)).perform(click())
        onView(withId(R.id.layout_fragment_home)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_navigateToCategoriesFragment() {
        //navigate to home fragment first
        onView(withId(R.id.homeFragment)).perform(click())

        //navigate to categories
        onView(withId(R.id.categoriesFragment)).perform(click())
        onView(withId(R.id.layout_fragment_categories)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_navigateToFavoritesFragment() {
        //navigate to home fragment first
        onView(withId(R.id.homeFragment)).perform(click())

        //navigate to favorites
        onView(withId(R.id.favoritesFragment)).perform(click())
        onView(withId(R.id.layout_fragment_favorites)).check(matches(isDisplayed()))
    }
}