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
package gparap.apps.paidagogaki_gr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.paidagogaki_gr.adapters.PostAdapter
import gparap.apps.paidagogaki_gr.api.WordpressService
import gparap.apps.paidagogaki_gr.data.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a RecyclerView with adapter for posts
        val postsRecyclerView = findViewById<RecyclerView>(R.id.recycleViewMain)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = PostAdapter()

        //get all posts and update UI
        WordpressService.create().getPosts().enqueue(object : Callback<List<PostModel>>{
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}