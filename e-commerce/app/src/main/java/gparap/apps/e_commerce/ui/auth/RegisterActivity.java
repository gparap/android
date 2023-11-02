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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import gparap.apps.e_commerce.ui.MainActivity;
import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.api.AuthService;
import gparap.apps.e_commerce.api.HttpClient;
import gparap.apps.e_commerce.data.AuthResponseModel;
import gparap.apps.e_commerce.utils.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    private EditText username, password, passwordConfirm, email;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWidgets();

        //get input data from intent (if any) and update these input fields
        username.setText(getIntent().getStringExtra(AppConstants.USERNAME));
        password.setText(getIntent().getStringExtra(AppConstants.PASSWORD));

        //TODO: validate input data & password confirmation

        //register user
        register.setOnClickListener(view -> {
            if (!email.getText().toString().isEmpty()
                    && !username.getText().toString().isEmpty()
                    && !email.getText().toString().isEmpty()
                    && !email.getText().toString().isEmpty()) {

                //create Http client
                Retrofit httpClient = new HttpClient().create();

                //create web service
                Call<AuthResponseModel> call = httpClient.create(AuthService.class).registerUser(
                        email.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString()
                );

                //call web service to register user
                call.enqueue(new Callback<AuthResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthResponseModel> call, @NonNull Response<AuthResponseModel> response) {
                        //get the web service response
                        AuthResponseModel auth = (AuthResponseModel) response.body();

                        //if user is registered redirect to MainActivity
                        if (auth != null && Objects.equals(auth.getStatus(), AppConstants.AUTH_STATUS_TRUE)) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra(AppConstants.USERNAME, auth.getUsername());

                            //welcome user
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_welcome_user_after_register) + username.getText().toString(), Toast.LENGTH_SHORT).show();

                            startActivity(intent);
                            finish();

                        }else{
                            //inform user that registration failed
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_register_failed), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthResponseModel> call, @NonNull Throwable t) {
                        System.out.println(t.getLocalizedMessage());
                    }
                });
            }
        });

    }

    private void getWidgets() {
        username = findViewById(R.id.edit_text_register_username);
        password = findViewById(R.id.edit_text_register_password);
        passwordConfirm = findViewById(R.id.edit_text_register_confirm_password);
        email = findViewById(R.id.edit_text_register_email);
        register = findViewById(R.id.button_register);
    }
}