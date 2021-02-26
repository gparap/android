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
package gparap.apps.converter_binary

/**
 * Parses conversion result.
 * Created by gparap on 2021-02-25.
 */
class Parser {
    /**
     * Parses binary by adding a space between every byte for better visual representation.
     * ie: "000000001111111100000000" -> = " 00000000 11111111 00000000"
     */
    fun parseBinary(binaryToParse: String): String {
        val charArray: CharArray = binaryToParse.toCharArray()
        val stringBuffer = StringBuffer()

        for (i in charArray.indices) {
            if (i.rem(8) == 0){ stringBuffer.append(" ") }
            stringBuffer.append(charArray[i])
        }
        return stringBuffer.toString().trimStart()
    }

    /**
     * Unnprses binary by removing all spaces among bytes for better visual representation.
     */
    fun unparseBinary(binaryToUnparse: String): String {
        return binaryToUnparse.filterNot { it -> it == ' ' }
    }
}