/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.calculator_bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import gparap.apps.calculator_bmi.utils.Utils;

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

        //display BMI and category
        if (isValidated) {
            float bmi = Utils.getInstance().calculateBMI(weight, height);
            textViewBMI.setText(Utils.getInstance().getDecimalFormat(bmi));
            textViewCategory.setText(Utils.getInstance().getBMICategory(bmi));
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