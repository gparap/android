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
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
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
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progress)

        //create a RecyclerView with adapter for posts
        postsRecyclerView = findViewById(R.id.recycleViewMain)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = PostAdapter()

        //get all posts and update UI
        getAllPosts()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            //get all posts and update UI
            R.id.menu_item_home -> getAllPosts()

            //get parents category posts and update UI
            R.id.menu_item_parents -> getParentsPosts()

            //get arts category posts and update UI
            R.id.menu_item_arts -> getArtsPosts()

            //get health category depression posts and update UI
            R.id.menu_item_depression -> getDepressionPosts()

            //get health category multiple sclerosis posts and update UI
            R.id.menu_item_sclerosis -> getSclerosisPosts()

            //get older posts and update UI
            R.id.menu_item_older_posts -> getOlderPosts()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAllPosts() {
        showLoadingProgress()
        WordpressService.create().getPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
                hideLoadingProgress()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
                hideLoadingProgress()
            }
        })
    }

    private fun getParentsPosts() {
        showLoadingProgress()
        WordpressService.create().getParentsPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
                hideLoadingProgress()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
                hideLoadingProgress()
            }
        })
    }

    private fun getArtsPosts() {
        showLoadingProgress()
        WordpressService.create().getArtsPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
                hideLoadingProgress()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
                hideLoadingProgress()
            }
        })
    }

    private fun getDepressionPosts() {
        showLoadingProgress()
        WordpressService.create().getDepressionPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
                hideLoadingProgress()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
                hideLoadingProgress()
            }
        })
    }

    private fun getSclerosisPosts() {
        showLoadingProgress()
        WordpressService.create().getSclerosisPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
                hideLoadingProgress()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
                hideLoadingProgress()
            }
        })
    }

    private fun getOlderPosts() {
        showLoadingProgress()
        WordpressService.create().getOlderPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>,
            ) {
                val posts: MutableList<PostModel> = response.body() as MutableList<PostModel>
                postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
                hideLoadingProgress()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                println(t.message.toString())
                hideLoadingProgress()
            }
        })
    }

    private fun showLoadingProgress() {
        progress.visibility = ProgressBar.VISIBLE
    }

    private fun hideLoadingProgress() {
        progress.visibility = ProgressBar.INVISIBLE
    }
}