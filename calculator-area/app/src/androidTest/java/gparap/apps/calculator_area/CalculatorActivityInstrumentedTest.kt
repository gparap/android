package gparap.apps.calculator_area

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.ui.UiController
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by gparap on 2021-02-12.
 */
@RunWith(AndroidJUnit4::class)
class CalculatorActivityInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)
    var rootView: View? = null

    @Before
    fun setUp() {
        //get the top-level window decor view
        activityScenarioRule.scenario.onActivity { activity ->
            rootView = activity.window.decorView
        }

        //launch activity
        ActivityScenario.launch(CalculatorActivity::class.java)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.calculator_area", appContext.packageName)
    }

    //VISIBILITY OF WIDGETS
    @Test
    fun isDisplayed_Spinner2D(){
        onView(withId(R.id.spinnerShapes2D)).check(matches(isDisplayed()))
    }
    @Test
    fun isDisplayed_EditTextSideA(){
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
    }
    @Test
    fun isDisplayed_EditTextSideB(){
        onView(withId(R.id.editTextSideB)).check(matches(isDisplayed()))
    }
    @Test
    fun isDisplayed_EditTextHeight(){
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
    }
    @Test
    fun isDisplayed_EditTextDiameter(){
        onView(withId(R.id.editTextDiameter)).check(matches(isDisplayed()))
    }
    @Test
    fun isDisplayed_EditTextResult(){
        onView(withId(R.id.textViewResult)).check(matches(isDisplayed()))
    }
    @Test
    fun isDisplayed_ButtonCalculate(){
        onView(withId(R.id.buttonCalculate)).check(matches(isDisplayed()))
    }
    //endof VISIBILITY OF WIDGETS

    //FUNCTIONALITY OF WIDGETS - BASIC
    @Test
    fun clickSpinnerToshowListOfShapes2D() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Hexagon")).perform(click())
        onView(withId(R.id.spinnerShapes2D)).check(matches(isDisplayed()))
    }

    @Test
    fun inputSideA() {
        onView(withId(R.id.editTextSideA)).perform(typeText("1"), closeSoftKeyboard())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
    }
    @Test
    fun inputSideB() {
        onView(withId(R.id.editTextSideB)).perform(typeText("1"), closeSoftKeyboard())
        onView(withId(R.id.editTextSideB)).check(matches(isDisplayed()))
    }
    @Test
    fun inputSideHeight() {
        onView(withId(R.id.editTextHeight)).perform(typeText("1"), closeSoftKeyboard())
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
    }
    @Test
    fun inputSideDiameter() {
        onView(withId(R.id.editTextDiameter)).perform(typeText("1"), closeSoftKeyboard())
        onView(withId(R.id.editTextDiameter)).check(matches(isDisplayed()))
    }
    //endof FUNCTIONALITY OF WIDGETS

    //INPUT FIELDS - CHANGE VISIBILITY
    @Test
    fun squareItemSelected_SetVisible_SideA_SetInvisible_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Square")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun rectangleItemSelected_SetVisible_SideA_SetInvisible_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Rectangle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun parallelogramItemSelected_SetVisible_SideA_Height_SetInvisible_SideB_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Parallelogram")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun equilateralTriangleItemSelected_SetVisible_SideA_SetInvisible_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Equilateral Triangle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun triangleItemSelected_SetVisible_SideA_Height_SetInvisible_SideB_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Triangle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun trapezoidItemSelected_SetVisible_SideA_SideB_Height_SetInvisible_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Trapezoid")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun hexagonItemSelected_SetVisible_SideA_SetInvisible_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Hexagon")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isDisplayed())))
    }
    @Test
    fun circleItemSelected_SetVisible_SideA_SetInvisible_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Circle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextSideB)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isDisplayed())))
        onView(withId(R.id.editTextDiameter)).check(matches(isDisplayed()))
    }
    //endof INPUT FIELDS - CHANGE VISIBILITY

    //INPUT FIELDS - VALIDATION
    @Test
    fun validateInput_Error_FieldMustNotBeEmpty() {
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EnterValue))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }
    @Test
    fun validateInput_Error_SidesCannotBeEqual_Parallelogram() {
        //enter values
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Parallelogram")).perform(click())
        onView(withId(R.id.editTextSideA)).perform(typeText("10"))
        onView(withId(R.id.editTextHeight)).perform(typeText("10"))

        //close keyboard
        onView(isRoot()).perform(closeSoftKeyboard())

        //test
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EqualValues_Parallelogram))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }
    @Test
    fun validateInput_Error_SidesCannotBeEqual_Trapezoid_SideAWithHeight() {
        //enter values
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Trapezoid")).perform(click())
        onView(withId(R.id.editTextSideA)).perform(typeText("10"))
        onView(withId(R.id.editTextSideB)).perform(typeText("1"))   //indifferent
        onView(withId(R.id.editTextHeight)).perform(typeText("10"))

        //close keyboard
        onView(isRoot()).perform(closeSoftKeyboard())

        //test
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EqualValues_Trapezoid))
            .inRoot(withDecorView(not(`is`(rootView))))
            .check(matches(isDisplayed()))
    }
    @Test
    fun validateInput_Error_SidesCannotBeEqual_Trapezoid_SideBWithHeight() {
        //enter values
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Trapezoid")).perform(click())
        onView(withId(R.id.editTextSideA)).perform(typeText("1"))   //indifferent
        onView(withId(R.id.editTextSideB)).perform(typeText("10"))
        onView(withId(R.id.editTextHeight)).perform(typeText("10"))

        //close keyboard
        onView(isRoot()).perform(closeSoftKeyboard())

        //test
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EqualValues_Trapezoid))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }
    //endof INPUT FIELDS - VALIDATION
}