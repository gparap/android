package gparap.cloud.notebook;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class MainActivityTest {

    ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(MainActivity.class);
        Intents.init();
    }

    @Test
    public void isDrawerLayoutDisplayed(){
        onView(withId(R.id.activityMain)).check(matches(isDisplayed()));
    }

    @Test
    public void isDrawerLayoutContentDisplayed(){
        onView(withId(R.id.drawerContent)).check(matches(isDisplayed()));
    }

    @Test
    public void isDrawerLayoutNavigationNotDisplayed(){
        onView(withId(R.id.drawerNavigation)).check(matches(not(isDisplayed())));
    }

    @Test
    public void openNavigationDrawer(){
        onView(withId(R.id.activityMain)).perform(DrawerActions.open());
        onView(withId(R.id.activityMain)).check(matches(isDisplayed()));
    }

    @Test
    public void startNoteActivityFromAddNoteButton(){
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.noteActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void startNoteActivityFromAddNoteMenuItem(){
        openNavigationDrawer();
        onView(withId(R.id.noteAdd)).perform(click());
        onView(withId(R.id.noteActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void openRecyclerViewItem(){
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
    }

    @Test
    public void startRegisterActivityIfUserIsNotRegistered(){
        FirebaseAuth.getInstance().signInAnonymously();
        openNavigationDrawer();
        onView(withId(R.id.noteLogin)).perform(click());
        intended(hasComponent(RegisterActivity.class.getName()));
    }
}