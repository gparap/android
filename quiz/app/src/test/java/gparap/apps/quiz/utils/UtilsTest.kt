/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.quiz.utils

import org.junit.Assert.*

import org.junit.Test

class UtilsTest {

    @Test
    fun getQuizModelFromJSON() {
        val category = "geography"
        val jsonString =
            "{\"results\":[{\"category\":$category,\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"What European country is not a part of the EU?\",\"correct_answer\":\"Norway\",\"incorrect_answers\":[\"Lithuania\",\"Ireland\",\"Czechia\"]}]}"
        val quizModel = Utils.getQuizModelFromJSON(jsonString)

        assertEquals(quizModel[0].category, category)
    }

    @Test
    fun getJsonFileByCategory() {
        val category = "history"
        val expectedFile = "history.json"
        val actualFile = Utils.getJsonFileByCategory(category)

        assertEquals(actualFile, expectedFile)
    }
}