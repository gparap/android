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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gparap.apps.open_book_library.R

class OpenLibraryFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_library, container, false)
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_book_categories, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_book_category_ancient_classics -> TODO("not implemented yet.")
            R.id.menu_book_category_biography -> TODO("not implemented yet.")
            R.id.menu_book_category_folklore -> TODO("not implemented yet.")
            R.id.menu_book_category_history -> TODO("not implemented yet.")
            R.id.menu_book_category_non_fiction -> TODO("not implemented yet.")
            R.id.menu_book_category_novel -> TODO("not implemented yet.")
            R.id.menu_book_category_poetry -> TODO("not implemented yet.")
            R.id.menu_book_category_philosophy -> TODO("not implemented yet.")
            R.id.menu_book_category_science_fiction -> TODO("not implemented yet.")
            R.id.menu_book_category_short_stories -> TODO("not implemented yet.")
        }
        return super.onOptionsItemSelected(item)
    }

}