package gparap.cloud.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        //display progress bar
        ProgressBar progressBar = findViewById(R.id.progressBarSplash);
        progressBar.setVisibility(View.VISIBLE);

        //splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //user in new
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                //user is signed-in
                else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 1667);
    }
}