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
package gparap.apps.puzzle_scramble_squares.utils

import android.graphics.Bitmap
import androidx.core.graphics.scale

object Utils {
    /**
     * Returns a scaled bitmap thats fits a given display.
     */
    fun getScaledBitmap(bitmap: Bitmap, displayWidth: Int, displayHeight: Int): Bitmap {
        val scaleX = bitmap.width * displayWidth / bitmap.width
        val scaleY = bitmap.height * displayHeight / bitmap.height
        return bitmap.scale(scaleX, scaleY)
    }
}