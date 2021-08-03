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
package gparap.apps.painter.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

object Utils {
    //get display metrics that describe the size and density of this display
    private val displayMetrics = DisplayMetrics()

    /**
     * Returns the absolute width of the display in pixels
     */
    fun getDeviceWidth(context: Context?): Int {
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
     * Returns the absolute height of the display in pixels
     */
    fun getDeviceHeight(context: Context?): Int {
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}