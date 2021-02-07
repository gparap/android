package gparap.cloud.notebook;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class NoteActivityTest {
    ActivityScenario<NoteActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(NoteActivity.class);
    }

    @Test
    public void isActivityDisplayed() {
        onView(withId(R.id.noteActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void isActivityToolbarDisplayed() {
        onView(withId(R.id.toolbarNote)).check(matches(isDisplayed()));
    }

    @Test
    public void isActivityContentDisplayed() {
        onView(withId(R.id.toolbarNote)).check(matches(isDisplayed()));
    }

    @Test
    public void isActivityButtonSaveDisplayed() {
        onView(withId(R.id.fabSave)).check(matches(isDisplayed()));
    }

    @Test
    public void goBackHome() {
        activityScenario.onActivity(NoteActivity::gotoHome);
        onView(withId(R.id.activityMain)).check(matches(isDisplayed()));
    }

    @Test
    public void areTitleAndDetailsFilledBeforeSavingNote() {
        onView(withId(R.id.editTextNoteTitle)).perform(ViewActions.typeText("dummy"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextNoteDetails)).perform(ViewActions.typeText("dummy"));

        activityScenario.onActivity(activity -> {
            Assert.assertFalse(activity.editTextNoteTitle.getText().toString().isEmpty());
            Assert.assertFalse(activity.editTextNoteDetails.getText().toString().isEmpty());
        });
    }
}