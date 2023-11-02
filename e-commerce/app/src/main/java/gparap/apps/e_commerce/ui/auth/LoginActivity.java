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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.api.AuthService;
import gparap.apps.e_commerce.api.HttpClient;
import gparap.apps.e_commerce.data.AuthResponseModel;
import gparap.apps.e_commerce.ui.MainActivity;
import gparap.apps.e_commerce.utils.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get the input fields
        username = findViewById(R.id.edit_text_login_username);
        password = findViewById(R.id.edit_text_login_password);

        //login user
        findViewById(R.id.button_login).setOnClickListener(view -> {
            //create http client
            Retrofit retrofit = new HttpClient().create();

            //create web service
            Call<AuthResponseModel> call = retrofit.create(AuthService.class).loginUser(
                    username.getText().toString().trim(),
                    password.getText().toString()
            );

            //call web service
            call.enqueue(new Callback<AuthResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<AuthResponseModel> call, @NonNull Response<AuthResponseModel> response) {
                    //get the web service response
                    AuthResponseModel auth = response.body();

                    //if user is authenticated redirect to MainActivity
                    if (auth != null && Objects.equals(auth.getStatus(), AppConstants.AUTH_STATUS_TRUE)) {
                        //update SharedPreferences with username and user login status values
                        SharedPreferences preferences = getSharedPreferences(AppConstants.SHARED_PREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(AppConstants.USERNAME, auth.getUsername());
                        editor.putString(AppConstants.USER_LOGIN_STATUS, AppConstants.LOGIN_STATUS_1);
                        editor.apply();

                        //create an intent for main activity
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra(AppConstants.USERNAME, auth.getUsername());

                        //welcome user
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_welcome_user_after_login) + username.getText().toString(), Toast.LENGTH_SHORT).show();

                        //redirect to main activity
                        startActivity(intent);
                        finish();


                    }else{
                        //inform user that authentication failed
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_login_failed), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AuthResponseModel> call, @NonNull Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });

        //register user
        findViewById(R.id.button_goto_register).setOnClickListener(view->{
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

            //keep input fields data so that user don't have to write it again
            intent.putExtra(AppConstants.USERNAME, username.getText().toString().trim());
            intent.putExtra(AppConstants.PASSWORD, password.getText().toString().trim());

            startActivity(intent);
        });
    }
}