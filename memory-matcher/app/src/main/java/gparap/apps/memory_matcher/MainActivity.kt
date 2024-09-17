/*
 * Copyright 2024 gparap
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
package gparap.apps.memory_matcher

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gparap.apps.memory_matcher.data.CardModel
import gparap.apps.memory_matcher.data.GridModel

class MainActivity : AppCompatActivity() {
    private var grid: GridModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //get the device orientation
        val orientation = resources.configuration.orientation

        //get all the image views for this orientation and put them inside an array
        val images: Array<ImageView?>?
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val grid00 = findViewById<ImageView>(R.id.grid_row_0_col_0)
            val grid01 = findViewById<ImageView>(R.id.grid_row_0_col_1)
            val grid10 = findViewById<ImageView>(R.id.grid_row_1_col_0)
            val grid11 = findViewById<ImageView>(R.id.grid_row_1_col_1)
            val grid20 = findViewById<ImageView>(R.id.grid_row_2_col_0)
            val grid21 = findViewById<ImageView>(R.id.grid_row_2_col_1)
            val grid30 = findViewById<ImageView>(R.id.grid_row_3_col_0)
            val grid31 = findViewById<ImageView>(R.id.grid_row_3_col_1)
            images = arrayOf(grid00, grid01, grid10, grid11, grid20, grid21, grid30, grid31)
        } else {
            val grid00 = findViewById<ImageView>(R.id.grid_row_0_col_0)
            val grid01 = findViewById<ImageView>(R.id.grid_row_0_col_1)
            val grid02 = findViewById<ImageView>(R.id.grid_row_0_col_2)
            val grid03 = findViewById<ImageView>(R.id.grid_row_0_col_3)
            val grid10 = findViewById<ImageView>(R.id.grid_row_1_col_0)
            val grid11 = findViewById<ImageView>(R.id.grid_row_1_col_1)
            val grid12 = findViewById<ImageView>(R.id.grid_row_1_col_2)
            val grid13 = findViewById<ImageView>(R.id.grid_row_1_col_3)
            images = arrayOf(grid00, grid01, grid02, grid03, grid10, grid11, grid12, grid13)
        }

        //display the grid, if it is filled
        if (savedInstanceState?.getBoolean("is_grid_filled") == true) {
            @Suppress("deprecation")
            grid = savedInstanceState.getParcelable("grid")!!

            //display on the grid
            grid!!.list.forEach { card ->
                //choose the card bitmap based on its visibility status
                val cardBitmap = if (card.isVisible) {
                    card.bitmapFront
                } else {
                    card.bitmapBack
                }

                //set the card bitmap
                images[card.position]?.setImageBitmap(cardBitmap).apply {
                    images[card.position]?.setOnClickListener {
                        //flip the card
                        card.isVisible = !card.isVisible
                        if (card.isVisible){
                            images[card.position]?.setImageBitmap(card.bitmapFront)
                        }else{
                            images[card.position]?.setImageBitmap(card.bitmapBack)
                        }
                    }
                }
            }
        }

        //fill & display the grid
        if (savedInstanceState == null) {
            //get a list of decoded images inside a bitmap array
            val bitmaps = ArrayList<Bitmap>()
            assets.list("planets")!!.forEach { asset ->
                //skip the cardback
                if (asset.contains("cardback.png")) {
                    return@forEach
                }

                //decode images
                val inputStream = assets.open("planets/$asset")
                bitmaps.add(BitmapFactory.decodeStream(inputStream))
            }

            //initialize the grid
            grid = GridModel(0, false, ArrayList())
            grid!!.size = images.size
            grid!!.list = ArrayList()

            //set grid card positioning
            for (i in images.indices) {
                //decode the back of the card image
                val inputStream = assets.open("planets/cardback.png")
                val cardback = BitmapFactory.decodeStream(inputStream)
                val cardBitmap = CardModel(0, 0, null, cardback)

                //set card position in grid
                grid!!.list.add(cardBitmap)
                grid!!.list[i].position = i
            }

            //update the grid with the first pair of images, shuffled
            bitmaps.shuffle()
            for (i in 0 until bitmaps.size) {
                images[i]?.setImageBitmap(bitmaps[i]).apply {
                    images[i]?.setOnClickListener { println("grid position $i clicked.") }
                }

                //set grid card bitmaps
                grid!!.list[i].bitmapFront = bitmaps[i]
            }

            //update the grid with the second pair of images, shuffled
            bitmaps.shuffle()
            for (i in 0 until bitmaps.size) {
                images[i + bitmaps.size]?.setImageBitmap(bitmaps[i]).apply {
                    images[i + bitmaps.size]?.setOnClickListener { println("grid position ${i + bitmaps.size} clicked.") }
                }

                //set grid card bitmaps
                grid!!.list[i + bitmaps.size].bitmapFront = bitmaps[i]
            }

            //display the grid
            grid!!.list.forEach { card ->
                //choose the card bitmap based on its visibility status
                val cardBitmap = if (card.isVisible) {
                    card.bitmapFront
                } else {
                    card.bitmapBack
                }

                //set the card bitmap
                images[card.position]?.setImageBitmap(cardBitmap).apply {
                    images[card.position]?.setOnClickListener {
                        //flip the card
                        card.isVisible = !card.isVisible
                        if (card.isVisible){
                            images[card.position]?.setImageBitmap(card.bitmapFront)
                        }else{
                            images[card.position]?.setImageBitmap(card.bitmapBack)
                        }
                    }
                }
            }

            //update the grid flag
            grid!!.isFilled = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //save the grid state
        outState.putBoolean("is_grid_filled", grid!!.isFilled)
        outState.putParcelable("grid", grid)
    }
}