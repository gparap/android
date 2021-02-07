package gparap.cloud.notebook;

import android.os.SystemClock;

import androidx.test.core.app.ActivityScenario;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SplashActivityTest {

    @Test
    public void userIsSignedIn(){
        //sign in
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInAnonymously();

        ActivityScenario.launch(SplashActivity.class);
        SystemClock.sleep(1667);
        onView(withId(R.id.activityMain)).check(matches(isDisplayed()));

        //sign-out
        Objects.requireNonNull(firebaseAuth.getCurrentUser()).delete();
        firebaseAuth.signOut();
    }

    @Test
    public void userIsNotSignedIn() {
        ActivityScenario.launch(SplashActivity.class);
        SystemClock.sleep(1667);
        onView(withId(R.id.activityLogin)).check(matches(isDisplayed()));
    }
}