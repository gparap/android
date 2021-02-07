package gparap.cloud.todo_list;

import android.os.SystemClock;
import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class MainActivityTest {
    final static int FIREBASE_LOGOUT_TASK_MAX_DURATION = 5000;
    final static String TEXT_SIGN_OUT = "Sign out";
    final static String TEXT_TODO     = "todo0";
    final static String TEXT_ADD      = "ADD";
    final static String TEXT_DELETE   = "DELETE";
    ActivityScenario<MainActivity> activityScenario;
    int itemsCountOld;  //helper for recycler view item count
    int itemsCountNew;  //helper for recycler view item count

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(MainActivity.class);
        Intents.init();
        itemsCountOld = -1;
        itemsCountNew = -1;
    }

    @Test
    public void startLoginActivityIfUserIsSignedOut() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
            onView(withText(TEXT_SIGN_OUT)).perform(click());
            SystemClock.sleep(FIREBASE_LOGOUT_TASK_MAX_DURATION);
        }
        intended(hasComponent(LoginActivity.class.getName()));
        Intents.release();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void addTODO() throws UiObjectNotFoundException {
        //get items count
        activityScenario.onActivity(activity -> itemsCountOld = activity.recyclerView.getAdapter().getItemCount());

        //enter to-do
        onView(withId(R.id.fabAddToDo)).perform(click());
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject uiObjectExitText = uiDevice.findObject(new UiSelector().className(EditText.class));
        uiObjectExitText.legacySetText(TEXT_TODO);

        //add to-do
        UiObject uiObjectButton = uiDevice.findObject(new UiSelector().text(TEXT_ADD));
        uiObjectButton.click();

        //check if to-do added
        activityScenario.onActivity(activity -> itemsCountNew = activity.recyclerView.getAdapter().getItemCount());
        assert itemsCountNew != itemsCountOld;
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void deleteTODO() throws UiObjectNotFoundException {
        //get items count
        activityScenario.onActivity(activity -> itemsCountOld = activity.recyclerView.getAdapter().getItemCount());

        //delete to-do
        activityScenario.onActivity(activity -> activity.recyclerView.getChildAt(0).performLongClick());
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject uiObjectDelete = uiDevice.findObject(new UiSelector().text(TEXT_DELETE));
        uiObjectDelete.click();

        //check if to-do deleted
        activityScenario.onActivity(activity -> itemsCountNew = activity.recyclerView.getAdapter().getItemCount());
        assert itemsCountNew != itemsCountOld;
    }
}