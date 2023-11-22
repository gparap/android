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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.ui.auth.LoginActivity;
import gparap.apps.e_commerce.utils.AppConstants;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Check if user is authenticated and proceed accordingly //TODO: splash animation
        new Handler(getMainLooper()).postDelayed(() -> {
            //get shared preferences
            SharedPreferences preferences = getSharedPreferences(AppConstants.SHARED_PREFERENCES, MODE_PRIVATE);
            String username = preferences.getString(AppConstants.USERNAME, "");
            String userLoginStatus = preferences.getString(AppConstants.USER_LOGIN_STATUS, AppConstants.LOGIN_STATUS_0);

            //check login status and redirect accordingly
            if (userLoginStatus.equals(AppConstants.LOGIN_STATUS_1)) {
                //user is authenticated - goto main activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(AppConstants.USERNAME, username);
                startActivity(intent);

            } else {
                //user is not authenticated - goto login activity //TODO: pass username if exists
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }

            //close splash screen
            finish();

        }, 1667);
    }
}