package gparap.apps.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by gparap on 2020-08-31.
 */
public class CalculatorActivity extends AppCompatActivity {
    TextView textViewDisplay;   //displays the result
    String result = "";         //final result of operation
    int operations = 0;         //number of operations allowed
    char operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        textViewDisplay = findViewById(R.id.textViewDisplay);

        //restore calculator state
        if (savedInstanceState != null) {
            result = savedInstanceState.getString("result");
            textViewDisplay.setText(savedInstanceState.getCharSequence("textViewDisplay"));
            operations = savedInstanceState.getInt("operations");
            operation = savedInstanceState.getChar("operation");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //save calculator state
        outState.putString("result", result);
        outState.putCharSequence("textViewDisplay", textViewDisplay.getText());
        outState.putInt("operations", operations);
        outState.putChar("operation", operation);
    }

    //display the number (0..9)
    public void OnButtonClickNumber(View view) {
        CharSequence tempText = ((Button) view).getText();

        //disappear multiple zeros in front of number - 1st operand
        if (result.length() == 0 && tempText.toString().equals("0"))
            tempText = "0";
        if (result.length() == 1 && result.charAt(0) == '0') {
            if (!tempText.toString().equals("0"))
                result = "";
            if (tempText.toString().equals("0"))
                tempText = "";
        }

        if (!result.isEmpty()){
            String []operands;

            //check if expression starts with negative or positive number
            if (result.charAt(0) == '-') {
                operands = result.substring(1).split("[+[-[*[/[%]]]]]");
            } else {
                operands = result.split("[+[-[*[/[%[\\^]]]]]]");
            }

            //disappear multiple zeros in front of number - 2nd operand
            if (operands.length == 2 && operands[1].length() == 1 && operands[1].charAt(0) == '0') {
                if (!tempText.toString().equals("0"))
                    result = result.substring(0, result.length()-1);
                if (tempText.toString().equals("0"))
                    tempText = "";
            }
        }

        //display ongoing expression
        result += tempText.toString();

        //add to display
        textViewDisplay.setText(result);
    }

    //display the operator ("+", "-", "*", "/", "%", "^")
    public void OnButtonClickOperation(View view) {
        CharSequence tempText = ((Button) view).getText();

        //dont display operator in the start except "-"
        if (result.equals("")) {
            result = "0";
        }

        //handle operations
        if (tempText.toString().equals("+") || tempText.toString().equals("-") ||
            tempText.toString().equals("*") || tempText.toString().equals("/") ||
            tempText.toString().equals("%") || tempText.toString().equals("^")) {

            //check if more than 1 operation is pressed
            if (operations == 0) {
                operations++;
                operation = tempText.charAt(0);

                //display ongoing expression
                result += tempText.toString();

            } else if (operations == 1) {

                //check if last index of display was an operation
                if (result.charAt(result.length() - 1) == '+' || result.charAt(result.length() - 1) == '-' ||
                    result.charAt(result.length() - 1) == '*' || result.charAt(result.length() - 1) == '/' ||
                    result.charAt(result.length() - 1) == '%' || result.charAt(result.length() - 1) == '^') {

                    //change operation
                    operation = tempText.charAt(0);
                    result = result.substring(0, result.length() - 1);

                    //display ongoing expression
                    result += tempText.toString();
                }
            }
        }
        //add to display
        textViewDisplay.setText(result);
    }

    //delete last input
    public void OnButtonClickDelete(View view) {
        //check if empty
        if (result.length() == 0){
            return;
        }

        //check if only one input
        if (result.length() == 1){
            result = "0";

            //add to display
            textViewDisplay.setText(result);
            return;
        }

        //remove operation
        char helperChar = result.charAt(result.length()-1);
        if (helperChar == '+' || helperChar == '-' || helperChar == '*' || helperChar == '/' ||
            helperChar == '%' || helperChar == '^') {
            operations--;
        }
        result = result.substring(0, result.length()-1);

        //add to display
        textViewDisplay.setText(result);
    }

    //clear everything
    public void OnButtonClickClear(View view) {
        reset();
    }

