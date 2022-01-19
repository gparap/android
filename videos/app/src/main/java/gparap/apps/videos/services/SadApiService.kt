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
package gparap.apps.videos.services

import gparap.apps.videos.models.SadApiResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Youtube Search and Download's web service for receiving videos.
 * Results are in JSON format.
 */
interface SadApiService {
    /**
     * Get list of trending videos.
     */
    @Suppress("SpellCheckingInspection")
    @get:Headers(
        "x-rapidapi-host: youtube-search-and-download.p.rapidapi.com",
        "x-rapidapi-key: MY_API_KEY"
    )
    @get:GET("/trending?type=mu&hl=en&gl=US")
    val getTrendingVideos: Call<SadApiResponseModel?>?
}