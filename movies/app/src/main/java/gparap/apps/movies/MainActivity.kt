/*
 * Copyright (c) 2022-2023 gparap
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

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import gparap.apps.movies.adapters.MovieAdapter
import gparap.apps.movies.model.MovieModel
import gparap.apps.movies.model.MovieResponseModel
import gparap.apps.movies.services.MovieService
import gparap.apps.movies.services.RetrofitClient
import gparap.apps.movies.utils.AppConstants
import gparap.apps.movies.utils.AppConstants.APP_OPENED_TIMES_BY_USER
import gparap.apps.movies.utils.AppConstants.INTERSTITIAL_AD_UNIT_ID
import gparap.apps.movies.utils.AppConstants.INTERSTITIAL_LOAD_FACTOR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @Suppress("PrivatePropertyName")
    private val TAG = "Mobile Ads SDK"
    private var interstitialAd: InterstitialAd? = null
    private lateinit var moviesAdapter: MovieAdapter
    private var movies = ArrayList<MovieModel>()    //placeholder for all movies until Room DB
    private var selectedSearchType = "-1"           //ie. search movies by title, by genre, etc.
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a default spinner adapter
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_array_strings,
            android.R.layout.simple_spinner_item
        ).apply {
            this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        //setup spinner with adapter
        val searchSpinner: androidx.appcompat.widget.AppCompatSpinner =
            findViewById(R.id.search_spinner)
        searchSpinner.adapter = adapter

        //handle spinner item selection
        searchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                //set the movie search type based on the spinner selection
                selectedSearchType = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //create a recyclerView with adapter to display the movies list
        val recyclerViewMovies = findViewById<RecyclerView>(R.id.recycler_view_movies)
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        moviesAdapter = MovieAdapter()
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
                response: Response<MovieResponseModel?>,
            ) {
                //get all movies
                movies = response.body()?.movies as ArrayList<MovieModel>

                //update adapter with movies
                moviesAdapter.movies = movies
            }

            override fun onFailure(call: Call<MovieResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })

        //initialize the Mobile Ads SDK
        MobileAds.initialize(this) { }

        //get the times that user opened the app
        val sharedPref = getSharedPreferences(APP_OPENED_TIMES_BY_USER, Context.MODE_PRIVATE)
        val timesUserOpenedTheApp = sharedPref.getInt(APP_OPENED_TIMES_BY_USER, 1)

        //load an interstitial ad every X-th time the user opened the app !!! NOT the 1st time !!!
        if (timesUserOpenedTheApp % INTERSTITIAL_LOAD_FACTOR == 0) {
            //load ad
            InterstitialAd.load(this, INTERSTITIAL_AD_UNIT_ID,
                AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d(TAG, adError.message)
                        interstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        Log.d(TAG, "Ad was loaded.")
                        this@MainActivity.interstitialAd = interstitialAd

                        //show the ad
                        if (this@MainActivity.interstitialAd != null) {
                            this@MainActivity.interstitialAd?.show(this@MainActivity)

                        } else {
                            Log.d(TAG, "The interstitial ad wasn't ready yet.")
                        }
                    }
                })
        }

        //update the times that user opened the app
        val editor = sharedPref.edit()
        editor?.putInt(APP_OPENED_TIMES_BY_USER, timesUserOpenedTheApp + 1)
        editor?.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        //setup the searchable functionality
        searchView = (menu?.findItem(R.id.menu_item_search_movies)?.actionView) as SearchView
        searchView.queryHint = getString(R.string.text_search_movies)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //search queries are based on spinner item selected
                when (selectedSearchType) {
                    getString(R.string.text_search_movies_by_title) -> searchMoviesByTitle(query)
                    getString(R.string.text_search_movies_by_genre) -> searchMoviesByGenre(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        //handle the back button click of the search view
        val menuItem = menu.findItem(R.id.menu_item_search_movies)
        menuItem.setOnActionExpandListener(object : OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                //display all movies if we are done searching
                if (moviesAdapter.movies.size != movies.size) {
                    moviesAdapter.movies = movies
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun searchMoviesByTitle(query: String?) {
        //creates a Retrofit HTTP client instance
        val retrofit = RetrofitClient()
            .build(AppConstants.BASE_URL)
            .create(MovieService::class.java)

        //consume the web service (get movie(s) by title) and update UI
        val response: Call<MovieResponseModel?>? = retrofit.getMoviesByTitle(query)
        response?.enqueue(object : Callback<MovieResponseModel?> {
            override fun onResponse(
                call: Call<MovieResponseModel?>,
                response: Response<MovieResponseModel?>,
            ) {
                //get movie(s) by title
                val moviesByTitle: List<MovieModel>? = response.body()?.movies

                //update adapter with movie(s)
                moviesAdapter.movies = moviesByTitle as ArrayList<MovieModel>

                //close the search view
                searchView.clearFocus()
            }

            override fun onFailure(call: Call<MovieResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }

    private fun searchMoviesByGenre(query: String?) {
        //creates a Retrofit HTTP client instance
        val retrofit = RetrofitClient()
            .build(AppConstants.BASE_URL)
            .create(MovieService::class.java)

        //consume the web service (get movie(s) by title) and update UI
        val response: Call<MovieResponseModel?>? = retrofit.getMoviesByGenre(query)
        response?.enqueue(object : Callback<MovieResponseModel?> {
            override fun onResponse(
                call: Call<MovieResponseModel?>,
                response: Response<MovieResponseModel?>,
            ) {
                //get movie(s) by title
                val moviesByTitle: List<MovieModel>? = response.body()?.movies

                //update adapter with movie(s)
                moviesAdapter.movies = moviesByTitle as ArrayList<MovieModel>

                //close the search view
                searchView.clearFocus()
            }

            override fun onFailure(call: Call<MovieResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}

