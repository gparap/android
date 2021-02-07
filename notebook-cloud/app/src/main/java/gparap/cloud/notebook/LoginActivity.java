package gparap.cloud.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

@SuppressWarnings({"UnnecessarilyQualifiedInnerClassAccess", "Convert2Lambda", "NonConstantResourceId"})
public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button buttonLogin, buttonLoginGuest;
    EditText editTextLoginEmail, editTextLoginPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init toolbar
        toolbar = findViewById(R.id.toolbarRegister);
        toolbar.setTitle("User Login");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLoginGuest = findViewById(R.id.buttonLoginGuest);
        editTextLoginEmail = findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        progressBar = findViewById(R.id.progressBarContinueAsGuest);
    }

    public void onClickLogin(View view) {
        switch (view.getId()) {
            //sign-in existing user
            case R.id.buttonLogin:
                //check if input fields are empty
                if (editTextLoginEmail.getText().toString().isEmpty() || editTextLoginPassword.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please, fill in all input fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //sign in
                progressBar.setVisibility(View.VISIBLE);
                Task<AuthResult> authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextLoginEmail.getText().toString(),
                        editTextLoginPassword.getText().toString());
                authResult.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        //goto main activity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Login failed, please check your credentials.", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            //register new user
            case R.id.buttonLoginRegistration:
                //credentials, if filled, should be transferred in register activity
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                intentRegister.putExtra("e-mail", editTextLoginEmail.getText().toString());
                intentRegister.putExtra("password", editTextLoginPassword.getText().toString());
                intentRegister.putExtra("parent", "LoginActivity");
                startActivity(intentRegister);
                finish();
                break;

            //continue as guest
            case R.id.buttonLoginGuest:
                progressBar.setVisibility(View.VISIBLE);

                //goto main activity as a guest user
                Intent intentGuest = new Intent(LoginActivity.this, MainActivity.class);
                intentGuest.putExtra("user_auth", "anonymous");
                startActivityForResult(intentGuest, 999);
                finish();
                break;
        }
    }
}