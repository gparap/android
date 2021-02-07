package gparap.cloud.todo_list;

import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class LoginActivityTest {
    final static String TEXT_EMAIL          = "email";
    final static String TEXT_NEXT           = "NEXT";
    final static String TEXT_SIGN_IN        = "SIGN IN";
    final static String TEXT_GOOGLE_ACCOUNT = "NONE OF THE ABOVE";
    final static String USER_EMAIL          = "user1@com.com";
    final static String USER_PASSWORD       = "123456";

    @Before
    public void setUp() {
        ActivityScenario.launch(LoginActivity.class);
        Intents.init();
    }

    @Test
    public void SignIn_UpWithEmail() throws UiObjectNotFoundException {
        //sign-in button
        onView(withId(R.id.buttonLogin)).perform(click());
        skipGoogleAccountPrompt();

        //e-mail button
        onView(withText(containsString(TEXT_EMAIL))).perform(click());
        skipGoogleAccountPrompt();

        //enter e-mail
        onView(withClassName(containsString(EditText.class.getSimpleName()))).perform(typeText(USER_EMAIL));
        onView(withText(equalToIgnoringCase((TEXT_NEXT)))).perform(click());
        closeSoftKeyboard();

        //enter password
        onView(withClassName(containsString(EditText.class.getSimpleName()))).perform(typeText(USER_PASSWORD));
        onView(withText(containsString(TEXT_SIGN_IN))).perform(click());
        closeSoftKeyboard();

        //test intent
        intended(hasComponent(MainActivity.class.getName()));
    }

    /**
     * Simulates the click of "NONE OF THE ABOVE" button in google's user account dialog prompt.
     *
     * @throws UiObjectNotFoundException UiObjectNotFoundException
     */
    private void skipGoogleAccountPrompt() throws UiObjectNotFoundException {
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject uiObject = uiDevice.findObject(new UiSelector().text(TEXT_GOOGLE_ACCOUNT));
        if (uiObject.exists())
            uiObject.click();
    }
}