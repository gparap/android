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

import android.app.WallpaperManager
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
import gparap.apps.wallpaper.utils.PermissionManager
import gparap.apps.wallpaper.utils.Utils


class WallpaperActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var wallpaperObj: WallpaperModel
    private lateinit var imageViewWallpaper: ImageView

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

        //find wallpaper image view
        imageViewWallpaper = this.findViewById(R.id.image_view_wallpaper)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //set the device home screen wallpaper
            R.id.fab_menu_item_set_wallpaper -> {
                //create bitmap from wallpaper image and scale it to device dimensions
                val bitmap = Utils.createScaledBitmap(
                    imageViewWallpaper.drawable,
                    Utils.getScreenWidth(this),
                    Utils.getScreenHeight(this)
                )

                //set the wallpaper
                val manager = WallpaperManager.getInstance(this)
                manager.setBitmap(bitmap)
                Toast.makeText(
                    this,
                    this.resources.getString(R.string.toast_wallpaper_set),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //save the wallpaper image to TODO: location
            R.id.fab_menu_item_save_wallpaper -> {
                if (!PermissionManager.hasPermissionToSave(this)) {
                    PermissionManager.requestPermissionToSave(this)
                } else {
                    saveWallpaper()
                }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        //permission granted
        if (PermissionManager.isPermissionToSaveGranted(requestCode, grantResults[0])) {
            saveWallpaper()
        } else {
            Toast.makeText(
                this,
                this.resources.getString(R.string.toast_permission_denied),
                Toast.LENGTH_SHORT
            ).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun saveWallpaper() {
        //create file path to save the image
        val file = Utils.createWallpaperFile(wallpaperObj.id)

        //save the image
        val isSavedSuccessfully = Utils.saveImageToFile(file, imageViewWallpaper, this)

        //give user feedback
        if (isSavedSuccessfully) {
            Toast.makeText(
                this,
                this.resources.getString(R.string.toast_wallpaper_saved),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                this.resources.getString(R.string.toast_cannot_save),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}