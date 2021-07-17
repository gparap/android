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
package gparap.apps.hangman.utils

import gparap.apps.hangman.words.Colors
import java.lang.StringBuilder
import java.util.*
import kotlin.random.Random

object Utils {
    /**
     * Returns a random word from a collection of words, in caps.
     */
    fun getRandomWord(): String {
        val index = Random.nextInt(Colors.colors.size)
        return Colors.colors[index].uppercase(Locale.getDefault())
    }

    /**
     * Returns underscored word(s) ("_") filled-in with all letters of the word(s) under search,
     * appending spaces between words.
     */
    fun getUnderscoredWords(word: String): StringBuilder {
        val underscoredWord = StringBuilder()
        for (w in word) {
            if (w == ' ') {
                underscoredWord.append(' ')
            } else {
                underscoredWord.append('_')
            }
        }
        return underscoredWord
    }
}