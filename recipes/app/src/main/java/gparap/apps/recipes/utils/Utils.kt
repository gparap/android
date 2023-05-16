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
package gparap.apps.recipes.utils

object Utils {

    /** Returns the recipe's relevant multi-valued string as a single string,
     * in the form of an ordered list, ie:
     * 1. string value
     * 2. string value
     *    ............
     * n. string value */
    fun getOrderedListString(multiString: String?): String {
        var orderedListString = ""

        //split multi string with "|"
        val stringArray = multiString?.split('|')

        //construct the ordered string
        var i = 0
        stringArray?.forEach {
            var tempString = (i + 1).toString() + "." + ' ' + it.trim()
            if (i < stringArray.size - 1) {
                tempString = tempString.plus('\n')
            }
            i++
            orderedListString += tempString
        }

        //return the ordered string
        return orderedListString
    }
}