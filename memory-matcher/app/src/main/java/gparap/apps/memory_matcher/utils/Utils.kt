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
package gparap.apps.memory_matcher.utils

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import gparap.apps.memory_matcher.data.CardModel

object Utils {
    //* Returns a bitmap based on a memory card's visibility status ./
    fun getCardBitmap(card: CardModel): Bitmap? {
        val bitmap = if (card.isVisible) {
            card.bitmapFront
        } else {
            card.bitmapBack
        }
        return bitmap
    }

    /** Returns a bitmap based on a memory card's name. */
    fun getCardBitmap(assets: AssetManager, fileName: String) : Bitmap{
        val inputStream = assets.open(fileName)
        return BitmapFactory.decodeStream(inputStream)
    }

    /** Flips a memory card to its opposite side. */
    fun flipCard(card: CardModel, images: Array<ImageView?>?) {
        card.isVisible = !card.isVisible
        if (card.isVisible) {
            images?.get(card.position)?.setImageBitmap(card.bitmapFront)
        } else {
            images?.get(card.position)?.setImageBitmap(card.bitmapBack)
        }
    }

}