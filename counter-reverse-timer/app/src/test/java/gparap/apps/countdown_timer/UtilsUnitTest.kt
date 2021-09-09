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
package gparap.apps.countdown_timer

import gparap.apps.countdown_timer.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UtilsUnitTest {
    @Test
    @DisplayName("should convert seconds to milliseconds")
    fun convertSecondsToMillis() {
        val seconds=30
        val expectedMillis = 30000L
        val actualMillis = Utils.getInstance().convertSecondsToMillis(seconds)
        Assertions.assertEquals(expectedMillis, actualMillis)
    }

    @Test
    @DisplayName("should convert minutes to milliseconds")
    fun convertMinutesToMillis() {
        val minutes=30
        val expectedMillis = 1800000L
        val actualMillis = Utils.getInstance().convertMinutesToMillis(minutes)
        Assertions.assertEquals(expectedMillis, actualMillis)
    }

    @Test
    @DisplayName("should convert hours to milliseconds")
    fun convertHoursToMillis() {
        val hours=2
        val expectedMillis = 7200000L
        val actualMillis = Utils.getInstance().convertHoursToMillis(hours)
        Assertions.assertEquals(expectedMillis, actualMillis)
    }
}