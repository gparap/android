package gparap.apps.classifieds;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isLaunched_MarketFragment() {
        onView(withId(R.id.navigation_market)).perform(click());
        onView(withId(R.id.layout_fragment_market)).check(matches(isDisplayed()));
    }

    @Test
    public void isLaunched_DashboardFragment() {
        onView(withId(R.id.navigation_dashboard)).perform(click());
        onView(withId(R.id.layout_fragment_dashboard)).check(matches(isDisplayed()));
    }

    @Test
    public void isLaunched_NotificationsFragment() {
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.layout_fragment_notifications)).check(matches(isDisplayed()));
    }
}