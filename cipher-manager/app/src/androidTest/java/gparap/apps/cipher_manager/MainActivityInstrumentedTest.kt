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
package gparap.apps.cipher_manager

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }
    
    @Test
    fun isVisible_textView_privateKey_label() {
        onView(withId(R.id.textView_privateKey_label)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_editText_privateKey() {
        onView(withId(R.id.editText_privateKey)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_textView_publicKey_label() {
        onView(withId(R.id.textView_publicKey_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_publicKey() {
        onView(withId(R.id.editText_publicKey)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_encrypt() {
        onView(withId(R.id.button_encrypt)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_decrypt() {
        onView(withId(R.id.button_decrypt)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_cipher_value_label() {
        onView(withId(R.id.textView_cipher_value_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editText_cipher_value() {
        onView(withId(R.id.editText_cipher_value)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_button_clear() {
        onView(withId(R.id.button_clear)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_cipher_info() {
        onView(withId(R.id.textView_cipher_info)).check(matches(isDisplayed()))
    }
}