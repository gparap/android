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
package gparap.apps.anagram_color_finder.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UtilsUnitTest {
    @Suppress("UsePropertyAccessSyntax")
    @Test
    @DisplayName("Should pick a random color")
    fun getColorAtRandom() {
        val color: String? = Utils.getInstance().getColorAtRandom()
        Assertions.assertNotNull(color)
    }

    @Test
    @DisplayName("Should form an anagram")
    fun formAnagram() {
        val color = "Titanium yellow"
        val anagram: String? = Utils.getInstance().formAnagram(color)

        org.junit.jupiter.api.assertAll(color,
            { assertEquals(color.length, anagram!!.length) },   //color length equals anagram length
            { assertNotEquals(color, anagram) }                 //color is not equal to anagram
        )
    }
}