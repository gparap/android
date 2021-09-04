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
package gparap.apps.calculator_tip;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

import gparap.apps.calculator_tip.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Button buttonMinusFive, buttonPlusFive, buttonMinusOne, buttonPlusOne;
    TextView textViewTipPercentage, textViewAmountTip, textViewAmountTotal,
            textViewPersonsToSplit, textViewAmountPerPerson;
    EditText editTextBill;
    String helperWithZeros; //to help with no zeros at the start of a number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgets();
        addListeners();
    }

    /**
     * Computes tip amount, total bill amount and amount split per persons and
     * updates text fields with currency formatted amounts
     */
    private void computeAmounts() {
        //compute amounts
        float amountTip = 0, amountTotal = 0, amountPerPerson = 0;
        try {
            amountTip = Utils.getInstance().getTip(editTextBill.getText().toString(), textViewTipPercentage.getText().toString());
            amountTotal = Utils.getInstance().getBill(editTextBill.getText().toString(), amountTip);
            amountPerPerson = Utils.getInstance().getSplit(amountTotal, textViewPersonsToSplit.getText().toString());
        } catch (Exception ignored) {
        }

        //update text fields
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.getDefault());
        textViewAmountTip.setText(currencyInstance.format(amountTip));
        textViewAmountTotal.setText(currencyInstance.format(amountTotal));
        textViewAmountPerPerson.setText(currencyInstance.format(amountPerPerson));
    }

    /**
     * Updates the tip percentage based on the button pressed and
     * updates the persons to split based on the button pressed
     *
     * @param v the button pressed
     */
    @Override
    public void onClick(View v) {
        //get current tip percentage
        int tipPercentage = Integer.parseInt(textViewTipPercentage.getText().toString());
        int personsToSplit = Integer.parseInt(textViewPersonsToSplit.getText().toString());

        //subtract tip percentage by five (-5)
        if (v.getId() == R.id.buttonMinusFive) {
            tipPercentage -= 5;
            //don't go below zero
            if (tipPercentage < 0) {
                tipPercentage = 0;
            }
        }

        //add tip percentage by five (+5)
        if (v.getId() == R.id.buttonPlusFive) {
            tipPercentage += 5;
            //don't go above one hundred
            if (tipPercentage > 100) {
                tipPercentage = 100;
            }
        }

        //subtract person to split by one (-1)
        if (v.getId() == R.id.buttonMinusOne) {
            personsToSplit -= 1;
            //don't go below one person
            if (personsToSplit < 1) {
                personsToSplit = 1;
            }
        }

        //add person to split by one (+1)
        if (v.getId() == R.id.buttonPlusOne) {
            personsToSplit += 1;
        }

        //update tip percentage and persons to split fields
        textViewTipPercentage.setText(String.valueOf(tipPercentage));
        textViewPersonsToSplit.setText(String.valueOf(personsToSplit));

        //get total amounts
        computeAmounts();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //get the number before it is changed as a backup
        helperWithZeros = editTextBill.getText().toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //remove zero(s) from the start
        if (editTextBill.getText().toString().startsWith("0")) {
            if (helperWithZeros.equals("") || helperWithZeros.equals("0") || helperWithZeros.endsWith("0")) {
                editTextBill.setText("");
            } else if (helperWithZeros.contains("0.")) {
                helperWithZeros = helperWithZeros.substring(1);
                editTextBill.setText(helperWithZeros);
            } else
                editTextBill.setText(helperWithZeros);
        }
        //get total amounts
        computeAmounts();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private void addListeners() {
        buttonMinusFive.setOnClickListener(this);
        buttonPlusFive.setOnClickListener(this);
        buttonMinusOne.setOnClickListener(this);
        buttonPlusOne.setOnClickListener(this);
        editTextBill.addTextChangedListener(this);
    }

    private void getWidgets() {
        buttonMinusFive = findViewById(R.id.buttonMinusFive);
        buttonPlusFive = findViewById(R.id.buttonPlusFive);
        buttonMinusOne = findViewById(R.id.buttonMinusOne);
        buttonPlusOne = findViewById(R.id.buttonPlusOne);
        textViewTipPercentage = findViewById(R.id.textViewTipPercentage);
        editTextBill = findViewById(R.id.editTextBill);
        textViewAmountTip = findViewById(R.id.textViewAmountTip);
        textViewAmountTotal = findViewById(R.id.textViewAmountTotal);
        textViewPersonsToSplit = findViewById(R.id.textViewPersonsToSplit);
        textViewAmountPerPerson = findViewById(R.id.textViewAmountPerPerson);
    }
}