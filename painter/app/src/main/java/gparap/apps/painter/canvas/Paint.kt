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
package gparap.apps.painter.canvas

import android.graphics.Color
import android.graphics.Paint

class Paint : Paint() {
    init {
        //create a new paint with default settings
        isAntiAlias = true
        style = Style.STROKE
        color = Color.BLACK
        strokeWidth = 5f
    }

    /**
     * Returns custom paint flag
     */
    fun getFlag(flag: Int): Paint {
        return Paint(flag)
    }
}