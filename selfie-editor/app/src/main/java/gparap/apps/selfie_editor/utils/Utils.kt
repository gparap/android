/*
 * Copyright 2022 gparap
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
package gparap.apps.selfie_editor.utils

import android.app.Activity
import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowMetrics
import java.io.ByteArrayOutputStream
import java.io.InputStream

object Utils {
    /** Returns a byte array from a stream on to the content associated with the image URI.  */
    fun getByteArray(inputStream: InputStream?): ByteArray {
        val byteArray = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var len: Int
        if (inputStream != null) {
            while (inputStream.read(byteArray).also { len = it } != -1) {
                byteArrayOutputStream.write(byteArray, 0, len)
            }
        }
        return byteArrayOutputStream.toByteArray()
    }

    /** Returns the absolute width of the display in pixels. */
    fun getDeviceWidth(context: Context?): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics =
                (context as Activity).windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right

        } else {
            val displayMetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    /** Returns the absolute height of the display in pixels. */
    fun getDeviceHeight(context: Context?): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics =
                (context as Activity).windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom

        } else {
            val displayMetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }
}