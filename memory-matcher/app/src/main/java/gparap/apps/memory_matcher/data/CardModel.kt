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
package gparap.apps.memory_matcher.data

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** This model class represents the memory card. */
@Parcelize
data class CardModel(
    val pairId: Int,
    var position: Int,
    var bitmapFront: Bitmap?,
    var bitmapBack: Bitmap, //generic card bitmap, same for all cards
    var isVisible: Boolean = false
) : Parcelable
