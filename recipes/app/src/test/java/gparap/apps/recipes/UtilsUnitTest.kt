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
package gparap.apps.recipes

import gparap.apps.recipes.utils.Utils
import org.junit.Test

import org.junit.Assert.*

class UtilsUnitTest {
    @Test
    fun getOrderedListString_isCorrect() {
        val mixedString = "value 1 | value 2 |...| value n"
        val expectedOrderedString = "1. value 1" + '\n' + "2. value 2" + '\n' + "3. ..." + '\n' + "4. value n"
        val actualOrderedString = Utils.getOrderedListString(mixedString)
        assertEquals(expectedOrderedString, actualOrderedString)
    }
}