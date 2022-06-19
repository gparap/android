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
package gparap.apps.calculator_area

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorActivityInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)
    private var rootView: View? = null
    private var context: Context? = null
    private lateinit var activityScenario: ActivityScenario<CalculatorActivity>

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            //get the top-level window decor view
            rootView = activity.window.decorView

            //get the activity context
            context = activity.baseContext
        }

        //launch activity
        activityScenario = ActivityScenario.launch(CalculatorActivity::class.java)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.calculator_area", appContext.packageName)
    }

    @Test
    fun isDisplayed_Spinner2D() {
        onView(withId(R.id.spinnerShapes2D)).check(matches(isDisplayed()))
    }

    @Test
    fun isDisplayed_EditTextResult() {
        onView(withId(R.id.textViewResult)).check(matches(isDisplayed()))
    }

    @Test
    fun isDisplayed_ButtonCalculate() {
        onView(withId(R.id.buttonCalculate)).check(matches(isDisplayed()))
    }

    @Test
    fun squareItemSelected_SetVisible_SideA_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_square))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun rectangleItemSelected_SetVisible_SideA_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_rectangle))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun parallelogramItemSelected_SetVisible_SideA_Height_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_parallelogram))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun rhombusItemSelected_SetVisible_Diagonal1_Diagonal2_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_rhombus))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextDiagonal2)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun equilateralTriangleItemSelected_SetVisible_SideA_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_equilateral_triangle))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun triangleItemSelected_SetVisible_SideA_Height_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_triangle))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun trapezoidItemSelected_SetVisible_SideA_SideB_Height_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_trapezoid))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun regularPentagonItemSelected_SetVisible_SideA_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_regular_pentagon))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun hexagonItemSelected_SetVisible_SideA_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_hexagon))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun circleItemSelected_SetVisible_SideA_SetInvisible_allOtherInputFields() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_circle))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(not(isDisplayed())))
    }

    @Test
    fun ovalItemSelected_SetVisible_SemiAxis1_SemiAxis2_SetInvisible_SideB_Height_Radius() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_oval))).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextRadius)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal1)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiagonal2)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSemiAxis1)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSemiAxis2)).check(matches(isDisplayed()))
    }

    @Test
    fun validateInput_Error_FieldMustNotBeEmpty() {
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EnterValue))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        waitForToastMessage()
    }

    @Test
    fun validateInput_Error_SidesCannotBeEqual_Parallelogram() {
        //enter values
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_parallelogram))).perform(click())
        onView(withId(R.id.editTextSideA)).perform(typeText("10"))
        onView(withId(R.id.editTextHeight)).perform(typeText("10"))

        //close keyboard
        onView(isRoot()).perform(closeSoftKeyboard())

        //test
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EqualValues_Parallelogram))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        waitForToastMessage()
    }

    @Test
    fun validateInput_Error_SidesCannotBeEqual_Trapezoid_SideAWithHeight() {
        //enter values
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_trapezoid))).perform(click())
        onView(withId(R.id.editTextSideA)).perform(typeText("10"))
        onView(withId(R.id.editTextSideB)).perform(typeText("10"))
        onView(withId(R.id.editTextHeight)).perform(typeText("1"))   //indifferent

        //close keyboard
        onView(isRoot()).perform(closeSoftKeyboard())

        //test
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EqualValues_Trapezoid))
            .inRoot(withDecorView(not(`is`(rootView))))
            .check(matches(isDisplayed()))
        waitForToastMessage()
    }

    @Test
    fun validateInput_Error_SemiAxesCannotBeEqual_Oval() {
        //enter values
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_oval))).perform(click())
        onView(withId(R.id.editTextSemiAxis1)).perform(typeText("10"))
        onView(withId(R.id.editTextSemiAxis2)).perform(typeText("10"))

        //close keyboard
        onView(isRoot()).perform(closeSoftKeyboard())

        //test
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EqualValues_Oval))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
        waitForToastMessage()
    }

    @Test
    fun squareItemSelected_SetVisible_ImageViewShape2d() {
        //select square
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_square))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.square)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun rectangleItemSelected_SetVisible_ImageViewShape2d() {
        //select rectangle
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_rectangle))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.rectangle)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun parallelogramItemSelected_SetVisible_ImageViewShape2d() {
        //select parallelogram
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_parallelogram))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.parallelogram)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun rhombusItemSelected_SetVisible_ImageViewShape2d() {
        //select rhombus
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_rhombus))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.rhombus)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun equilateralTriangleItemSelected_SetVisible_ImageViewShape2d() {
        //select equilateralTriangle
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_equilateral_triangle))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.equilateral_triangle)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun triangleItemSelected_SetVisible_ImageViewShape2d() {
        //select triangle
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_triangle))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.triangle)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun trapezoidItemSelected_SetVisible_ImageViewShape2d() {
        //select trapezoid
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_trapezoid))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.trapezoid)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun regularPentagonItemSelected_SetVisible_ImageViewShape2d() {
        //select regular pentagon
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_regular_pentagon))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.regular_pentagon)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun hexagonItemSelected_SetVisible_ImageViewShape2d() {
        //select hexagon
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_hexagon))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.hexagon)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun circleItemSelected_SetVisible_ImageViewShape2d() {
        //select circle
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_circle))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.circle)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun ovalItemSelected_SetVisible_ImageViewShape2d() {
        //select oval
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`(context?.getString(R.string.shape_oval))).perform(click())
        onView(withId(R.id.imageViewShape2d)).check(matches(isDisplayed()))

        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(R.id.imageViewShape2d)
            val actualBitmap = (imageView.drawable as BitmapDrawable).bitmap
            val expectedBitmap = getBitmapFromDrawable(R.drawable.oval)
            assertEquals(actualBitmap, expectedBitmap)
        }
    }

    @Test
    fun isUtilityFunctionCorrect_getImageDrawable() {
        val expectedBitmap = getBitmapFromDrawable(R.drawable.parallelogram)
        val actualDrawable = Utils.getImageDrawable(
            context?.getString(R.string.shape_parallelogram)!!, context?.resources!!
        )
        val actualBitmap = (actualDrawable as BitmapDrawable).bitmap
        assertEquals(expectedBitmap, actualBitmap)
    }

    //Gets the drawable object associated with a resource ID
    // and returns the bitmap used by this drawable to render
    private fun getBitmapFromDrawable(resourceId: Int): Bitmap {
        val drawable = ResourcesCompat.getDrawable(context!!.resources, resourceId, null)
        return (drawable as BitmapDrawable).bitmap
    }

    //Waits for as long as a toast message is displayed so as messages not to get mixed
    private fun waitForToastMessage() {
        Thread.sleep(Toast.LENGTH_LONG.toLong())
    }
}