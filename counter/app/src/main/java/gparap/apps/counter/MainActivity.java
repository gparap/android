package gparap.apps.counter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by gparap on 2020-08-31.
 */
public class MainActivity extends AppCompatActivity {

    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);
    }

    public void AddOne(View view) {
        textViewDisplay.setText(
                String.valueOf(Integer.parseInt(textViewDisplay.getText().toString()) + 1)
        );
    }

    public void AddTen(View view) {
        textViewDisplay.setText(
                String.valueOf(Integer.parseInt(textViewDisplay.getText().toString()) + 10)
        );
    }

    public void AddHundred(View view) {
        textViewDisplay.setText(
                String.valueOf(Integer.parseInt(textViewDisplay.getText().toString()) + 100)
        );
    }

    public void SubtractOne(View view) {
        textViewDisplay.setText(
                String.valueOf(Integer.parseInt(textViewDisplay.getText().toString()) - 1)
        );
    }

    public void SubtractTen(View view) {
        textViewDisplay.setText(
                String.valueOf(Integer.parseInt(textViewDisplay.getText().toString()) - 10)
        );
    }

    public void SubtractHundred(View view) {
        textViewDisplay.setText(
                String.valueOf(Integer.parseInt(textViewDisplay.getText().toString()) - 100)
        );
    }

    public void Clear(View view) {
        textViewDisplay.setText("0");
    }
}