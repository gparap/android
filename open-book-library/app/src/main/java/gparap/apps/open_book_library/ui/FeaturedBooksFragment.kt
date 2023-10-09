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
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.open_book_library.R
import gparap.apps.open_book_library.adapters.BookAdapter
import gparap.apps.open_book_library.data.BookModel

class FeaturedBooksFragment : Fragment() {
    private lateinit var books: ArrayList<BookModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create the list of the app's default featured books TODO: fill in the attribution details
        books = ArrayList<BookModel>().apply {
            this.add(BookModel("Autobiography of a Yogi", "Paramahansa Yogananda", "Autobiography", 1946, 758, "Hindi, English", "India and the United States", "Philosophical Library", "Autobiography-of-a-Yogi.jpg", "", "", "", true, "Autobiography_of_a_Yogi.pdf", ""))
            this.add(BookModel("Oliver Twist (1838) Volume 1", "Charles Dickens", "novel", 1838, 284, "English", "England", "Bentley's Miscellany", "Olivertwist_front.jpg", "George Cruikshank", "", "", true, "Oliver_Twist_(1838)_Volume_1.pdf", ""))
            this.add(BookModel("Oliver Twist (1838) Volume 2", "Charles Dickens", "novel", 1838, 257, "English", "England", "Bentley's Miscellany", "Olivertwist_front.jpg", "George Cruikshank", "", "", true, "Oliver_Twist_(1838)_Volume_2.pdf", ""))
            this.add(BookModel("Oliver Twist (1838) Volume 3", "Charles Dickens", "novel", 1838, 272, "English", "England", "Bentley's Miscellany", "Olivertwist_front.jpg", "George Cruikshank", "", "", true, "Oliver_Twist_(1838)_Volume_3.pdf", ""))
            this.add(BookModel("Pip", "Ian Hay", "", 1907, 373, "English", "United Kingdom", "", "Pip_(novel).jpg", "", "", "", true, "Pip.pdf", ""))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_featured_books, container, false)

        //create the adapter with the featured books
        val booksAdapter = BookAdapter()
        booksAdapter.addBooks(books)

        //setup RecyclerView with BookAdapter
        val recyclerViewBooks = fragmentView.findViewById<RecyclerView>(R.id.recycler_view_featured_books)
        recyclerViewBooks?.layoutManager = LinearLayoutManager(fragmentView.context)
        recyclerViewBooks?.adapter = booksAdapter

        //return the layout for this fragment
        return fragmentView
    }
}