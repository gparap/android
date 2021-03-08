package gparap.apps.anagram_color_finder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by gparap on 2020-10-12.
 */
public class MainActivity extends AppCompatActivity {
    TextView textViewAnagram;   //displays color value as an anagram
    EditText editTextAnswer;    //for user input
    Button buttonSubmit;        //user submit button
    String color;               //a random value from colors array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //restore color value
        color = null;
        if (savedInstanceState != null){
            color = savedInstanceState.getString("color");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        textViewAnagram = findViewById(R.id.textViewAnagram);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        //form color anagram
        if (color == null) {
            displayAnagram();
        }
        
       //check answer
       buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if answer is empty
                if (editTextAnswer.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your answer.", Toast.LENGTH_SHORT).show();
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
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //save color value
        outState.putString("color", color);
    }

    /**
     * Forms anagram for a random picked color.
     *
     * @return anagram
     */
    private String formAnagram() {
        //choose random color
        color = Colors.colors[new Random().nextInt(Colors.colors.length)];

        //check if color is more than one word
        StringBuilder stringBuilder = new StringBuilder();
        String[] colorWords = color.split(" ");

        //form anagram for every color word
        for (String colorWord : colorWords) {
            List<String> anagram = Arrays.asList(colorWord.split(""));
            Collections.shuffle(anagram);

            //form anagram
            for (String s : anagram) {
                stringBuilder.append(s);
            }
            stringBuilder.append(" ");
        }
        //trim last space
        stringBuilder.trimToSize();

        //return anagram
        return stringBuilder.toString();
    }

    /**
     * Clears input field and displays new anagram.
     */
    private void displayAnagram() {
        //clear input field
        editTextAnswer.setText("");

        //display anagram
        textViewAnagram.setText(formAnagram());
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
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //return to main
                        dialog.dismiss();

                        //reset anagram
                        displayAnagram();
                    }
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
