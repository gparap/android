package gparap.apps.spinner;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by gparap on 2020-08-13.
 */
public class MainActivity extends AppCompatActivity {
    final int animationDuration = 1000;
    Random randomRotation = new Random();
    int fromDegrees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get widgets
        Button buttonSpin = (Button) findViewById(R.id.button_spin);
        final ImageView spinner = (ImageView) findViewById(R.id.image_spinner);

        //click button to rotate spinner
        buttonSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //randomize end rotation degrees
                int toDegrees = randomRotation.nextInt(3600);

                //set up rotation
                final RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(animationDuration);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());                      //smooth stopping
                rotateAnimation.setFillAfter(true);                                                 //keep image as it is rotated

                //rotate spinner
                spinner.startAnimation(rotateAnimation);

                //reset start rotation degrees
                fromDegrees = toDegrees;

            }
        });

    }
}