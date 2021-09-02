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
package gparap.apps.anagram_color_finder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import gparap.apps.anagram_color_finder.utils.Utils;

public class MainActivity extends AppCompatActivity {
    TextView textViewAnagram;   //displays color value as an anagram
    EditText editTextAnswer;    //for user input
    Button buttonSubmit;        //user submit button
    String color;               //a random value from colors array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get widgets
        textViewAnagram = findViewById(R.id.textViewAnagram);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        //restore color value
        color = null;
        if (savedInstanceState != null) {
            color = savedInstanceState.getString(getString(R.string.key_color));
            textViewAnagram.setText(savedInstanceState.getString(getString(R.string.key_anagram)));
        }

        //form color anagram
        if (color == null) {
            init();
        }

        //check answer
        buttonSubmit.setOnClickListener(v -> {
            //check if answer is empty
            if (editTextAnswer.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, getString(R.string.toast_enter_answer), Toast.LENGTH_SHORT).show();
            } else {
                //hide virtual keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editTextAnswer.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                //check answer
                if (editTextAnswer.getText().toString().equalsIgnoreCase(color)) {
                    createDialog(true, null);
                } else {
                    createDialog(false, color);
                }
                //reset color and anagram
                init();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //save color and anagram value
        outState.putString(getString(R.string.key_color), color);
        outState.putString(getString(R.string.key_anagram), textViewAnagram.getText().toString());
    }

    /**
     * Initializes picked color and its anagram.
     */
    private void init() {
        color = Utils.getInstance().getColorAtRandom();
        displayAnagram();
    }

    /**
     * Clears input field and displays new anagram.
     */
    private void displayAnagram() {
        //clear input field
        editTextAnswer.setText("");

        //display anagram
        textViewAnagram.setText(Utils.getInstance().formAnagram(color));
    }

    /**
     * Creates an alert dialog to inform user about correctness of their answer.
     *
     * @param isCorrect     if user answer is correct or not
     * @param correctAnswer the correct answer
     */
    private void createDialog(boolean isCorrect, @Nullable String correctAnswer) {
        //Create dialog
        @SuppressLint("InflateParams") AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                //.setTitle("Checking your Answer...")
                .setView(getLayoutInflater().inflate(R.layout.dialog_main, null))
                .setPositiveButton("OK", (dialog1, which) -> {
                    //return to main
                    dialog1.dismiss();

                    //reset anagram
                    displayAnagram();
                }).create();
        dialog.show();
        //get dialog widgets
        TextView textViewAnswer = dialog.findViewById(R.id.textViewAnswer);
        TextView textViewLabelAnswerCorrect = dialog.findViewById(R.id.textViewLabelAnswerCorrect);
        TextView textViewAnswerCorrect = dialog.findViewById(R.id.textViewAnswerCorrect);

        //check answer correctness
        if (isCorrect) {
            textViewAnswer.setText(R.string.answer_correct);

            //hide correct answer
            textViewLabelAnswerCorrect.setVisibility(View.INVISIBLE);
            textViewAnswerCorrect.setVisibility(View.INVISIBLE);
        } else {
            textViewAnswer.setText(R.string.answer_wrong);

            //show correct answer
            textViewAnswerCorrect.setText(correctAnswer);
            textViewLabelAnswerCorrect.setVisibility(View.VISIBLE);
            textViewAnswerCorrect.setVisibility(View.VISIBLE);
        }
    }
}
