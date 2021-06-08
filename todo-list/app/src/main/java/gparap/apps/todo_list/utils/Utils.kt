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
package gparap.apps.todo_list.utils

object Utils {
    /**
     * Accepts an integer and fills in zeros in front, if it is less than 10.
     * Returns integer as string.
     * ie: 1 -> "01", 9 -> "09", etc.
     */
    fun fillInZeroInFront(number: Int) : String {
        if (number < 10) {
            return "0$number"
        }
        return "$number"
    }
}