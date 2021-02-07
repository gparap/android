package gparap.apps.color_shift;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by gparap on 2020-09-01.
 */
public class MainActivity extends AppCompatActivity {
    //helpers
    int colorPrevious = -1;
    final int COLORS_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //change the background color of the app
        changeColor();
    }

    // Change color at random amongst red, green, blue
    private void changeColor() {
        //get widgets
        final ConstraintLayout layout = findViewById(R.id.layoutMain);
        Button buttonColor = findViewById(R.id.buttonColor);

        //change to random color
        buttonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int color;

                //keep changing the color (so as to not be the same)
                do{
                    color = Math.abs(random.nextInt()) % COLORS_COUNT;
                }while (color == colorPrevious);

                //set background color
                switch (color){
                    case 0:
                        layout.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        layout.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        layout.setBackgroundColor(Color.BLUE);
                        break;
                }

                //hold a reference to the current color (as previous)
                colorPrevious = color;
            }
        });
    }
}