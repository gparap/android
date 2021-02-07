package gparap.apps.calculator_bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by gparap on 2020-09-07.
 */
public class MainActivity extends AppCompatActivity {
    Button buttonFindBMI;
    EditText editTextHeight, editTextWeight;
    TextView textViewBMI, textViewCategory;
    float height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // Init all
    private void init() {
        //get widgets
        buttonFindBMI = findViewById(R.id.buttonFindBMI);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        textViewBMI = findViewById(R.id.textViewBMI);
        textViewCategory = findViewById(R.id.textViewCategory);
        //initialize variables
        height = 0.0f;
        weight = 0;
    }

    // Computes and Displays the body mass index
    // (BMI = weight / (height * height)
    public void FindBMI(View view) {
        //helper
        boolean isValidated = true;

        //check height for dot (".")
        if (!editTextHeight.getText().toString().contains(".")) {
            Toast.makeText(editTextHeight.getContext(), "Enter Correct Height!", Toast.LENGTH_SHORT).show();
            isValidated = false;
        }
        //check if user input height and weight
        try {
            //get height and weight
            height = Float.parseFloat(editTextHeight.getText().toString());
            weight = Float.parseFloat(editTextWeight.getText().toString());

        } catch (Exception e) {
            isValidated = false;
            if (editTextHeight.getText().toString().isEmpty()) {
                Toast.makeText(editTextHeight.getContext(), "Enter Height!", Toast.LENGTH_SHORT).show();
            } else if (editTextWeight.getText().toString().isEmpty()) {
                Toast.makeText(editTextHeight.getContext(), "Enter Weight!", Toast.LENGTH_SHORT).show();
            }
        }

        //proceed to computation
        if (isValidated){
            //find BMI
            double bmi = weight / (height * height);
            DecimalFormat decimalFormat = new DecimalFormat("#.#"); //remove all decimals except 1st

            //display BMI
            textViewBMI.setText(decimalFormat.format(bmi));

            //find BMI category
            if (bmi < 18.5) {
                textViewCategory.setText(R.string.categoryUnderweight);
            } else if (bmi > 18.5 && bmi < 25) {
                textViewCategory.setText(R.string.categoryNormal);
            } else if (bmi > 25 && bmi < 30) {
                textViewCategory.setText(R.string.categoryOverweight);
            } else if (bmi > 30) {
                textViewCategory.setText(R.string.categoryObese);
            }
        }
    }

    // Clears all fields
    public void clearFields(View view) {
        editTextHeight.setText("");
        editTextHeight.setHint("meters");
        editTextWeight.setText("");
        editTextWeight.setHint("kilos");
        textViewBMI.setText("");
        textViewCategory.setText("");
    }
}