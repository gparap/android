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
package gparap.apps.memory_matcher.managers

import gparap.apps.memory_matcher.data.CardModel
import gparap.apps.memory_matcher.data.GridModel

/** This manager class handles the state of the memory grid, overall. */
class GridManager {
    private lateinit var grid: GridModel
    private lateinit var activePairCard: CardModel  //the 1st card of a pair
    fun getActivePairCard() : CardModel{
        return activePairCard
    }
    fun setActivePairCard(card: CardModel) {
        activePairCard = card
    }

    fun initGrid() {
        grid = GridModel(0, false, ArrayList())
    }

    fun initGrid(size: Int, isFilled: Boolean, cards: ArrayList<CardModel>) {
        grid = GridModel(size, isFilled, cards)
    }

    fun getCards(): ArrayList<CardModel> {
        return grid.cards
    }

    fun getGridSize(): Int {
        return grid.size
    }

    fun setGridSize(size: Int) {
        grid.size = size
    }

    fun setCardPosition(card: CardModel, index: Int) {
        grid.cards.add(card)
        grid.cards[index].position = index
    }

    fun isGridFilled(): Boolean {
        return grid.isFilled
    }

    fun setGridFilled() {
        grid.isFilled = true
    }
}