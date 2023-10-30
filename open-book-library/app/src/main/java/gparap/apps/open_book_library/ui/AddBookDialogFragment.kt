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
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import gparap.apps.open_book_library.R
import gparap.apps.open_book_library.data.BookModel
import gparap.apps.open_book_library.services.AddBookDialogCallback

/**
 * This class handles the addition of a new book by the user.
 * The book exists in user's device library and the details are stored in a local database.
 * Thus, the added book is quickly available from the featured books fragment.
 */
class AddBookDialogFragment : DialogFragment(), AddBookDialogCallback {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_book, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("test")
            .setView(dialogView)
            .setPositiveButton("OK") {_dialog, _which ->
                //get the book details from the user input TODO: details
                val titleWidget = dialogView.findViewById<EditText>(R.id.bookmark_title)
                val title = titleWidget.text.toString()

                //create test book
                val book = BookModel(title,"","",0,0,"","","","",""
                    ,"","",false,"","")
                addBook(book)
            }
            .setNegativeButton("Cancel") { _dialog, _which -> _dialog.dismiss()}
            .create()

        return dialog
    }

    override fun addBook(book: BookModel) {
        //DEBUG
        println(book.title)
    }
}