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
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeaturedBooksFragmentInstrumentedTest {
    private lateinit var fragmentScenario: FragmentScenario<FeaturedBooksFragment>

    @Before
    fun setUp() {
        //launch fragment
        fragmentScenario = FragmentScenario.launchInContainer(FeaturedBooksFragment::class.java)
    }

    @Test
    fun onLoad_assertFeaturedBooksNotZero() {
        fragmentScenario.onFragment {
            val recyclerView =
                it.view?.findViewById<RecyclerView>(gparap.apps.open_book_library.R.id.recycler_view_featured_books)
            assert(recyclerView?.adapter?.itemCount!! > 0)
        }
    }
}