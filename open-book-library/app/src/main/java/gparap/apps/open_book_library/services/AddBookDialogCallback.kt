package gparap.apps.open_book_library.services

import gparap.apps.open_book_library.data.BookModel

interface AddBookDialogCallback {
    fun addBook(book: BookModel)
}