package gparap.cloud.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        //display progress bar
        ProgressBar progressBar = findViewById(R.id.progressBarSplash);
        progressBar.setVisibility(View.VISIBLE);

        //splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //user is signed-in
                if (auth.getCurrentUser() != null) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                //user is guest
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 1667);
    }
}