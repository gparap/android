package gparap.cloud.todo_list;

import android.os.SystemClock;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

public class SplashActivityTest {
    FirebaseAuth firebaseAuth;

    @Before
    public void setUp(){
        ActivityScenario.launch(SplashActivity.class);
        Intents.init();
    }

    @Test
    public void startLoginActivityIfUserIsNew(){
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            firebaseAuth.signOut();
        }
        SystemClock.sleep(1667);
        intended(hasComponent(LoginActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void startMainActivityIfUserIsSignedIn(){
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            SystemClock.sleep(1667);
            intended(hasComponent(MainActivity.class.getName()));
        }
        Intents.release();
    }
}