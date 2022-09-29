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
package gparap.apps.paidagogaki_gr.api

import gparap.apps.paidagogaki_gr.data.PostModel
import gparap.apps.paidagogaki_gr.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/** Fetches blog's data in JSON format from the WordPress API using Retrofit HTTP client. */
interface WordpressService {

    @GET("/wp-json/wp/v2/posts")
    fun getPosts(): Call<List<PostModel>>

    @GET("/wp-json/wp/v2/posts?categories=9")
    fun getParentsPosts(): Call<List<PostModel>>

    @GET("/wp-json/wp/v2/posts?categories=12")
    fun getArtsPosts(): Call<List<PostModel>>

    @GET("/wp-json/wp/v2/posts?categories=6")
    fun getDepressionPosts(): Call<List<PostModel>>

    @GET("/wp-json/wp/v2/posts?categories=4")
    fun getSclerosisPosts(): Call<List<PostModel>>

    companion object {
        fun create(): WordpressService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WordpressService::class.java)
        }
    }
}
