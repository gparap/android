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
package gparap.apps.barcode

import android.graphics.Matrix
import android.view.View
import android.widget.ImageView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var decorView: View

    @Before
    fun setUp() {
        //launch activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //retrieve the top-level window decor view
        activityScenario.onActivity {
            decorView = it.window.decorView
        }
    }

    @Test
    fun onCreate_isBottomNavigationBarVisible() {
        onView(withId(R.id.bottom_nav_view)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToScannerFragment() {
        onView(withId(R.id.navigation_scanner)).perform(click())
        onView(withId(R.id.action_bar)).check(matches(hasDescendant(withText(R.string.title_scanner))))
    }

    @Test
    fun navigateToGeneratorFragment() {
        onView(withId(R.id.navigation_generator)).perform(click())
        onView(withId(R.id.action_bar)).check(matches(hasDescendant(withText(R.string.title_generator))))
    }

    @Test
    fun generatorFragment_textToGenerateBarcodeIsEmpty_displayToast() {
        //goto generator fragment
        onView(withId(R.id.navigation_generator)).perform(click())

        //make sure edit text is empty and attempt to generate code
        onView(withId(R.id.editTextGenerateBarcode)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.buttonGenerateBarcode)).perform(click())

        onView(withText(R.string.toast_empty_text_generator))
            .inRoot(withDecorView(not((decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun generatorFragment_generateBarcode() {
        //goto generator fragment
        onView(withId(R.id.navigation_generator)).perform(click())

        //make sure the barcode's image matrix is the identity matrix
        var identityMatrix: Matrix? = null
        activityScenario.onActivity {
            val imageView: ImageView = it.findViewById(R.id.imageViewGeneratedBarcode)
            imageView.setImageBitmap(null)
            identityMatrix = imageView.imageMatrix
        }

        //enter barcode
        onView(withId(R.id.editTextGenerateBarcode)).perform(typeText("barcode"))
        closeSoftKeyboard()
        onView(withId(R.id.buttonGenerateBarcode)).perform(click())

        activityScenario.onActivity {
            val imageView: ImageView = it.findViewById(R.id.imageViewGeneratedBarcode)
            assert(imageView.imageMatrix != identityMatrix)
        }
    }
}