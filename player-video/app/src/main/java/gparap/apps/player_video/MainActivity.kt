/*
 * Copyright 2023 gparap
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
package gparap.apps.player_video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.player_video.adapters.VideoAdapter
import gparap.apps.player_video.data.VideoModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a test list of video items
        val testVideoTitles = mutableListOf<VideoModel>().apply {
            add(VideoModel.Builder().title("title #1").description("test").length("0:00").build())
            add(VideoModel.Builder().title("title #2").description("test").length("0:00").build())
            add(VideoModel.Builder().title("title #3").description("test").length("0:00").build())
            add(VideoModel.Builder().title("title #4").description("test").length("0:00").build())
            add(VideoModel.Builder().title("title #5").description("test").length("0:00").build())
        }

        //setup videos recycler view with adapter
        val recyclerViewVideos = findViewById<RecyclerView>(R.id.recycler_view_videos)
        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        val videoAdapter = VideoAdapter()
        videoAdapter.videos = testVideoTitles as ArrayList<VideoModel>
        recyclerViewVideos.adapter = videoAdapter
    }
}