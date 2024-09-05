package gparap.apps.cryptocurrency

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
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
    fun isVisible_recycler_view_cryptos() {
        onView(withId(R.id.recycler_view_cryptos)).check(matches(isDisplayed()))
    }

    @Test
    fun notEmpty_recycler_view_cryptos() {
        Thread.sleep(1667)  //!!! wait for the web service response (adjust millis)
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view_cryptos)
            val adapter = recyclerView.adapter
            assert((adapter?.itemCount ?: 0) > 0) { "RecyclerView is empty" }
        }
    }
}