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
package gparap.apps.converter_binary

/**
 * Validates binary input.
 */
class Validator {
    /**
     * Validates binary input.
     *  -contains only "1" and/or "0"?
     *  -is length dividable by 8?
     */
    fun validateBinaryInput(binaryString: String): Boolean {
        //check if input consists only from "1" and "0"
        if (binaryString.contains(Regex("[23456789.,-[+]]"))) { return false }
        try { binaryString.toDouble() } catch (e:Exception) { return false }

        //check if input length is divisable with 8
        if (binaryString.length.rem(8) != 0) { return false }

        //validation passes
        return true
    }
}