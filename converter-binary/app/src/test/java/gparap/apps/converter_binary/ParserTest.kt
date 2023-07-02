/*
 * Copyright 2021-2023 gparap
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
package gparap.apps.converter_binary

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParserTest {
    private var parser: Parser? = null
    private lateinit var input: String
    private lateinit var expected :String
    private lateinit var actual :String

    @Before
    fun setUp() {
        parser = Parser()
    }

    @Test
    fun parseBinary() {
        input = "000000001111111100000000"
        expected = "00000000 11111111 00000000"
        actual = parser?.parseBinary(input)!!
        assertEquals("parsing output is wrong", expected, actual)
    }

    @Test
    fun unparseBinary() {
        input = "00000000 11111111 00000000"
        expected = "000000001111111100000000"
        actual = parser?.unparseBinary(input)!!
        assertEquals("unparsing output is wrong", expected, actual)
    }
}