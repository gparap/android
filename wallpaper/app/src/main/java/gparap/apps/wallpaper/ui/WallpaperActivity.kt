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

import android.os.Build
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.data.WallpaperModel

class WallpaperActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var wallpaperObj: WallpaperModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        supportActionBar?.hide()

        //get parcelable from bundle
        wallpaperObj = intent.getParcelableExtra("wallpaper")!!

        //display the wallpaper image
        val wallpaperImg = findViewById<ImageView>(R.id.image_view_wallpaper)
        Glide.with(this)
            .load(wallpaperObj.imageUrl)
            .into(wallpaperImg)

        //create a popup menu
        val fabMenu = findViewById<FloatingActionButton>(R.id.fab_menu)
        fabMenu.setOnClickListener {
            val popup = PopupMenu(this, it)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.fab_menu, popup.menu)
            popup.show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popup.setForceShowIcon(true)
            }
            popup.setOnMenuItemClickListener(this)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.fab_menu_item_set_wallpaper -> {
                Toast.makeText(this, "placeholder1", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.fab_menu_item_save_wallpaper -> {
                Toast.makeText(this, "placeholder2", Toast.LENGTH_SHORT).show()
                return true
            }

            //display the wallpaper details
            R.id.fab_menu_item_wallpaper_details -> {
                DetailsDialogFragment(wallpaperObj).show(supportFragmentManager, null)
                return true
            }
        }
        return false
    }
}