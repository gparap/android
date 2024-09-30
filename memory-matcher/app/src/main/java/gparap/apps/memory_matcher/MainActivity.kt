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
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gparap.apps.memory_matcher.data.CardModel
import gparap.apps.memory_matcher.managers.AppManager
import gparap.apps.memory_matcher.managers.GridManager
import gparap.apps.memory_matcher.utils.AppConstants
import gparap.apps.memory_matcher.utils.AppConstants.KEY_APP_MATCHED_PAIRS
import gparap.apps.memory_matcher.utils.AppConstants.KEY_APP_TOTAL_MOVES
import gparap.apps.memory_matcher.utils.AppConstants.KEY_GRID_CARD_LIST
import gparap.apps.memory_matcher.utils.AppConstants.KEY_GRID_SIZE
import gparap.apps.memory_matcher.utils.AppConstants.KEY_IS_GRID_FILLED
import gparap.apps.memory_matcher.utils.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var images: Array<ImageView?>
    private lateinit var gridManager: GridManager
    private lateinit var appManager: AppManager

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

        //create an application manager object to handle the state of the memory matcher application
        appManager = AppManager(images.size).apply { initApp() }

        //create a grid manager object to handle the state of the grid
        gridManager = GridManager()

        //display the grid, if it is filled
        if (savedInstanceState?.getBoolean(KEY_IS_GRID_FILLED) == true) {
            @Suppress("deprecation")
            gridManager.initGrid(
                savedInstanceState.getInt(KEY_GRID_SIZE),
                savedInstanceState.getBoolean(KEY_IS_GRID_FILLED),
                savedInstanceState.getParcelableArrayList(KEY_GRID_CARD_LIST)!!
            )

            //get the app manager values & update the UI
            appManager.initApp(
                savedInstanceState.getInt(KEY_APP_TOTAL_MOVES),
                savedInstanceState.getInt(KEY_APP_MATCHED_PAIRS)
            )
            updateUI()
            startNewRound()
        }

        //fill & display the grid
        if (savedInstanceState == null) {
            prepareGrid()
            startNewRound()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_item_restart -> {
                appManager.reset()
                updateUI()
                prepareGrid()
                startNewRound()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //save the grid state
        outState.putBoolean(KEY_IS_GRID_FILLED, gridManager.isGridFilled())
        outState.putInt(KEY_GRID_SIZE, gridManager.getGridSize())
        outState.putParcelableArrayList(KEY_GRID_CARD_LIST, gridManager.getCards())

        //save the application state
        outState.putInt(KEY_APP_TOTAL_MOVES, appManager.getTotalMoves())
        outState.putInt(KEY_APP_MATCHED_PAIRS, appManager.getMatchedPairs())
    }

    /* Prepares the grid for a new round of memory matcher. */
    private fun prepareGrid() {
        //get a list of decoded images inside a bitmap array
        val bitmaps = ArrayList<Bitmap>()
        assets.list("planets")!!.forEach { asset ->
            //skip the cardback
            if (asset.contains(AppConstants.PATH_CARDBACK)) {
                return@forEach
            }

            //decode images
            bitmaps.add(Utils.getCardBitmap(assets, AppConstants.PATH_PLANETS.plus("/$asset")))
        }

        //initialize the grid
        gridManager.initGrid()
        gridManager.setGridSize(images.size)

        //set grid card positioning
        for (i in images.indices) {
            //decode the back of the card image
            val cardback = Utils.getCardBitmap(assets, AppConstants.PATH_PLANETS_CARDBACK)
            val cardBitmap = CardModel(0, 0, null, cardback)

            //set card position in grid
            gridManager.setCardPosition(cardBitmap, i)
        }

        //update the grid with the first pair of images, shuffled
        bitmaps.shuffle()
        for (i in 0 until bitmaps.size) {
            images[i]?.setImageBitmap(bitmaps[i])

            //set grid card bitmaps
            gridManager.getCards()[i].bitmapFront = bitmaps[i]
        }

        //update the grid with the second pair of images, shuffled
        bitmaps.shuffle()
        for (i in 0 until bitmaps.size) {
            images[i + bitmaps.size]?.setImageBitmap(bitmaps[i])

            //set grid card bitmaps
            gridManager.getCards()[i + bitmaps.size].bitmapFront = bitmaps[i]
        }

        //update the grid flag
        gridManager.setGridFilled()
    }

    /** Starts a new round of memory matcher application. */
    private fun startNewRound() {
        //display & handle the grid
        gridManager.getCards().forEach { card ->
            //choose the card bitmap based on its visibility status
            val cardBitmap = Utils.getCardBitmap(card)

            //set the card bitmap
            images[card.position]?.setImageBitmap(cardBitmap).apply {
                images[card.position]?.setOnClickListener {

                    //handle the application state
                    if (!appManager.areMovesCompleted() && !card.isVisible) {
                        //flip the card
                        Utils.flipCard(card, images)

                        //we are in the 1st move, keep the card as an active pair card
                        if (!appManager.isMove1Complete()) {
                            gridManager.setActivePairCard(card)
                            appManager.setMove1Complete()
                        }

                        //we are in the 2nd move, this round is over
                        else if (!appManager.isMove2Complete()) {
                            appManager.setMove2Complete()

                            //check if we have a pair of cards (aka identical cards)
                            if (Utils.isCardPair(gridManager.getActivePairCard(), card)) {
                                appManager.resetMoves()
                                //update matched pairs
                                appManager.setPairMatched()
                                //check if there are no more pairs
                                if (appManager.getMatchedPairs() == appManager.getTotalPairs()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        getString(R.string.text_round_completed),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            } else if (card.isVisible) { //if both the moves are finished, hide the cards
                                //delay the hiding of the cards
                                Handler(Looper.getMainLooper()).postDelayed({
                                    //hide this (2nd of the pair) card
                                    Utils.flipCard(card, images)
                                    card.isVisible = false
                                    //hide the 1st card of the pair
                                    Utils.flipCard(gridManager.getActivePairCard(), images)
                                    gridManager.getActivePairCard().isVisible = false
                                }, AppConstants.CARD_HIDING_TIMEOUT).apply {
                                    //update the moves
                                    appManager.resetMoves()
                                }
                            }
                        }
                    }
                    updateUI()
                }
            }
        }
    }

    /* Updates the values of the round's total moves and matched pairs. */
    private fun updateUI() {
        findViewById<TextView>(R.id.textViewTotalMoves).apply { text = appManager.getTotalMoves().toString() }
        findViewById<TextView>(R.id.textViewMatchedPairs).apply { text = appManager.getMatchedPairs().toString() }
    }
}