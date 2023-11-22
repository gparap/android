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
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;

import org.junit.Before;
import org.junit.Test;

import gparap.apps.e_commerce.R;

public class LoginActivityInstrumentedTest {
    private View decorView;

    @Before
    public void setUp() {
        ActivityScenario<LoginActivity> activityScenario = ActivityScenario.launch(LoginActivity.class);

        //get the root view of this activity
        activityScenario.onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    public void isVisible_AppLogo() {
        onView(withId(R.id.image_view_login)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_Email() {
        onView(withId(R.id.edit_text_login_username)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_Password() {
        onView(withId(R.id.edit_text_login_password)).check(matches(isDisplayed()));
    }

    @Test
    public void isNotVisible_ProgressBar() {
        onView(withId(R.id.progress_login)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isVisible_LoginButton() {
        onView(withId(R.id.button_login)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_UserNotRegisterText() {
        onView(withId(R.id.text_view_user_not_registered)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_RegisterButton() {
        onView(withId(R.id.button_goto_register)).check(matches(isDisplayed()));
    }

    @Test
    public void loginUser_isFailed() {
        //using test credentials
        String username = "test_user";
        String password = "000"; //wrong password

        //try to login user
        onView(withId(R.id.edit_text_login_username)).perform(click());
        onView(withId(R.id.edit_text_login_username)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_login_password)).perform(click());
        onView(withId(R.id.edit_text_login_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_login)).perform(click());

        //asset login failed
        onView(withText(R.string.text_login_failed))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginUser_isSuccessful() {
        //using test credentials
        String username = "test_user";
        String password = "123456"; //correct password
        String welcomeText = ApplicationProvider.getApplicationContext().getResources().getString(R.string.text_welcome_user_after_login) + username;

        //try to login user
        onView(withId(R.id.edit_text_login_username)).perform(click());
        onView(withId(R.id.edit_text_login_username)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_login_password)).perform(click());
        onView(withId(R.id.edit_text_login_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_login)).perform(click());

        //asset login succeeded
        onView(withText(welcomeText))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void redirectToRegistration() {
        onView(withId(R.id.button_goto_register)).perform(click());
        onView(withId(R.id.layout_activity_register)).check(matches(isDisplayed()));
    }

    @Test
    public void redirectToRegistration_passInputDataFromLogin() {
        //test credentials
        String username = "test_user";
        String password = "123456";

        //enter login input and redirect to registration
        onView(withId(R.id.edit_text_login_username)).perform(click());
        onView(withId(R.id.edit_text_login_username)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edit_text_login_password)).perform(click());
        onView(withId(R.id.edit_text_login_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_goto_register)).perform(click());

        //assert data passed successfully
        onView(withId(R.id.edit_text_register_username)).check(matches(withText(username)));
        onView(withId(R.id.edit_text_register_password)).check(matches(withText(password)));
    }
}