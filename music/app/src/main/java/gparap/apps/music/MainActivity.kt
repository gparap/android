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
package gparap.apps.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.music.adapters.SongsAdapter
import gparap.apps.music.data.SongModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a test son list
        val songs = ArrayList<SongModel>()
        songs.add(SongModel("song 1", "00:00", "0 KB"))
        songs.add(SongModel("song 2", "00:00", "0 KB"))
        songs.add(SongModel("song 3", "00:00", "0 KB"))

        //create recycler view for songs with adapter
        val recyclerViewSongs = findViewById<RecyclerView>(R.id.recyclerViewSongs)
        recyclerViewSongs.layoutManager = LinearLayoutManager(this)
        recyclerViewSongs.adapter = SongsAdapter().apply { setSongs(songs) }
    }
}