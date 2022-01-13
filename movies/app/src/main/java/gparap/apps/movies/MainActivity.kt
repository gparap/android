package gparap.apps.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.movies.adapters.MovieAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a recyclerView with adapter to display the movies list
        val recyclerViewMovies = findViewById<RecyclerView>(R.id.recycler_view_movies)
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        val moviesAdapter = MovieAdapter()
        recyclerViewMovies.adapter = moviesAdapter
    }
}