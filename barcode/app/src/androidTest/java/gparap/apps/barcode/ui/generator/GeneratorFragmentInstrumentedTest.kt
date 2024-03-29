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
package gparap.apps.barcode.ui.generator

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import gparap.apps.barcode.R
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class GeneratorFragmentInstrumentedTest {

    @Before
    fun setUp() {
        FragmentScenario.launchInContainer(
            GeneratorFragment::class.java, null, R.style.Base_Theme_MaterialComponents, null
        )
    }

    @Test
    fun isVisible_imageViewGeneratedBarcode() {
        onView(withId(R.id.imageViewGeneratedBarcode)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextGenerateBarcode() {
        onView(withId(R.id.editTextGenerateBarcode)).check(matches(isDisplayed()))

    }

    @Test
    fun isVisible_buttonGenerateBarcode() {
        onView(withId(R.id.buttonGenerateBarcode)).check(matches(isDisplayed()))

    }

    @Test
    fun isNotVisible_buttonSaveGeneratedBarcode() {
        onView(withId(R.id.buttonSaveGenerateBarcode)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_buttonSaveGeneratedBarcode_whenBarcodeIsGenerated() {
        //generate a test qrcode
        onView(withId(R.id.editTextGenerateBarcode)).perform(typeText("generated.."))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonGenerateBarcode)).perform(click())
        Thread.sleep(667)   //wait to generate

        onView(withId(R.id.buttonSaveGenerateBarcode)).check(matches(isDisplayed()))
    }
}