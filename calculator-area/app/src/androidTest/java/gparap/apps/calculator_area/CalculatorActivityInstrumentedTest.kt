package gparap.apps.calculator_area

import android.view.View
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
    fun isDisplayed_LabelArea(){
        onView(withId(R.id.labelArea)).check(matches(isDisplayed()))
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

    //INPUT FIELDS - ACTIVATION/DE-ACTIVATION
    @Test
    fun squareItemSelected_Activate_SideA_Deactivate_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Square")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun rectangleItemSelected_Activate_SideA_Deactivate_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Rectangle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(isEnabled()))
        onView(withId(R.id.editTextHeight)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun parallelogramItemSelected_Activate_SideA_Height_Deactivate_SideB_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Parallelogram")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextHeight)).check(matches(isEnabled()))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun equilateralTriangleItemSelected_Activate_SideA_Deactivate_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Equilateral Triangle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun triangleItemSelected_Activate_SideA_Height_Deactivate_SideB_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Triangle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextHeight)).check(matches(isEnabled()))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun trapezoidItemSelected_Activate_SideA_SideB_Height_Deactivate_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Trapezoid")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(isEnabled()))
        onView(withId(R.id.editTextHeight)).check(matches(isEnabled()))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun hexagonItemSelected_Activate_SideA_Deactivate_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Hexagon")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(isEnabled()))
        onView(withId(R.id.editTextSideB)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextDiameter)).check(matches(not(isEnabled())))
    }
    @Test
    fun circleItemSelected_Activate_SideA_Deactivate_SideB_Height_Diameter() {
        onView(withId(R.id.spinnerShapes2D)).perform(click())
        onData(`is`("Circle")).perform(click())
        onView(withId(R.id.editTextSideA)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextSideB)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextHeight)).check(matches(not(isEnabled())))
        onView(withId(R.id.editTextDiameter)).check(matches(isEnabled()))
    }
    //endof INPUT FIELDS - ACTIVATION/DE-ACTIVATION

    //INPUT FIELDS - VALIDATION
    @Test
    fun validateInput_Error_FieldMustNotBeEmpty() {
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withText(R.string.toast_EnterValue))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))
    }
    //endof INPUT FIELDS - VALIDATION
}