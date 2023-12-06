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

import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.player_video.adapters.VideoAdapter
import gparap.apps.player_video.data.VideoModel

class MainActivity : AppCompatActivity() {
    private var videos = mutableListOf<VideoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //handle permissions TODO: minor APIs
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_MEDIA_VIDEO
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                //get the list of video items
                videos = getVideos() as MutableList<VideoModel>

            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_MEDIA_VIDEO),
                    999
                )
            }
        }

        //setup videos recycler view with adapter
        val recyclerViewVideos = findViewById<RecyclerView>(R.id.recycler_view_videos)
        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        val videoAdapter = VideoAdapter()
        videoAdapter.videos = videos as ArrayList<VideoModel>
        recyclerViewVideos.adapter = videoAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 999) {
                getVideos()
            }
        }
    }

    /** Returns a list of all the device videos using the MediaStore API */
    private fun getVideos(): List<VideoModel> {
        val videos = mutableListOf<VideoModel>()

        //get the content URI
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            android.provider.MediaStore.Video.Media.getContentUri(android.provider.MediaStore.VOLUME_EXTERNAL)
        } else {
            android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }

        //define the columns that will be returned
        val projection: Array<String> = arrayOf(
            android.provider.MediaStore.Video.Media._ID,
            android.provider.MediaStore.Video.Media.TITLE,
            android.provider.MediaStore.Video.Media.DESCRIPTION,
            android.provider.MediaStore.Video.Media.DURATION,
            android.provider.MediaStore.Video.Media.DATA,
        )

        //query the given URI and get the Cursor
        val cursor: Cursor? = contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )

        //get video files and add to list
        while (cursor?.moveToNext() == true) {
            //get the video details of what is to be displayed
            var index = cursor.getColumnIndex(android.provider.MediaStore.Video.Media.TITLE)
            val title = cursor.getString(index)
            index = cursor.getColumnIndex(android.provider.MediaStore.Video.Media.DESCRIPTION)
            val description = cursor.getString(index)
            index = cursor.getColumnIndex(android.provider.MediaStore.Video.Media.DURATION)
            val duration = cursor.getString(index)
            index = cursor.getColumnIndex(android.provider.MediaStore.Video.Media.DATA)
            val path = cursor.getString(index)

            //create a video model with these details and add to list
            videos.add(VideoModel.Builder()
                .title(title).description(description).length(duration).path(path)
                .build())
        }

        //free up the Cursor
        cursor?.close()

        //return the list of videos
        return videos
    }
}