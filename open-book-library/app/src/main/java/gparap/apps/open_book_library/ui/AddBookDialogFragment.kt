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

import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import gparap.apps.open_book_library.R
import gparap.apps.open_book_library.data.AppDatabase
import gparap.apps.open_book_library.data.BookModel
import gparap.apps.open_book_library.services.AddBookDialogCallback

/**
 * This class handles the addition of a new book by the user.
 * The book exists in user's device library and the details are stored in a local database.
 * Thus, the added book is quickly available from the featured books fragment.
 */
class AddBookDialogFragment(private val bookTitle: String?, private val bookUrl: Uri?) :
    DialogFragment(), AddBookDialogCallback {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_book, null)

        //display the book title without the file extension (".pdf")
        val titleWidget = dialogView.findViewById<EditText>(R.id.bookmark_title)
        if (bookTitle.isNullOrEmpty()) {
            titleWidget.setText("")
        } else {
            titleWidget.setText(bookTitle.removeSuffix(".pdf"))
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("test")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->

                //get the book details from the user input
                val title = getWidgetText(dialogView, R.id.bookmark_title)
                val author = getWidgetText(dialogView, R.id.bookmark_author)
                val genre = getWidgetText(dialogView, R.id.bookmark_genre)
                val date = getWidgetText(dialogView, R.id.bookmark_date)
                val pages = getWidgetText(dialogView, R.id.bookmark_pages)
                val language = getWidgetText(dialogView, R.id.bookmark_language)
                val country = getWidgetText(dialogView, R.id.bookmark_country)
                val publisher = getWidgetText(dialogView, R.id.bookmark_publisher)
                val coverArtist = getWidgetText(dialogView, R.id.bookmark_cover_artist)

                //create book
                val book = BookModel(
                    title,
                    author,
                    genre,
                    date.toInt(),
                    pages.toInt(),
                    language,
                    country,
                    publisher,
                    "",
                    coverArtist,
                    "", //TODO: attributions in the end
                    "",//TODO: attributions in the end
                    false,
                    "",
                    bookUrl.toString()
                )

                //add book details to local database
                addBook(book)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()

        return dialog
    }

    override fun addBook(book: BookModel) {
        val database = context?.let { AppDatabase(it) }
        database?.insertBook(book)
    }

    private fun getWidgetText(dialog: View, widgetId: Int): String {
        val widget = dialog.findViewById<EditText>(widgetId)
        return widget.text.toString()
    }
}