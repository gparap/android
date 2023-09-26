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

        //create the list of the app's default featured books TODO: fill in the rest of the book details
        books = ArrayList<BookModel>().apply {
            this.add(BookModel("Autobiography of a Yogi", "", "", 0, 0, "", "", "", "Autobiography-of-a-Yogi.jpg", "", "", ""))
            this.add(BookModel("Oliver Twist (1838) Volume 1", "", "", 0, 0, "", "", "", "Olivertwist_front.jpg", "", "", ""))
            this.add(BookModel("Oliver Twist (1838) Volume 2", "", "", 0, 0, "", "", "", "Olivertwist_front.jpg", "", "", ""))
            this.add(BookModel("Oliver Twist (1838) Volume 3", "", "", 0, 0, "", "", "", "Olivertwist_front.jpg", "", "", ""))
            this.add(BookModel("Pip", "", "", 0, 0, "", "", "", "Pip_(novel).jpg", "", "", ""))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_featured_books, container, false)

        //create the adapter with the featured books
        val booksAdapter = BookAdapter().apply {
            addBooks(books)
        }

        //setup RecyclerView with BookAdapter
        val recyclerViewBooks = fragmentView.findViewById<RecyclerView>(R.id.recycler_view_featured_books)
        recyclerViewBooks?.layoutManager = LinearLayoutManager(fragmentView.context)
        recyclerViewBooks?.adapter = booksAdapter

        //return the layout for this fragment
        return fragmentView
    }
}