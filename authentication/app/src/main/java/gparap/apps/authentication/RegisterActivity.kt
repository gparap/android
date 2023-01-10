package gparap.apps.authentication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.authentication.api.AuthenticationService
import gparap.apps.authentication.api.RetrofitClient
import gparap.apps.authentication.data.AuthenticationModel
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
        val username: EditText = findViewById<EditText>(R.id.editTextUsername)
        val email = findViewById<EditText>(R.id.editTextEmail_register)
        val password = findViewById<EditText>(R.id.editTextPassword_register)

        //TODO: validate password confirmation

        //create web service client
        val service = RetrofitClient().create()

        //register user
        findViewById<Button>(R.id.buttonRegister).setOnClickListener {
            service.create(AuthenticationService::class.java).createUserWithEmailAndPassword(
                username.text.toString(), email.text.toString(), password.text.toString()
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