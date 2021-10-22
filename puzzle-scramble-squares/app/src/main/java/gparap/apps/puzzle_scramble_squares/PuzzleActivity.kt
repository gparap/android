/*
 * Copyright 2021 gparap
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
package gparap.apps.puzzle_scramble_squares

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.puzzle_scramble_squares.utils.Utils

class PuzzleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)

        //get the picked image's uri from the intent
        var uri: Uri? = null
        val strUri = intent.getStringExtra("picked_picture_uri")
        if (!strUri.isNullOrEmpty()) {
            uri = Uri.parse(strUri)
        }

        //get the real display area of the device
        val defaultDisplay = this.windowManager.defaultDisplay
        val defaultDisplayWidth = defaultDisplay.width
        val defaultDisplayHeight = defaultDisplay.height

        //get bitmap from uri
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

        //scale bitmap to fit the screen
        val scaledBitmap = Utils.getScaledBitmap(bitmap, defaultDisplayWidth, defaultDisplayHeight)

        //display the picked image and scale it to fit the screen
        val image = findViewById<ImageView>(R.id.image_view_scrambled_squares)
        image.setImageBitmap(scaledBitmap)
    }
}