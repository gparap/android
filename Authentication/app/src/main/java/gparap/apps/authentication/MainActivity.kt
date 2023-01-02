package gparap.apps.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import gparap.apps.authentication.api.AuthenticationService
import gparap.apps.authentication.api.RetrofitClient
import gparap.apps.authentication.data.AuthenticationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get user email & password
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)

        //TODO: validate input

        //create web service client
        val service = RetrofitClient().create()

        //login user
        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            service.create(AuthenticationService::class.java).signInWithEmailAndPassword(
                email.text.toString(), password.text.toString()
            )?.enqueue(object : Callback<AuthenticationModel?> {
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
    }
}