package gparap.apps.authentication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.authentication.api.AuthenticationService
import gparap.apps.authentication.api.RetrofitClient
import gparap.apps.authentication.data.AuthenticationModel
import gparap.apps.authentication.utils.Validator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //get user details
        val editTextUsername: EditText = findViewById<EditText>(R.id.editTextUsername)
        val username = editTextUsername.text.toString()
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail_register)
        val email = editTextEmail.text.toString()
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword_register)
        val password = editTextPassword.text.toString()
        val editTextPasswordConfirm = findViewById<EditText>(R.id.editTextPasswordConfirm_register)
        val passwordConfirm = editTextPasswordConfirm.text.toString()

        //create web service client
        val service = RetrofitClient().create()

        //validate input & register user
        findViewById<Button>(R.id.buttonRegister).setOnClickListener {
            //validate the user input
            if (Validator.isEmpty(username) || Validator.isEmpty(email)
                || Validator.isEmpty(password) || Validator.isEmpty(passwordConfirm)
            ) {
                println("Error: empty input")
                return@setOnClickListener
            }
            if (!Validator.isEmailValid(email)) {
                println("Error: invalid email")
                return@setOnClickListener
            }
            if (!Validator.isPasswordLongEnough(password)) {
                println("Error: Password too short")
                return@setOnClickListener
            }
            if (!Validator.isPasswordTooLong(password)) {
                println("Error: Password too long")
                return@setOnClickListener
            }
            if (!Validator.doPasswordsMatch(password, passwordConfirm)) {
                println("Error: Passwords do not match")
                return@setOnClickListener
            }

            //register user
            service.create(AuthenticationService::class.java).createUserWithEmailAndPassword(
                username, email, password
            )?.enqueue(object : Callback<AuthenticationModel?> {
                override fun onResponse(
                    call: Call<AuthenticationModel?>,
                    response: Response<AuthenticationModel?>
                ) {
                    println(response.body()?.status)
                    println(response.body()?.msg)
                }

                override fun onFailure(call: Call<AuthenticationModel?>, t: Throwable) {
                    println(t.localizedMessage?.toString())
                }
            })
        }
    }
}