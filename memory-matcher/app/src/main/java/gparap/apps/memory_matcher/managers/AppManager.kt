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
package gparap.apps.memory_matcher.managers

/** This manager class handles the state of the memory matcher application. */
class AppManager {
    private var move1: Boolean = false //the 1st card that is selected
    private var move2: Boolean = false //the 2nd card that is selected

    fun areMovesCompleted(): Boolean {
        return move1 && move2
    }

    fun isMove1Complete(): Boolean {
        return move1
    }

    fun isMove2Complete(): Boolean {
        return move2
    }

    fun setMove1Complete() {
        move1 = true
    }

    fun setMove2Complete() {
        move2 = true
    }

    fun resetMoves() {
        move1 = false
        move2 = false
    }
}