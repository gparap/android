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
package gparap.apps.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Model for a movie which is included in the "movies" of the web service's response.
 */
data class MovieModel(
    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("plot")
    val plotSummary: String,

    @SerializedName("cast")
    val cast: String,

    @SerializedName("director")
    val directedBy: String,

    @SerializedName("script")
    val writtenBy: String,

    @SerializedName("producer")
    val producedBy: String,

    @SerializedName("photo")
    val cinematography: String,

    @SerializedName("editor")
    val editedBy: String,

    @SerializedName("music")
    val musicBy: String,

    @SerializedName("studio")
    val productionCompany: String,

    @SerializedName("distro")
    val distributedBy: String,

    @SerializedName("date")
    val releaseDate: String,

    @SerializedName("time")
    val filmTime: String,

    @SerializedName("country")
    val releaseCountry: String,

    @SerializedName("lang")
    val language: String,
)