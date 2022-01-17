/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.movies.adapters.MovieAdapter
import gparap.apps.movies.model.MovieModel
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

        //consume the web service (get all movies) and update UI
        val response: Call<MovieResponseModel?>? = retrofit.getMovies
        response?.enqueue(object : Callback<MovieResponseModel?> {
            override fun onResponse(
                call: Call<MovieResponseModel?>,
                response: Response<MovieResponseModel?>
            ) {
                //get all movies
                val movies: List<MovieModel>? = response.body()?.movies

                //update adapter with movies
                moviesAdapter.movies = movies as ArrayList<MovieModel>
            }

            override fun onFailure(call: Call<MovieResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}