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
package gparap.apps.open_book_library.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.open_book_library.R
import gparap.apps.open_book_library.data.BookModel
import gparap.apps.open_book_library.ui.FeaturedBooksFragmentDirections

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var books: ArrayList<BookModel> = ArrayList()
    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun addBooks(books: ArrayList<BookModel>) {
        this.books = books
        notifyDataSetChanged()
    }

    /** Describes the RecyclerView book item. */
    class BookViewHolder(itemView: View) : ViewHolder(itemView) {
        val bookCover: ImageView = itemView.findViewById(R.id.cardview_book_cover)
        val bookTitle: TextView = itemView.findViewById(R.id.cardview_book_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        //get the target context
        context = parent.context

        //inflate view from xml
        val view: View = LayoutInflater.from(context)
            .inflate(context.resources.getLayout(R.layout.card_view_book_item), parent, false)

        //create & return item view
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        //show book cover
        val assetManager: AssetManager = context.assets
        val inputStream = assetManager.open(books[position].coverUrl)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        holder.bookCover.setImageDrawable(bitmap.toDrawable(context.resources))

        //show book title
        holder.bookTitle.text = books[position].title

        //register a callback to display the book details in another fragment
        holder.itemView.setOnClickListener {
            //pass the book details as fragment arguments to a navigation-generated object
            val directions = FeaturedBooksFragmentDirections.actionFeaturedBooksFragmentToBookDetailsFragment()
            directions.argsBookTitle = books[position].title
            directions.argsBookAuthor = books[position].author
            directions.argsBookGenre = books[position].genre
            directions.argsBookDate = books[position].date
            directions.argsBookPages = books[position].pages
            directions.argsBookLanguage = books[position].language
            directions.argsBookCountry = books[position].country
            directions.argsBookPublisher = books[position].publisher
            directions.argsBookCoverUrl = books[position].coverUrl
            directions.argsBookCoverArtist = books[position].coverArtist
            //TODO: attribution details

            //redirect to the book details fragment
            val navController = holder.itemView.findNavController()
            navController.navigate(directions)
        }
    }
}