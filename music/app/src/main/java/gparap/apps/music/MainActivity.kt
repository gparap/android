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

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.music.adapters.SongsAdapter
import gparap.apps.music.api.MusicService
import gparap.apps.music.data.MusicResponseModel
import gparap.apps.music.data.SongResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewSongs: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create recycler view for songs
        recyclerViewSongs = findViewById(R.id.recyclerViewSongs)
        recyclerViewSongs.layoutManager = LinearLayoutManager(this)

        //fetch all songs   TODO: will be implemented in user defined songs inside local database
        val response: Call<MusicResponseModel?>? = MusicService.create().getAllSongs()
        response?.enqueue(object : Callback<MusicResponseModel?> {
            override fun onResponse(
                call: Call<MusicResponseModel?>,
                response: Response<MusicResponseModel?>,
            ) {
                val songs: List<SongResponseModel>? = response.body()?.songs
                println(songs.toString())
                println(songs?.size)
            }

            override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.music_type_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            //get music of medieval classical period
            R.id.menu_item_medieval -> {
                MusicService.create().getMedievalSongs()
                    ?.enqueue(object : Callback<MusicResponseModel?> {
                        override fun onResponse(
                            call: Call<MusicResponseModel?>,
                            response: Response<MusicResponseModel?>
                        ) {
                            displaySongs(response)
                        }

                        override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                            println(t.message.toString())
                        }
                    })
            }

            //get music of renaissance classical period
            R.id.menu_item_renaissance -> {
                MusicService.create().getRenaissanceSongs()
                    ?.enqueue(object : Callback<MusicResponseModel?> {
                        override fun onResponse(
                            call: Call<MusicResponseModel?>,
                            response: Response<MusicResponseModel?>
                        ) {
                            displaySongs(response)
                        }

                        override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                            println(t.message.toString())
                        }
                    })
            }

            //get music of baroque classical period
            R.id.menu_item_baroque -> {
                MusicService.create().getBaroqueSongs()
                    ?.enqueue(object : Callback<MusicResponseModel?> {
                        override fun onResponse(
                            call: Call<MusicResponseModel?>,
                            response: Response<MusicResponseModel?>
                        ) {
                            displaySongs(response)
                        }

                        override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                            println(t.message.toString())
                        }
                    })
            }

            //get music of classical period
            R.id.menu_item_classical -> {
                MusicService.create().getClassicalSongs()
                    ?.enqueue(object : Callback<MusicResponseModel?> {
                        override fun onResponse(
                            call: Call<MusicResponseModel?>,
                            response: Response<MusicResponseModel?>
                        ) {
                            displaySongs(response)
                        }

                        override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                            println(t.message.toString())
                        }
                    })
            }

            //get instrumental music
            R.id.menu_item_instrumental -> {
                MusicService.create().getInstrumentalSongs()
                    ?.enqueue(object : Callback<MusicResponseModel?> {
                        override fun onResponse(
                            call: Call<MusicResponseModel?>,
                            response: Response<MusicResponseModel?>
                        ) {
                            displaySongs(response)
                        }

                        override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                            println(t.message.toString())
                        }
                    })
            }

            //get traditional music
            R.id.menu_item_traditional -> {
                MusicService.create().getTraditionalSongs()
                    ?.enqueue(object : Callback<MusicResponseModel?> {
                        override fun onResponse(
                            call: Call<MusicResponseModel?>,
                            response: Response<MusicResponseModel?>
                        ) {
                            displaySongs(response)
                        }

                        override fun onFailure(call: Call<MusicResponseModel?>, t: Throwable) {
                            println(t.message.toString())
                        }
                    })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //load fetched songs into the recycler view for displaying
    private fun displaySongs(response: Response<MusicResponseModel?>) {
        val songs = response.body()?.songs as ArrayList<SongResponseModel>?
        recyclerViewSongs.adapter = SongsAdapter().apply {
            setSongs(songs)
        }
    }
}