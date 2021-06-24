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

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class UtilsUnitTest {

    @Test
    fun fillInZeroInFrontNumberThatIsLessThan10() {
        val number = 9
        val expectedString = "09"
        val actualString = Utils.fillInZeroInFront(number)
        assertEquals(expectedString, actualString)
    }

    @Test
    fun convertTimeAndDateAsString_testLocaleUS() {
        val time = "14:32"
        val date = "9/6/2021"

        //test Locale.US
        Locale.setDefault(Locale.US)//09/06/21 14:32
        var expectedString = "6/9/21 2:32 PM"
        var actualString = Utils.convertTimeAndDateAsString(time, date)
        assertEquals(expectedString, actualString)

        //test Locale.US
        Locale.setDefault(Locale.UK)
        expectedString = "09/06/21 14:32"
        actualString = Utils.convertTimeAndDateAsString(time, date)
        assertEquals(expectedString, actualString)
    }

    @Test
    //!!! To run this test:
    //!!! 1. add package android.text in src/test,
    //!!! 2. goto "https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/text/TextUtils.java"
    //!!! 3. and copy the method "public static String[] split(String text, String expression)" to manually mock TextUtils.
    //!!! cause: "Method split in android.text.TextUtils not mocked. See http://g.co/androidstudio/not-mocked for details."
    fun convertTimeAsStringLocale_hoursNotStartingWithZero() {
        val time = "14:02"

        //test Locale.US
        Locale.setDefault(Locale.US)
        var expectedTime = "2:02 PM"
        var actualTime = Utils.convertTimeAsStringLocale(time)
        assertEquals(expectedTime, actualTime)

        //test Locale.CHINA
        Locale.setDefault(Locale.CHINA)
        expectedTime = "2:02 下午"
        actualTime = Utils.convertTimeAsStringLocale(time)
        assertEquals(expectedTime, actualTime)
    }

    @Test
    //!!! To run this test:
    //!!! 1. add package android.text in src/test,
    //!!! 2. goto "https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/text/TextUtils.java"
    //!!! 3. and copy the method "public static String[] split(String text, String expression)" to manually mock TextUtils.
    //!!! cause: "Method split in android.text.TextUtils not mocked. See http://g.co/androidstudio/not-mocked for details."
    fun convertTimeAsStringLocale_hoursStartingWithZero() {
        val time = "04:02"

        //test Locale.US
        Locale.setDefault(Locale.US)
        var expectedTime = "4:02 AM"
        var actualTime = Utils.convertTimeAsStringLocale(time)
        assertEquals(expectedTime, actualTime)

        //test Locale.JAPANESE
        Locale.setDefault(Locale.JAPANESE)
        expectedTime = "4:02 午前"
        actualTime = Utils.convertTimeAsStringLocale(time)
        assertEquals(expectedTime, actualTime)
    }

    @Test
    fun convertDateAsStringLocale() {
        //test Locale.CHINA
        Locale.setDefault(Locale.CHINA)
        val date = "07/06/2021"
        var expectedDate = "21-6-7"
        var actualDate = Utils.convertDateAsStringLocale(date)
        assertEquals(expectedDate, actualDate)

        //test Locale.US
        Locale.setDefault(Locale.US)
        expectedDate = "6/7/21"
        actualDate = Utils.convertDateAsStringLocale(date)
        assertEquals(expectedDate, actualDate)

        //test Locale.UK
        Locale.setDefault(Locale.UK)
        expectedDate = "07/06/21"
        actualDate = Utils.convertDateAsStringLocale(date)
        assertEquals(expectedDate, actualDate)
    }
}