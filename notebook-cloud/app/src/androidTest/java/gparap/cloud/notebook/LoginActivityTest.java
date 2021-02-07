package gparap.cloud.notebook;

import android.content.ComponentName;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginActivityTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(LoginActivity.class);
        Intents.init();
    }

    @Test
    public void startMainActivity() {
        onView(withId(R.id.buttonLoginGuest)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void startRegisterActivity() {
        onView(withId(R.id.buttonLoginRegistration)).perform(click());
        intended(hasComponent(RegisterActivity.class.getName()));
    }
}