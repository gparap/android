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

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Model for a movie which is included in the "movies" of the web service's response.
 */
@Parcelize
data class MovieModel(
    @SerializedName("link")
    val watchLink: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("plot")
    val summary: String,

    @SerializedName("cast")
    val cast: String,

    @SerializedName("director")
    val director: String,

    @SerializedName("script")
    val writer: String,

    @SerializedName("producer")
    val producer: String,

    @SerializedName("photo")
    val photography: String,

    @SerializedName("editor")
    val editor: String,

    @SerializedName("music")
    val music: String,

    @SerializedName("publisher")
    val publisher: String,

    @SerializedName("year")
    val releaseYear: String,

    @SerializedName("duration")
    val filmTime: String,

    @SerializedName("country")
    val releaseCountry: String,

    @SerializedName("lang")
    val language: String,

    @SerializedName("article")
    @Expose
    val articleModel: List<ArticleModel>,
) : Parcelable