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
package gparap.apps.movies.services

import gparap.apps.movies.model.MovieResponseModel
import gparap.apps.movies.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A web service for receiving movie, serial and cast information.
 * APIs results are in JSON format.
 */
interface MovieService {
    /**
     * Gets all the movies.
     */
    @get:GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    val getMovies: Call<MovieResponseModel?>?

    /**
     * Gets movie(s) by title.
     */
    @GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    fun getMoviesByTitle(@Query("title") title: String?): Call<MovieResponseModel?>?

    /**
     * Gets movies by genre.
     */
    @GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    fun getMoviesByGenre(@Query("genre") genre: String?): Call<MovieResponseModel?>?

    /**
     * Gets movies by actor.
     */
    @GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    fun getMoviesByActor(@Query("actor") actor: String?): Call<MovieResponseModel?>?

    /**
     * Gets movies by director.
     */
    @GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    fun getMoviesByDirector(@Query("director") director: String?): Call<MovieResponseModel?>?

    /**
     * Gets movies by publisher.
     */
    @GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    fun getMoviesByPublisher(@Query("publisher") publisher: String?): Call<MovieResponseModel?>?

    /**
     * Gets movies by release year.
     */
    @GET("/api/movies/movies.php?key=" + AppConstants.API_KEY)
    fun getMoviesByYear(@Query("year") year: String?): Call<MovieResponseModel?>?

    /**
     * Gets all new movies.
     */
    @get:GET("/api/movies/movies.php?key=" + AppConstants.API_KEY + "&new=1")
    val getNewMovies: Call<MovieResponseModel?>?
}