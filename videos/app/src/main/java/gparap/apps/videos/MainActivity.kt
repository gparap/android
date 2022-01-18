package gparap.apps.videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.videos.adapters.VideoAdapter
import gparap.apps.videos.models.VideoModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DEBUG (test data)
        val videos = ArrayList<VideoModel>()
        videos.add(VideoModel("", "", "title1","channel1", "date1"))
        videos.add(VideoModel("", "", "title2","channel2", "date2"))
        videos.add(VideoModel("", "", "title3","channel3", "date3"))

        //setup RecyclerView with adapter
        val recyclerViewVideos = findViewById<RecyclerView>(R.id.recycler_view_videos)
        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        val adapter = VideoAdapter()
        adapter.videos = videos
        recyclerViewVideos.adapter = adapter
    }
}