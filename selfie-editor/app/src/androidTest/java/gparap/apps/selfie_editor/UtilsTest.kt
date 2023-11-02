/*
 * Copyright 2023 gparap
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
package gparap.apps.selfie_editor

import android.content.Context
import android.net.Uri
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.selfie_editor.utils.Utils
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class UtilsTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
    }

    //!!! important !!!
    // Steps to reproduce this test:
    //  1.take a picture using the app
    //  2.print the uri of the picture
    //  3.use that uri for the input stream to get the byte array
    //  4. copy the byte array value (the first 100 bytes)
    @Test
    fun getByteArray_isCorrect() {
        //use the first 100 bytes of the array
        val expectedByteArray = arrayOf(
            -1, -40, -1, -31, 4, 41, 69, 120, 105, 102, 0, 0, 77, 77, 0, 42, 0, 0, 0, 8, 0, 15, 1,
            59, 0, 2, 0, 0, 0, 13, 0, 0, 0, -62, 1, 15, 0, 2, 0, 0, 0, 6, 0, 0, 0, -49, 1, 1, 0, 4,
            0, 0, 0, 1, 0, 0, 18, 0, 1, 18, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 50, 0, 2, 0, 0, 0, 20,
            0, 0, 0, -43, 1, 27, 0, 5, 0, 0, 0, 1, 0, 0, 0, -23, -126, -104, 0, 2, 0, 0
        )

        //get the byte array
        val uri = Uri.parse("content://media/external/images/media/404")
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val actualByteArray = Utils.getByteArray(inputStream)
        inputStream?.close()

        //compare the first 100 byte of the array
        for (i in 0..99) {
            assertEquals(actualByteArray[i], expectedByteArray[i].toByte())
        }
    }
}