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
package gparap.apps.music.api

import gparap.apps.music.data.MusicResponseModel
import gparap.apps.music.utils.AppConstants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MusicService {

    @GET("/api/music.php?key=" + AppConstants.API_KEY)
    fun getAllSongs(): Call<MusicResponseModel?>?

    @GET("/api/music_medieval.php?key=" + AppConstants.API_KEY)
    fun getMedievalSongs(): Call<MusicResponseModel?>?

    @GET("/api/music_renaissance.php?key=" + AppConstants.API_KEY)
    fun getRenaissanceSongs(): Call<MusicResponseModel?>?

    @GET("/api/music_baroque.php?key=" + AppConstants.API_KEY)
    fun getBaroqueSongs(): Call<MusicResponseModel?>?

    @GET("/api/music_classical.php?key=" + AppConstants.API_KEY)
    fun getClassicalSongs(): Call<MusicResponseModel?>?

    @GET("/api/music_instrumental.php?key=" + AppConstants.API_KEY)
    fun getInstrumentalSongs(): Call<MusicResponseModel?>?

    @GET("/api/music_traditional.php?key=" + AppConstants.API_KEY)
    fun getTraditionalSongs(): Call<MusicResponseModel?>?

    companion object {
        /** Creates an implementation of the MusicService endpoints. */
        fun create(): MusicService {
            return Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicService::class.java)
        }
    }
}