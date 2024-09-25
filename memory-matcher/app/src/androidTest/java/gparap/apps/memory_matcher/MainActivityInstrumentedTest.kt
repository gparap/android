package gparap.apps.memory_matcher

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_grid() {
        onView(withId(R.id.layout_memory_grid)).check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Run only in portrait mode")
    fun areVisible_gridCards_2x4() {
        onView(withId(R.id.grid_row_0_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_2_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_2_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_3_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_3_col_1)).check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Run only in landscape mode")
    fun areVisible_gridCards_4x2_land() {
        onView(withId(R.id.grid_row_0_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_2)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_0_col_3)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_0)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_1)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_2)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_row_1_col_3)).check(matches(isDisplayed()))
    }

    private fun getDrawableFromImageView(viewId: Int): Drawable? {
        var drawable: Drawable? = null
        activityScenario.onActivity {
            val imageView = it.findViewById<ImageView>(viewId)
            drawable = imageView.drawable
        }
        return drawable
    }

    @Test
    fun isCardFlippingCorrectly() {
        val viewId = R.id.grid_row_0_col_0

        //get the card drawable before flipping
        val cardDrawableBeforeFlipping = getDrawableFromImageView(viewId)
        val cardBitmapDrawableBeforeFlipping = convertDrawableToBitmap(cardDrawableBeforeFlipping!!)

        //click the 1st card (flip the card) and get its drawable
        onView(withId(R.id.grid_row_0_col_0)).perform(click())
        val cardDrawableAfterFlipping = getDrawableFromImageView(viewId)
        val cardBitmapDrawableAfterFlipping = convertDrawableToBitmap(cardDrawableAfterFlipping!!)

        //images must NOT be the same
        assert(!cardBitmapDrawableBeforeFlipping.sameAs(cardBitmapDrawableAfterFlipping))
    }

    @Test
    fun isCardFlippingBackCorrectly() {
        val viewId = R.id.grid_row_0_col_0

        //get the card drawable before flipping
        val cardDrawableBeforeFlipping = getDrawableFromImageView(viewId)
        val cardBitmapDrawableBeforeFlipping = convertDrawableToBitmap(cardDrawableBeforeFlipping!!)

        //click the 1st card (flip the card) and get its drawable
        //click the 2nd card (activate a card pair flip reset)
        onView(withId(viewId)).perform(click())
        onView(withId(R.id.grid_row_0_col_1)).perform(click())
        Thread.sleep(1667)  //TODO: this is the flip reset timeout
        val cardDrawableAfterFlipping = getDrawableFromImageView(viewId)
        val cardBitmapDrawableAfterFlipping = convertDrawableToBitmap(cardDrawableAfterFlipping!!)

        //images must be the same
        assert(cardBitmapDrawableBeforeFlipping.sameAs(cardBitmapDrawableAfterFlipping))
    }

    private fun convertDrawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        return bitmap
    }
}