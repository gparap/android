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

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

class UtilsInstrumentedTest {

    @Test
    fun getQuizCategory() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedCategory = "\"category\":\"Geography\""
        val responseModel = Utils.getJSONDataByCategory(context, "geography.json")

        assert(responseModel!!.contains(expectedCategory)) {
            "\"category\":\"Geography\" was expected"
        }
    }
}