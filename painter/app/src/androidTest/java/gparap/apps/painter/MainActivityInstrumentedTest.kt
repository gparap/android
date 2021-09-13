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
package gparap.apps.painter

import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_CanvasArea() {
        onView(withId(R.id.main_canvas_area)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageViewColorPicker() {
        onView(withId(R.id.imageViewColorPicker)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageViewEraser() {
        onView(withId(R.id.imageViewEraser)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageViewClearCanvas() {
        onView(withId(R.id.imageViewClearCanvas)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_seekBarPenSize() {
        onView(withId(R.id.seekBarPenSize)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageViewPenSize() {
        onView(withId(R.id.imageViewPenSize)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewPenSize() {
        onView(withId(R.id.textViewPenSize)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageViewSave() {
        onView(withId(R.id.imageViewSave)).check(matches(isDisplayed()))
    }

    @Test
    fun onSeekBarChange_showChangedPenSizeValue() {
        //init the SeekBar progress to 0
        onView(withId(R.id.seekBarPenSize)).perform(setProgress(0))

        //change the SeekBar progress
        val progress = 39
        onView(withId(R.id.seekBarPenSize)).perform(setProgress(progress))

        //get the pen size value
        var penSize = 0
        activityScenario.onActivity {
            val textView = it.findViewById<TextView>(R.id.textViewPenSize)
            penSize = textView.text.toString().toInt()
        }

        assert(penSize == progress)
    }

    //ViewAction for changing the SeekBar progress
    private fun setProgress(progress: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(SeekBar::class.java)
            }

            override fun getDescription(): String {
                return "change the SeekBar progress"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val seekBar = view?.findViewById<SeekBar>(R.id.seekBarPenSize)
                seekBar?.progress = progress
            }
        }
    }
}