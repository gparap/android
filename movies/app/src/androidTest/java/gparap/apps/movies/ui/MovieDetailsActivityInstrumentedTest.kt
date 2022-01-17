package gparap.apps.movies.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import gparap.apps.movies.R
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class MovieDetailsActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MovieDetailsActivity::class.java)
    }

    @Test
    fun isVisible_image_view_movie_details() {
        onView(withId(R.id.image_view_movie_details)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_movie_title() {
        onView(withId(R.id.text_view_movie_title)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_summary() {
        onView(withId(R.id.text_view_movie_summary)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_details_label() {
        onView(withId(R.id.text_view_movie_details_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isVisible_text_view_movie_director_label() {
        onView(withId(R.id.text_view_movie_director_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_director() {
        onView(withId(R.id.text_view_movie_director)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_cast_label() {
        onView(withId(R.id.text_view_movie_cast_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_cast() {
        onView(withId(R.id.text_view_movie_cast)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_writer_label() {
        onView(withId(R.id.text_view_movie_writer_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_writer() {
        onView(withId(R.id.text_view_movie_writer)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_producer_label() {
        onView(withId(R.id.text_view_movie_producer_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_producer() {
        onView(withId(R.id.text_view_movie_producer)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_photography_label() {
        onView(withId(R.id.text_view_movie_photography_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_photography() {
        onView(withId(R.id.text_view_movie_photography)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_editor_label() {
        onView(withId(R.id.text_view_movie_editor_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isVisible_text_view_movie_editor() {
        onView(withId(R.id.text_view_movie_editor)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_music_label() {
        onView(withId(R.id.text_view_movie_music_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_music() {
        onView(withId(R.id.text_view_movie_music)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_publisher_label() {
        onView(withId(R.id.text_view_movie_publisher_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_publisher() {
        onView(withId(R.id.text_view_movie_publisher)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_release_year_label() {
        onView(withId(R.id.text_view_movie_release_year_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_release_year() {
        onView(withId(R.id.text_view_movie_release_year)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_release_country_label() {
        onView(withId(R.id.text_view_movie_release_country_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isVisible_text_view_movie_release_country() {
        onView(withId(R.id.text_view_movie_release_country)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_language_label() {
        onView(withId(R.id.text_view_movie_language_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_language() {
        onView(withId(R.id.text_view_movie_language)).check(matches(not(isDisplayed())))

    }

    @Test
    fun isVisible_text_view_movie_film_time_label() {
        onView(withId(R.id.text_view_movie_film_time_label)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_text_view_movie_film_time() {
        onView(withId(R.id.text_view_movie_film_time)).check(matches(not(isDisplayed())))

    }
}