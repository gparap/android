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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.music.R
import gparap.apps.music.adapters.SongsAdapter
import gparap.apps.music.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewSongs: RecyclerView
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create ViewModel for this activity
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        //create recycler view for songs
        recyclerViewSongs = findViewById(R.id.recyclerViewSongs)
        recyclerViewSongs.layoutManager = LinearLayoutManager(this)

        //observe songs
        viewModel.getSongs().observe(this) {
            recyclerViewSongs.adapter = SongsAdapter().apply {
                setSongs(it)
            }
        }

        //fetch favorite songs TODO: will be implemented in user defined songs inside local database
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
            R.id.menu_item_medieval -> viewModel.getMedievalSongs()
            R.id.menu_item_renaissance -> viewModel.getRenaissanceSongs()
            R.id.menu_item_baroque -> viewModel.getBaroqueSongs()
            R.id.menu_item_classical -> viewModel.getClassicalSongs()
            R.id.menu_item_instrumental -> viewModel.getInstrumentalSongs()
            R.id.menu_item_traditional -> viewModel.getTraditionalSongs()
            R.id.menu_item_folk -> viewModel.getFolkSongs()
            R.id.menu_item_world -> viewModel.getWorldSongs()
        }
        return super.onOptionsItemSelected(item)
    }
}