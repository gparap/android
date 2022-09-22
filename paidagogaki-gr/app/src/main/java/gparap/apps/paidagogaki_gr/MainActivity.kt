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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.paidagogaki_gr.adapters.PostAdapter
import gparap.apps.paidagogaki_gr.data.PostModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a test list of posts
        val posts = mutableListOf<PostModel>()
        posts.add(PostModel(1, "1/1/2022", "1/1/2022", "", "test1"))
        posts.add(PostModel(2, "2/1/2022", "2/1/2022", "", "test2"))
        posts.add(PostModel(3, "3/1/2022", "3/1/2022", "", "test3"))
        posts.add(PostModel(4, "4/1/2022", "4/1/2022", "", "test4"))

        //create a RecyclerView with adapter for posts
        val postsRecyclerView = findViewById<RecyclerView>(R.id.recycleViewMain)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = PostAdapter().apply { setPosts(posts) }
    }
}