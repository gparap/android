package gparap.cloud.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    final int REQUEST_CODE_LOGIN = 999;
    final static String LOG = "LoginActivity: ";
    Toolbar toolbar;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //init toolbar
        toolbar = findViewById(R.id.toolbarLogin);
        toolbar.setTitle("User Authentication");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white, null));
        setSupportActionBar(toolbar);

        //get widgets
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //handle user login result
        if (requestCode == REQUEST_CODE_LOGIN) {
            if (resultCode == RESULT_OK) {
                //toast user name
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null)
                Toast.makeText(this, "Welcome, " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

                //goto main activity
                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentLogin);
                finish();
            } else {
                Log.e(LOG, "something went wrong in user login");
            }
        }
    }

    /**
     * Signs in an existing user or (signs up) a new user.
     *
     * @param view button login
     */
    public void onClickLogin(View view) {
        //configure identity providers
        List<AuthUI.IdpConfig> idpConfigs = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        //create a sign-in intent
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAlwaysShowSignInMethodScreen(true)
                .setAvailableProviders(idpConfigs)
                .setLogo(R.drawable.todolist)
                .build();

        //sign-in
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }
}