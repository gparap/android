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
package gparap.apps.password.ui.generator

import android.widget.TextView
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import gparap.apps.password.R
import org.junit.Before
import org.junit.Test

class GeneratorFragmentInstrumentedTest {
    private var fragmentScenario: FragmentScenario<GeneratorFragment>? = null

    @Before
    fun setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(GeneratorFragment::class.java)
    }

    @Test
    fun onClickRadioButton_generate08LengthPassword() {
        onView(withId(R.id.radioButton08CharsLengthPassword)).perform(click())
        onView(withId(R.id.buttonGeneratePassword)).perform(click())
        val expectedLength = 8
        val actualLength = getTextFromViewById(R.id.textViewPasswordGenerated)?.length
        assert(expectedLength == actualLength) { "Password length is not correct" }
    }

    @Test
    fun onClickRadioButton__generate16LengthPassword() {
        onView(withId(R.id.radioButton16CharsLengthPassword)).perform(click())
        onView(withId(R.id.buttonGeneratePassword)).perform(click())
        val expectedLength = 16
        val actualLength = getTextFromViewById(R.id.textViewPasswordGenerated)?.length
        assert(expectedLength == actualLength) { "Password length is not correct" }
    }

    @Test
    fun onClickradioButton_generateCustomLengthPassword() {
        onView(withId(R.id.radioButtonCustomLengthPassword)).perform(click())
        onView(withId(R.id.editTextCustomLengthPassword)).perform(typeText("5"))
        val expectedLength = 5
        val actualLength = getTextFromViewById(R.id.editTextCustomLengthPassword)?.toInt()
        assert(expectedLength == actualLength) { "Password length is not correct" }
    }

    private fun getTextFromViewById(viewId: Int): String? {
        var textView: TextView? = null
        fragmentScenario?.onFragment {
            textView = it.view?.findViewById(viewId)
        }
        return textView?.text?.toString()
    }
}