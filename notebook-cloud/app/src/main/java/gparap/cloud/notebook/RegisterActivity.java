package gparap.cloud.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

@SuppressWarnings("Convert2Lambda")
public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText editTextUsername, editTextEmail, editTextPassword, editTextPasswordConfirm;
    String parentActivity;
    DatabaseManager databaseManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init manager for database operations
        databaseManager = new DatabaseManager();
        databaseManager.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //init toolbar
        toolbar = findViewById(R.id.toolbarRegister);
        toolbar.setTitle(R.string.register);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get widgets
        editTextUsername = findViewById(R.id.editTextRegisterUsername);
        editTextEmail = findViewById(R.id.editTextRegisterEmail);
        editTextPassword = findViewById(R.id.editTextRegisterPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextLoginPassword);
        progressBar = findViewById(R.id.progressBarRegister);

        //get e-mail and password from intend (if filled)
        editTextEmail.setText(getIntent().getStringExtra("e-mail"));
        editTextPassword.setText(getIntent().getStringExtra("password"));

        //get parent activity from intent
        parentActivity = getIntent().getStringExtra("parent");
    }

    /**
     * Registers a new user to the database.
     *
     * @param view button register
     */
    public void OnClickRegister(View view) {
        //check if input fields are empty
        if (editTextUsername.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() ||
                editTextPassword.getText().toString().isEmpty() || editTextPasswordConfirm.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please, fill in all input fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        //check if passwords match
        if (!editTextPassword.getText().toString().equals(editTextPasswordConfirm.getText().toString())) {
            editTextPasswordConfirm.setError("Passwords do not match.");
            return;
        }

        //register new user if they have signed in anonymously
        if (databaseManager.getFirebaseAuth().getCurrentUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            linkUserWithCredential();
        }

        //register new user if they have not signed in at all
        else {
            progressBar.setVisibility(View.VISIBLE);
            Task<AuthResult> taskSignIn = databaseManager.getFirebaseAuth().signInAnonymously();
            taskSignIn.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            linkUserWithCredential();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        gotoParentActivity();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoParentActivity();
    }

    /**
     * Registers a new user to database.
     */
    private void linkUserWithCredential() {
        Task<AuthResult> taskLink = databaseManager.linkUserWithCredential(editTextEmail.getText().toString(),
                                                                           editTextPassword.getText().toString());
        taskLink.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(RegisterActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();

                //update user profile information
                Task<Void> task = databaseManager.updateUserProfile(editTextUsername.getText().toString());
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //goto main activity
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Goes back to the activity that intended this activity.
     */
    private void gotoParentActivity() {
        if (parentActivity.equals("MainActivity")) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        } else if (parentActivity.equals("LoginActivity")) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        finish();
    }
}