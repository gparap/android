package gparap.apps.wallpaper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.adapter.WallpaperAdapter
import gparap.apps.wallpaper.data.CreatorModel
import gparap.apps.wallpaper.data.TagModel
import gparap.apps.wallpaper.data.WallpaperModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //test data
        val wallpapers = ArrayList<WallpaperModel>()
        wallpapers.add(WallpaperModel(
            "1", "http://gparap.com/images/placeholder.png", "title", "sort", "cat",
            TagModel("", ""),
            CreatorModel("", ""))
        )
        wallpapers.add(WallpaperModel(
            "2", "http://gparap.com/images/placeholder.png", "title", "sort", "cat",
            TagModel("", ""),
            CreatorModel("", ""))
        )
        wallpapers.add(WallpaperModel(
            "3", "http://gparap.com/images/placeholder.png", "title", "sort", "cat",
            TagModel("", ""),
            CreatorModel("", ""))
        )
        wallpapers.add(WallpaperModel(
            "4", "http://gparap.com/images/placeholder.png", "title", "sort", "cat",
            TagModel("", ""),
            CreatorModel("", ""))
        )

        //setup RecyclerView with adapter
        val recyclerViewWallpapers = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerViewWallpapers.layoutManager = GridLayoutManager(this, 2)
        val adapter = WallpaperAdapter()
        adapter.wallpapers = wallpapers
        adapter.notifyDataSetChanged()
        recyclerViewWallpapers.adapter = adapter
    }
}