    //find the result of operation
    public void OnButtonClickEquals(View view) {
        if (result.isEmpty()) {
            return;
        }

        //dont do scientific number operations
        if (result.contains("E")) {
            reset();
            Toast.makeText(this, R.string.toast_unsupportedOperation, Toast.LENGTH_SHORT).show();
            return;
        }

        //expression starts with negative number
        String[] operands;
        if (result.charAt(0) == '-') {
            operands = result.substring(1).split("[+[-[*[/[%[\\^]]]]]]");
            operands[0] = "-".concat(operands[0]);
        } else {
            operands = result.split("[+[-[*[/[%[\\^]]]]]]");
        }

        //dont eveluate if operand is missing
        if (operands.length != 2) {
            return;
        }

        //evaluate expression
        Double tempResult = null;
        boolean isDivisionByZero = false;
        switch (operation) {
            case '+':
                tempResult = CalculatorOperationsKt.addTwoNumbers(
                        Double.parseDouble(operands[0]), Double.parseDouble(operands[1]));
                break;
            case '-':
                tempResult = CalculatorOperationsKt.subtractTwoNumbers(
                        Double.parseDouble(operands[0]), Double.parseDouble(operands[1]));
                break;
            case '*':
                tempResult = CalculatorOperationsKt.multiplyTwoNumbers(
                        Double.parseDouble(operands[0]), Double.parseDouble(operands[1]));
                break;
            case '/':
                // check for division by zero
                if (Double.parseDouble(operands[1]) == 0) {
                    isDivisionByZero = true;
                } else {
                    tempResult = CalculatorOperationsKt.divideTwoNumbers(
                            Double.parseDouble(operands[0]), Double.parseDouble(operands[1]));
                }
                break;
            case '%':
                if (Double.parseDouble(operands[0]) < 0 || Double.parseDouble(operands[1]) == 0) {
                    reset();
                    Toast.makeText(textViewDisplay.getContext(), R.string.toast_givePositiveNumbers, Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    tempResult = CalculatorOperationsKt.moduloTwoNumbers(Double.parseDouble(operands[0]),
                            Double.parseDouble(operands[1]));
                }
                break;
            case '^':
                tempResult = CalculatorOperationsKt.powerTwoNumbers(Double.parseDouble(operands[0]),
                        Double.parseDouble(operands[1]));
                break;
        }

        //check if it is a physical number and remove decimal zero (.0)
        result = String.valueOf(tempResult);

        if (result.endsWith(".0")){
            result = result.substring(0,result.length()-2);
        }
        //remove digits after decimal (ie 1.666666667... => 1.6667)
        if (result.contains(".") && !result.contains("E")) {
            try {
                result = result.substring(0, result.indexOf(".") + 5);
            }catch (Exception ignored){}
        }
        //remove digits after decimal with E (ie 1.66666667E67... => 1.6667E)
        if (result.contains("E")) {
            int indexDot = result.indexOf(".");
            int indexScN = result.indexOf("E");
            String resultOld = result;
            System.out.println(result);

            //add 4 decimals points
            if (indexScN - indexDot > 5) {
                result = result.substring(0, result.indexOf(".") + 5);
            }

            //try to add max 2 digits after scientific notation
            // (considering the dot)
            if (!result.contains("E")) {
                result = result.concat("E");
                for (int i=1; i<3; i++) {
                    try{
                        result = result.concat(String.valueOf(resultOld.charAt(indexScN + i)));
                    }catch (Exception ignored){}
                }
            }
        }

        textViewDisplay.setText(result);
        operations = 0;
        if (isDivisionByZero) {
            reset();
            Toast.makeText(textViewDisplay.getContext(), R.string.toast_divisionByZero, Toast.LENGTH_SHORT).show();
        }
    }

    //handle dot input
    public void OnButtonClickDot(View view) {
        //starting display is "0"
        if (result == null || result.equals("")) {
            result = "0.";
        }

        //initialize string builder with existing text
        StringBuilder tempResult = new StringBuilder();
        tempResult.append(result);

        //give dot to the last of operands
        String[] operands = result.split("[+[-[*[/[%]]]]]");
        if (operands.length == 1) {
            if (!operands[0].contains(".")) {
                tempResult.append(".");
            }
        } else if (operands.length == 2) {
            if (!operands[1].contains(".")) {
                tempResult.append(".");
            }
        }
        else {
            if (!operands[2].contains(".")) {
                tempResult.append(".");
            }
        }

        //add to display
        result = tempResult.toString();
        textViewDisplay.setText(result);
    }

    //reset the calculator
    private void reset() {
        textViewDisplay.setText("0");
        result = "";
        operations = 0;
    }
}
