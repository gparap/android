package gparap.games.double_dice_roller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Created by gparap on 2020-09-08.
 */
public class MainActivity extends AppCompatActivity {
    Button buttonRoll;
    ImageView diceLeft, diceRight;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LEFT DICE
                //get a random number 1..6 and compose the String for the resource
                int rollResult = random.nextInt(6) + 1;
                String dice = "dice" + rollResult;
                //get drawable based on drawable's id based on resource's String
                int resourceID = getResources().getIdentifier(dice, "drawable", getPackageName());
                Drawable drawable =  ContextCompat.getDrawable(getApplicationContext(), resourceID) ;
                //set the image for the left dice
                diceLeft.setImageDrawable(drawable);

                //RIGHT DICE
                //get a random number 1..6 and compose the String for the resource
                rollResult = random.nextInt(6) + 1;
                dice = "dice" + rollResult;
                //get drawable based on drawable's id based on resource's String
                resourceID = getResources().getIdentifier(dice, "drawable", getPackageName());
                drawable =  ContextCompat.getDrawable(getApplicationContext(), resourceID) ;
                //set the image for the right dice
                diceRight.setImageDrawable(drawable);
            }
        });
    }

    private void init() {
        //get widgets
        buttonRoll = findViewById(R.id.buttonRoll);
        diceLeft = findViewById(R.id.imageViewDiceLeft);
        diceRight = findViewById(R.id.imageViewDiceRight);
        //seed
        random = new Random();
    }
}
//TODO: layout more centered
//TODO: sound for rolling