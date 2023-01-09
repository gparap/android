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
            val response = service.create(AuthenticationService::class.java)
                .createUserWithEmailAndPassword(
                    username.text.toString(), email.text.toString(), password.text.toString()
                )
                ?.enqueue(object : Callback<AuthenticationModel?> {
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