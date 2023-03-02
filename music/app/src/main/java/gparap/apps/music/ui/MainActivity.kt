/*
 * Copyright (c) 2023 gparap
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
package gparap.apps.music.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import gparap.apps.music.R
import gparap.apps.music.adapters.SongsAdapter
import gparap.apps.music.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewSongs: RecyclerView
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBarLoadingSongs)

        //create ViewModel for this activity
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        //create recycler view for songs
        recyclerViewSongs = findViewById(R.id.recyclerViewSongs)
        recyclerViewSongs.layoutManager = LinearLayoutManager(this)

        //initialize the Google Mobile Ads SDK
        MobileAds.initialize(this) {}

        //observe songs
        viewModel.getSongs().observe(this) {
            recyclerViewSongs.adapter = SongsAdapter().apply {
                setSongs(it)
            }.apply {
                //load an ad
                val adView = findViewById<AdView>(R.id.adView_main)
                val adRequest = AdRequest.Builder().build()
                adView.loadAd(adRequest)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.music_type_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //hide welcome text when user is choosing category
        findViewById<TextView>(R.id.textViewWelcome_main).apply { this.visibility = View.GONE }
        findViewById<TextView>(R.id.textViewWelcome_secondary).apply { this.visibility = View.GONE }

        //fill the recycler view with songs
        when (item.itemId) {
            R.id.menu_item_medieval -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getMedievalSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.medieval_period)

            }
            R.id.menu_item_renaissance -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getRenaissanceSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.renaissance_period)
            }
            R.id.menu_item_baroque -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getBaroqueSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.baroque_period)
            }
            R.id.menu_item_classical -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getClassicalSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.classical_music)
            }
            R.id.menu_item_instrumental -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getInstrumentalSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.instrumental_music)
            }
            R.id.menu_item_traditional -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getTraditionalSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.traditional_music)
            }
            R.id.menu_item_folk -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getFolkSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.folk_music)
            }
            R.id.menu_item_world -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getWorldSongs(progressBar)
                supportActionBar?.title = resources.getString(R.string.world_music)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}