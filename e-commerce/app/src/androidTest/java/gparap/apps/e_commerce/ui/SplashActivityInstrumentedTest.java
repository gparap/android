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
package gparap.apps.e_commerce.ui;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.utils.AppConstants;

public class SplashActivityInstrumentedTest {
    @SuppressWarnings("FieldCanBeLocal")
    private final String user = "test_user";

    @Test
    public void isVisible_appLogo() {
        ActivityScenario<SplashActivity> scenario = ActivityScenario.launch(SplashActivity.class);
        onView(withId(R.id.image_view_app_logo_splash)).check(matches(isDisplayed()));
        scenario.close();
    }

    @Test
    public void isCorrect_UserIsLoggedIn() throws InterruptedException {
        //log-out user
        editSharedPreferences(AppConstants.LOGIN_STATUS_1);

        //wait for splash and start scenario
        ActivityScenario<SplashActivity> scenario = ActivityScenario.launch(SplashActivity.class);
        Thread.sleep(1667);

        //test here
        onView(withId(R.id.layout_activity_main)).check(matches(isDisplayed()));

        //close running scenario
        scenario.close();
    }

    @Test
    public void isCorrect_UserIsLoggedOut() throws InterruptedException {
        //log-out user
        editSharedPreferences(AppConstants.LOGIN_STATUS_0);

        //wait for splash and start scenario
        ActivityScenario<SplashActivity> scenario = ActivityScenario.launch(SplashActivity.class);
        Thread.sleep(1667);

        //test here
        onView(withId(R.id.layout_activity_login)).check(matches(isDisplayed()));

        //close running scenario
        scenario.close();
    }

    /** Update SharedPreferences by logging-in/out a test user. */
    private void editSharedPreferences(String loginStatus) {
        SharedPreferences preferences = ApplicationProvider.getApplicationContext().getSharedPreferences(AppConstants.SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppConstants.USERNAME, user);
        editor.putString(AppConstants.USER_LOGIN_STATUS, loginStatus);
        editor.apply();
    }
}