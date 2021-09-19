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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import java.text.SimpleDateFormat
import java.util.*

internal class UtilsUnitTest {

    @org.junit.jupiter.api.Test
    @DisplayName("should generate a filename suffix")
    fun generateFilenameSuffix() {
        val pattern = "yyMMddHHmm"
        val expectedSuffix = SimpleDateFormat(pattern).format(Date())
        val actualSuffix = Utils.generateFilenameSuffix(pattern)
        assertEquals(expectedSuffix, actualSuffix)
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should create a new file")
    fun createNewFile() {
        val expectedInFilename = "painting"
        val actualInFileName = Utils.createNewFile("directory", "file")
        assertTrue(actualInFileName.toString().contains(expectedInFilename))
    }
}