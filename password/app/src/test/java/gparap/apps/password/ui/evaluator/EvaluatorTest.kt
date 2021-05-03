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
package gparap.apps.password.ui.evaluator

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.password.R
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/*
!!! In order to run these tests we must lower the android build version
!!! to the maximum version supported by Robolectric.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class EvaluatorTest {
    private lateinit var context: Context
    private lateinit var evaluator: Evaluator

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        evaluator = Evaluator(context)
    }

    @Test
    fun evaluatePassword_isWeakest() {
        val expected = context.resources.getString(R.string.password_evaluation_weakest)
        val actual = evaluator.evaluatePassword("weakest")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeakest_testUpperCase() {
        val expected = context.resources.getString(R.string.password_evaluation_weakest)
        val actual = evaluator.evaluatePassword("weakAAA")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeakest_testDigits() {
        val expected = context.resources.getString(R.string.password_evaluation_weakest)
        val actual = evaluator.evaluatePassword("weak111")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeakest_testSpecialChars() {
        val expected = context.resources.getString(R.string.password_evaluation_weakest)
        val actual = evaluator.evaluatePassword("weak!!!")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeakest_test8charsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_weakest)
        val actual = evaluator.evaluatePassword("weak password")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_testUpperCaseWithDigits() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("aaaaA1")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_testUpperCaseWithSpecialChars() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("aaaaA!")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_testUpperCaseWith8CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("aaaaAAAA")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_testDigitsWithSpecialChars() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("aaaa0!")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_testDigitsWith8CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("aaaa0000")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_testSpecialCharsWith8CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("aaaa!!!!")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isWeak_test16charsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_weak)
        val actual = evaluator.evaluatePassword("weak password strength")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testUpperCaseWithDigits_WithSpecialChars() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaaC2!")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testUpperCaseWithDigits_With8CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaaC2aaaa")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testDigitsWithSpecialChars_With8CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaa0!aaaa")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testUpperCaseWithSpecialChars_With8CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaaA!aaaa")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testUpperCaseWith16CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaaAAAAaaaabbbb")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testDigitsWith16CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaa0000aaaabbbb")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isMedium_testSpecialCharsWith16CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_medium)
        val actual = evaluator.evaluatePassword("aaaa!!!!aaaabbbb")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isStrong_testUpperCaseWithDigits_With16CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_strong)
        val actual = evaluator.evaluatePassword("aaaaCCCC2222aaaa")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isStrong_testDigitsWithSpecialChars_With16CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_strong)
        val actual = evaluator.evaluatePassword("aaaa0000!!!!aaaa")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isStrong_testUpperCaseWithSpecialChars_With16CharsLength() {
        val expected = context.resources.getString(R.string.password_evaluation_strong)
        val actual = evaluator.evaluatePassword("aaaaAAAA!!!!aaaa")
        assertEquals(expected, actual)
    }

    @Test
    fun evaluatePassword_isStrongest() {
        val expected = context.resources.getString(R.string.password_evaluation_strongest)
        val actual = evaluator.evaluatePassword("aaaaAAAA0000!!!!")
        assertEquals(expected, actual)
    }
}