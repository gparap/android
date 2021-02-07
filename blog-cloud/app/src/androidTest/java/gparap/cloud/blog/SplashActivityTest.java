package gparap.cloud.blog;

import android.os.SystemClock;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

public class SplashActivityTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(SplashActivity.class);
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void gotoLoginActivityIfUserIsNotAuthenticated(){
        //sign out user if they're signed in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            FirebaseAuth.getInstance().signOut();
        }

        SystemClock.sleep(1667);

        //test
        intended(hasComponent(LoginActivity.class.getName()));
    }

    @Test
    public void gotoMainActivityIfUserIsAuthenticated(){
        //sign in user if they're not signed in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            FirebaseAuth.getInstance().signInAnonymously();
        }

        SystemClock.sleep(1667);

        //test
        intended(hasComponent(MainActivity.class.getName()));
    }
}