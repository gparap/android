package gparap.apps.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //get user email & password
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val email = editTextEmail.text.toString()
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val password = editTextPassword.text.toString()

        //create web service client
        val service = RetrofitClient().create()

        //validate input & login user
        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            //validate the user input
            if (Validator.isEmpty(email) || Validator.isEmpty(password)) {
                println("Error: empty input")
                return@setOnClickListener
            }
            if (!Validator.isEmailValid(email)) {
                println("Error: invalid email")
                return@setOnClickListener
            }

            //login user
            service.create(AuthenticationService::class.java)
                .signInWithEmailAndPassword(email, password)
                ?.enqueue(object : Callback<AuthenticationModel?> {
                    override fun onResponse(
                        call: Call<AuthenticationModel?>,
                        response: Response<AuthenticationModel?>
                    ) {
                        println(response.body()?.status)
                        println(response.body()?.msg)
                        println(response.message())
                    }

                    override fun onFailure(call: Call<AuthenticationModel?>, t: Throwable) {
                        println(t.message.toString())
                    }
                })
        }

        //goto registration
        findViewById<TextView>(R.id.textViewGoToRegistration).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}