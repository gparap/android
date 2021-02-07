package gparap.apps.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

/**
 * Created by gparap on 2020-10-12.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //hide action bar
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Objects.requireNonNull(this.getSupportActionBar()).hide();
            }else {
                this.getSupportActionBar().hide();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        //splash
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //goto main activity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1667);
    }
}