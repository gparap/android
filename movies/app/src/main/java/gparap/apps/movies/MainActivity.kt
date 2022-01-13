package gparap.apps.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.movies.adapters.MovieAdapter
import gparap.apps.movies.model.MovieResponseModel
import gparap.apps.movies.services.MovieService
import gparap.apps.movies.services.RetrofitClient
import gparap.apps.movies.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a recyclerView with adapter to display the movies list
        val recyclerViewMovies = findViewById<RecyclerView>(R.id.recycler_view_movies)
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        val moviesAdapter = MovieAdapter()
        recyclerViewMovies.adapter = moviesAdapter

        //creates a Retrofit HTTP client instance
        val retrofit = RetrofitClient()
            .build(AppConstants.BASE_URL)
            .create(MovieService::class.java)

        //consume the web service (get all movies)
        val response: Call<MovieResponseModel?>? = retrofit.getMovies
        response?.enqueue(object : Callback<MovieResponseModel?> {
            override fun onResponse(
                call: Call<MovieResponseModel?>,
                response: Response<MovieResponseModel?>
            ) {
                println(response.body()?.movies.toString())
            }

            override fun onFailure(call: Call<MovieResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}