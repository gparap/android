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
package gparap.apps.movies.ui

import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import gparap.apps.movies.R
import gparap.apps.movies.model.MovieModel
import gparap.apps.movies.utils.Utils

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get movie from intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            movie = intent.extras?.getParcelable("movie", MovieModel::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            movie = intent.extras?.get("movie") as MovieModel
        }

        //display title to the activity's ActionBar
        supportActionBar?.title = movie.title

        //display movie image
        val image = findViewById<ImageView>(R.id.image_view_movie_details)
        Glide.with(this)
            .load(movie.imageUrl)
            .into(image)

        //create and display link to watch the movie
        val watchLink = findViewById<TextView>(R.id.text_view_watch_movie_link)
        watchLink.text = Utils.createWatchMovieLink(
            movie.watchLink,
            resources.getString(R.string.text_watch_movie)
        )
        watchLink.movementMethod = LinkMovementMethod.getInstance()

        //display movie summary
        val summary = findViewById<TextView>(R.id.text_view_movie_summary)
        summary.text = movie.summary

        //display movie director
        val director = findViewById<TextView>(R.id.text_view_movie_director)
        director.text = movie.director

        //display movie cast
        val cast = findViewById<TextView>(R.id.text_view_movie_cast)
        cast.text = movie.cast

        //display movie writer
        val writer = findViewById<TextView>(R.id.text_view_movie_writer)
        writer.text = movie.writer

        //display movie producer
        val producer = findViewById<TextView>(R.id.text_view_movie_producer)
        producer.text = movie.producer

        //display movie photography
        val photography = findViewById<TextView>(R.id.text_view_movie_photography)
        photography.text = movie.photography

        //display movie editor
        val editor = findViewById<TextView>(R.id.text_view_movie_editor)
        editor.text = movie.editor

        //display movie music
        val music = findViewById<TextView>(R.id.text_view_movie_music)
        music.text = movie.music

        //display movie publisher
        val publisher = findViewById<TextView>(R.id.text_view_movie_publisher)
        publisher.text = movie.publisher

        //display movie release year
        val year = findViewById<TextView>(R.id.text_view_movie_release_year)
        year.text = movie.releaseYear

        //display movie release country
        val country = findViewById<TextView>(R.id.text_view_movie_release_country)
        country.text = movie.releaseCountry

        //display movie language
        val lang = findViewById<TextView>(R.id.text_view_movie_language)
        lang.text = movie.language

        //display movie duration
        val duration = findViewById<TextView>(R.id.text_view_movie_film_time)
        duration.text = movie.filmTime

        //display attribution details
        val article = movie.articleModel[0]
        val attribution = findViewById<TextView>(R.id.text_view_attribution)
        attribution.text = Utils.createAttributionDetails(article)
        attribution.movementMethod = LinkMovementMethod.getInstance()

        //handle banner ads
        MobileAds.initialize(this)
        val adViewTop: AdView = findViewById(R.id.ad_view_banner_top)
        val adViewBottom: AdView = findViewById(R.id.ad_view_banner_bottom)
        val adRequest = AdRequest.Builder().build()
        adViewTop.loadAd(adRequest)
        adViewBottom.loadAd(adRequest)
    }

    //close this activity and return home
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}