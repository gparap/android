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
package gparap.apps.wallpaper.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.adapter.WallpaperAdapter
import gparap.apps.wallpaper.services.CategoryCallback
import gparap.apps.wallpaper.utils.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: WallpaperAdapter
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup RecyclerView with adapter
        val recyclerViewWallpapers = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerViewWallpapers.layoutManager =
            GridLayoutManager(this, Utils.getGridLayoutSpanCount(this))
        adapter = WallpaperAdapter()
        recyclerViewWallpapers.adapter = adapter

        //enable progress bar
        progress = findViewById(R.id.progress_main)
        progress.visibility = View.VISIBLE

        //fetch all wallpapers
        CategoryCallback.getWallpapers(
            this.resources.getString(R.string.text_category_all), this, adapter, progress
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            //display all wallpapers
            R.id.category_menu_all -> {
                progress.visibility = View.VISIBLE
                CategoryCallback.getWallpapers(
                    resources.getString(R.string.text_category_all), this, adapter, progress
                )
            }

            //display abstract wallpapers
            R.id.category_menu_abstract -> {
                progress.visibility = View.VISIBLE
                CategoryCallback.getWallpapers(
                    resources.getString(R.string.text_category_abstract), this, adapter, progress
                )
            }

            //display colorful wallpapers
            R.id.category_menu_colorful -> {
                progress.visibility = View.VISIBLE
                CategoryCallback.getWallpapers(
                    resources.getString(R.string.text_category_colorful), this, adapter, progress
                )
            }

            //display nature wallpapers
            R.id.category_menu_nature -> {
                progress.visibility = View.VISIBLE
                CategoryCallback.getWallpapers(
                    resources.getString(R.string.text_category_nature), this, adapter, progress
                )
            }

            //display pattern wallpapers
            R.id.category_menu_pattern -> {
                progress.visibility = View.VISIBLE
                CategoryCallback.getWallpapers(
                    resources.getString(R.string.text_category_pattern), this, adapter, progress
                )
            }

            //display texture wallpapers
            R.id.category_menu_texture -> {
                progress.visibility = View.VISIBLE
                CategoryCallback.getWallpapers(
                    resources.getString(R.string.text_category_texture), this, adapter, progress
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }
}