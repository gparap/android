/*
 * Copyright 2023 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.e_commerce.ui.auth;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;

import org.junit.Before;
import org.junit.Test;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.ui.auth.RegisterActivity;

public class RegisterActivityInstrumentedTest {
    private View decorView;

    @Before
    public void setUp() {
        ActivityScenario<RegisterActivity> activityScenario = ActivityScenario.launch(RegisterActivity.class);

        //get the root view of this activity
        activityScenario.onActivity(activity ->
                decorView = activity.getWindow().getDecorView()
        );
    }

    @Test
    public void isVisible_AppLogo() {
        onView(withId(R.id.image_view_register)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_Email() {
        onView(withId(R.id.edit_text_register_email)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_Username() {
        onView(withId(R.id.edit_text_register_username)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_Password() {
        onView(withId(R.id.edit_text_register_password)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_PasswordConfirmation() {
        onView(withId(R.id.edit_text_register_confirm_password)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_LoginRegister() {
        onView(withId(R.id.button_register)).check(matches(isDisplayed()));
    }

    @Test
    public void isNotVisible_ProgressBar() {
        onView(withId(R.id.progress_register)).check(matches(not(isDisplayed())));
    }

    @Test
    public void registrationFailed_userAlreadyExistsInTheDatabase() {
        //using test credentials
        String email = "test_user@dot.com"; //e-mail from existing user in the database
        String username = "test_user";
        String password = "123123";

        //try to register user
        onView(withId(R.id.edit_text_register_email)).perform(click());
        onView(withId(R.id.edit_text_register_email)).perform(typeText(email));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_register_username)).perform(click());
        onView(withId(R.id.edit_text_register_username)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_register_password)).perform(click());
        onView(withId(R.id.edit_text_register_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_register_confirm_password)).perform(click());
        onView(withId(R.id.edit_text_register_confirm_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_register)).perform(click());

        //assert registration failed
        onView(withText(R.string.text_register_failed))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void registrationSuccessful_redirectToMainActivity() {
        //using test credentials
        String email = "no_user@dot.com"; //non-existing user in the database
        String username = "test_user";
        String password = "123456";

        //register user
        onView(withId(R.id.edit_text_register_email)).perform(click());
        onView(withId(R.id.edit_text_register_email)).perform(typeText(email));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_register_username)).perform(click());
        onView(withId(R.id.edit_text_register_username)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_register_password)).perform(click());
        onView(withId(R.id.edit_text_register_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_register_confirm_password)).perform(click());
        onView(withId(R.id.edit_text_register_confirm_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_register)).perform(click());

        //assert registration is successful
        onView(withId(R.id.layout_activity_main)).check(matches(isDisplayed()));

        //!!! important
        //!!! To replicate this test, delete manually this user from the database
    }

}