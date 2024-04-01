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
package gparap.apps.cipher_manager

import gparap.apps.cipher_manager.utils.Utils
import org.junit.Test

import org.junit.Assert.*

class UtilsUnitTest {
    @Test
    fun encryptTextWithAES_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "Hello World!"
        val expectedValue ="AAAAAAAAAAAAAAAAAAAAACGNaHWBhySF6MY9S4ZrsPU="
        val actualValue = Utils.encryptWithAES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun decryptTextWithAES_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "AAAAAAAAAAAAAAAAAAAAACGNaHWBhySF6MY9S4ZrsPU="
        val expectedValue ="Hello World!"
        val actualValue = Utils.decryptWithAES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }
}