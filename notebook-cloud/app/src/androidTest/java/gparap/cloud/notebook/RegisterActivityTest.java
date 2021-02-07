package gparap.cloud.notebook;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class RegisterActivityTest {
    ActivityScenario<RegisterActivity> activityScenario;
    @Before
    public void setUp(){
        activityScenario = ActivityScenario.launch(RegisterActivity.class);
        Intents.init();
    }

    @Test
    public void onSupportNavigateUp() {
        onView(withId(R.id.home)).perform(click()); //TODO: fix
        intended(hasComponent(LoginActivity.class.getName()));
    }
}