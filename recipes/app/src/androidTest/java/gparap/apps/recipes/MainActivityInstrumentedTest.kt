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

    @Test
    fun isVisible_homeFragment_text_view_random_recipe() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.text_view_random_recipe)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_card_view_random_recipe() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.card_view_random_recipe)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_image_view_random_recipe() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.image_view_random_recipe)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_text_view_featured_recipes() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.text_view_featured_recipes)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_recycler_view_featured_recipes() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.recycler_view_featured_recipes)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_recycle_view_recipe_categories() {
        navigateToFragment(R.id.categoriesFragment)
        onView(withId(R.id.recycle_view_recipe_categories)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_recycle_view_favorite_recipes() {
        navigateToFragment(R.id.favoritesFragment)
        onView(withId(R.id.recycle_view_favorite_recipes)).check(matches(isDisplayed()))
    }

    private fun navigateToFragment(fragmentId: Int) {
        onView(withId(fragmentId)).perform(click())
    }
}