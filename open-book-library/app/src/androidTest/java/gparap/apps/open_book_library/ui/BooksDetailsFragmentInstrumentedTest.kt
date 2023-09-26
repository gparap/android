/*
 * Copyright 2023 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.open_book_library.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import gparap.apps.open_book_library.R

@RunWith(JUnit4::class)
class BooksDetailsFragmentInstrumentedTest {
    @Before
    fun setUp() {
        FragmentScenario.launchInContainer(BookDetailsFragment::class.java)
    }

    @Test
    fun isVisible_image_view_book_cover() {
        onView(withId(R.id.image_view_book_cover)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_title_label() {
        onView(withId(R.id.text_view_book_title_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_title() {
        onView(withId(R.id.text_view_book_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_author_label() {
        onView(withId(R.id.text_view_book_author_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_author() {
        onView(withId(R.id.text_view_book_author)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_genre_label() {
        onView(withId(R.id.text_view_book_genre_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_genre() {
        onView(withId(R.id.text_view_book_genre)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_date_label() {
        onView(withId(R.id.text_view_book_date_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_date() {
        onView(withId(R.id.text_view_book_date)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_pages_label() {
        onView(withId(R.id.text_view_book_pages_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_pages() {
        onView(withId(R.id.text_view_book_pages)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_language_label() {
        onView(withId(R.id.text_view_book_language_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_language() {
        onView(withId(R.id.text_view_book_language)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_country_label() {
        onView(withId(R.id.text_view_book_country_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_country() {
        onView(withId(R.id.text_view_country)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_publisher_label() {
        onView(withId(R.id.text_view_book_publisher_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_publisher() {
        onView(withId(R.id.text_view_publisher)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_book_cover_artist_label() {
        onView(withId(R.id.text_view_book_cover_artist_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_cover_url() {
        onView(withId(R.id.text_view_cover_url)).check(matches(isDisplayed()))
    }
}