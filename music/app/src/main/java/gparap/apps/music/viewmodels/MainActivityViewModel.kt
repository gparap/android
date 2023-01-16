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
package gparap.apps.music.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.music.api.MusicService
import gparap.apps.music.data.MusicResponseModel
import gparap.apps.music.data.SongResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private var songsLiveData: MutableLiveData<ArrayList<SongResponseModel>?> = MutableLiveData()

    fun getSongs(): LiveData<ArrayList<SongResponseModel>?> {
        return songsLiveData
    }

    fun setSongs(songs: ArrayList<SongResponseModel>?) {
        songsLiveData.value = songs
    }

    /** Get music of medieval classical period. */
    fun getMedievalSongs() {
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

    /** Get music of renaissance classical period. */
    fun getRenaissanceSongs() {
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

    /** Get music of baroque classical period. */
    fun getBaroqueSongs() {
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

    /** Get music of classical period. */
    fun getClassicalSongs() {
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

    /** Get instrumental music. */
    fun getInstrumentalSongs() {
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

    /** Get traditional music. */
    fun getTraditionalSongs() {
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

    /** Get folk music. */
    fun getFolkSongs() {
        MusicService.create().getFolkSongs()
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

    /** Get world music. */
    fun getWorldSongs() {
        MusicService.create().getWorldSongs()
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

    //load fetched songs into the recycler view for displaying
    private fun displaySongs(response: Response<MusicResponseModel?>) {
        val songs = response.body()?.songs as ArrayList<SongResponseModel>?
        setSongs(songs)
    }
}