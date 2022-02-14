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
package gparap.apps.wallpaper.services

import gparap.apps.wallpaper.data.ApiResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    /** Fetch all wallpapers */
    @GET("/wallpapers.php")
    fun getAll(): Call<ApiResponseModel>

    /** Fetch abstract wallpapers */
    @GET("/abstract.php")
    fun getCategoryAbstract(): Call<ApiResponseModel>

    /** Fetch colorful wallpapers */
    @GET("/colorful.php")
    fun getCategoryColorful(): Call<ApiResponseModel>

    /** Fetch nature wallpapers */
    @GET("/nature.php")
    fun getCategoryNature(): Call<ApiResponseModel>

    /** Fetch pattern wallpapers */
    @GET("/pattern.php")
    fun getCategoryPattern(): Call<ApiResponseModel>

    /** Fetch texture wallpapers */
    @GET("/texture.php")
    fun getCategoryTexture(): Call<ApiResponseModel>
}