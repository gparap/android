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
package gparap.apps.wallpaper.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.data.WallpaperModel
import java.io.File
import java.io.FileOutputStream

object Utils {
    /**
     * Gets device orientation and returns the number of columns that the grid layout should have
     */
    fun getGridLayoutSpanCount(context: Context): Int {
        return if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            AppConstants.SPAN_COUNT_PORTRAIT
        } else {
            AppConstants.SPAN_COUNT_LANDSCAPE
        }
    }

    /**
     * Constructs and returns the wallpaper details message from a wallpaper model
     */
    fun getWallpaperDetailsMessage(wallpaper: WallpaperModel, context: Context): String {
        var message = ""

        //title
        message = message
            .plus(context.resources.getString(R.string.text_title))
            .plus(' ')
            .plus(wallpaper.title)
            .plus(context.resources.getString(R.string.text_new_line_double))

        //category
        message = message
            .plus(context.resources.getString(R.string.text_category))
            .plus(' ')
            .plus(wallpaper.category)
            .plus(context.resources.getString(R.string.text_new_line_double))

        //creator
        message = message
            .plus(context.resources.getString(R.string.text_creator))
            .plus(' ')
            .plus(wallpaper.creator[0].name)
            .plus(context.resources.getString(R.string.text_new_line_double))

        //creator link
        message = message
            .plus(context.resources.getString(R.string.text_link))
            .plus(' ')
            .plus(wallpaper.creator[0].website)
            .plus(context.resources.getString(R.string.text_new_line_double))

        return message
    }

    /**
     * Gets device screen width in pixels
     */
    fun getScreenWidth(activity: AppCompatActivity): Int {
        return getDisplayMetrics(activity).widthPixels
    }

    /**
     * Gets device screen height in pixels
     */
    fun getScreenHeight(activity: AppCompatActivity): Int {
        return getDisplayMetrics(activity).heightPixels
    }

    /**
     * Creates a scaled bitmap from an image drawable
     */
    fun createScaledBitmap(drawable: Drawable, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(
            (drawable as BitmapDrawable).bitmap,
            width,
            height,
            true
        )
    }

    /**
     * Create a directory for wallpapers in device's public pictures storage directory
     */
    fun createStorageDirectory(): File {
        val directoryPath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val directory = File("$directoryPath/wallpapers")
        if (!directory.exists() && !directory.isDirectory) {
            directory.mkdir()
        }
        return directory
    }

    /**
     * Creates a .png file to store the wallpaper image
     */
    fun createWallpaperFile(wallpaperID: String): File {
        return File(createStorageDirectory().absolutePath + "/wallpaper_" + wallpaperID + ".png")
    }

    private fun getDisplayMetrics(activity: AppCompatActivity): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        activity.window.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    /**
     * Saves the wallpaper image as a .png file in the "pictures" shared folder of the device
     */
    fun saveImageToFile(file: File, wallpaper: ImageView, activity: AppCompatActivity): Boolean {
        var isSavedSuccessfully = false
        var fileOutputStream: FileOutputStream? = null

        try {
            fileOutputStream = FileOutputStream(file)
            val bitmap = createScaledBitmap(
                wallpaper.drawable,
                getScreenWidth(activity),
                getScreenHeight(activity),
            )
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            isSavedSuccessfully = true

        } catch (e: Exception) {
            println(e.localizedMessage)
            isSavedSuccessfully = false
        } finally {
            fileOutputStream?.close()
        }

        return isSavedSuccessfully
    }
}