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
package gparap.apps.countdown_timer;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import gparap.apps.countdown_timer.utils.Utils;

public class MainActivity extends AppCompatActivity {
    EditText editTextHours, editTextMinutes, editTextSeconds;
    int hours, minutes, seconds;
    long millisInFuture;
    boolean isTimeCorrect;      //helper to check the correctness of the time field values
    CountDownTimer countDownTimer;
    boolean isCounting;         //helper to control the countdown
    View editTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupCounter();
    }

    private void init() {
        //get widgets
        editTextHours = findViewById(R.id.editTextHours);
        editTextMinutes = findViewById(R.id.editTextMinutes);
        editTextSeconds = findViewById(R.id.editTextSeconds);

        hours = minutes = seconds = 0;
        millisInFuture = 0L;
        isTimeCorrect = true;
        isCounting = false;

        //set colors when input fields are disabled (because of countdown)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editTextHours.setTextColor(getResources().getColor(R.color.colorInputDisabled, null));
            editTextMinutes.setTextColor(getResources().getColor(R.color.colorInputDisabled, null));
            editTextSeconds.setTextColor(getResources().getColor(R.color.colorInputDisabled, null));
        } else {
            editTextHours.setTextColor(getResources().getColor(R.color.colorInputDisabled));
            editTextMinutes.setTextColor(getResources().getColor(R.color.colorInputDisabled));
            editTextSeconds.setTextColor(getResources().getColor(R.color.colorInputDisabled));
        }
    }

    public void onClickStart(View view) {
        startCounter();
    }

    //Sets up the correct time values for the countdown
    private void setupCounter() {
        //correct hours field value
        isTimeCorrect = true;
        editTextHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //get a reference to the edit text
                editTextView = getCurrentFocus();

                //display hint if no value
                editTextHours.setHint("0");

                try {
                    //fix hours ( <= 23)
                    if (Integer.parseInt(editTextHours.getText().toString()) > 23) {
                        isTimeCorrect = false;
                    }
                } catch (Exception ignored) {
                } finally {
                    try {
                        if (!isTimeCorrect) {
                            if (Integer.parseInt(editTextHours.getText().toString()) > 23) {
                                editTextHours.setText(R.string.value_23);
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        });

        //correct minutes field value
        isTimeCorrect = true;
        editTextMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //get a reference to the edit text
                editTextView = getCurrentFocus();

                //display hint if no value
                editTextMinutes.setHint("0");

                try {
                    //fix minutes (<= 59)
                    if (Integer.parseInt(editTextMinutes.getText().toString()) > 59) {
                        isTimeCorrect = false;
                    }
                } catch (Exception ignored) {
                } finally {
                    try {
                        if (!isTimeCorrect)
                            if (Integer.parseInt(editTextMinutes.getText().toString()) > 59) {
                                editTextMinutes.setText(R.string.value_59);
                            }

                    } catch (Exception ignored) {
                    }
                }
            }
        });

        //correct seconds field value
        isTimeCorrect = true;
        editTextSeconds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //get a reference to the edit text
                editTextView = getCurrentFocus();

                //display hint if no value
                editTextSeconds.setHint("0");

                try {
                    //fix seconds (<= 60)
                    if (Integer.parseInt(editTextSeconds.getText().toString()) > 59) {
                        isTimeCorrect = false;
                    }
                } catch (Exception ignored) {
                } finally {
                    try {

                        if (!isTimeCorrect)
                            if (Integer.parseInt(editTextSeconds.getText().toString()) > 59) {
                                editTextSeconds.setText(R.string.value_59);
                            }
                    } catch (Exception ignored) {
                    }
                }
            }
        });
    }

    //Start the countdown
    private void startCounter() {
        if (!isCounting) {

            //get time from the edit fields
            if (!editTextHours.getText().toString().equals("")) {
                hours = Integer.parseInt(editTextHours.getText().toString());
                editTextHours.setText(String.valueOf(hours));       //remove redundant zeros
            }
            if (!editTextMinutes.getText().toString().equals("")) {
                minutes = Integer.parseInt(editTextMinutes.getText().toString());
                editTextMinutes.setText(String.valueOf(minutes));   //remove redundant zeros
            }
            if (!editTextSeconds.getText().toString().equals("")) {
                seconds = Integer.parseInt(editTextSeconds.getText().toString());
                editTextSeconds.setText(String.valueOf(seconds));   //remove redundant zeros
            }

            //compute the total number of milliseconds in the future until the countdown is done
            millisInFuture = Utils.getInstance().convertSecondsToMillis(seconds);
            millisInFuture += Utils.getInstance().convertMinutesToMillis(minutes);
            millisInFuture += Utils.getInstance().convertHoursToMillis(hours);

            //start the countdown
            countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //display seconds
                    seconds -= 1;
                    editTextSeconds.setText(String.valueOf(seconds));

                    //display minutes
                    if (seconds < 0) {
                        seconds = 59;
                        editTextSeconds.setText(String.valueOf(seconds));
                        minutes -= 1;
                        editTextMinutes.setText(String.valueOf(minutes));
                    }

                    //display hours
                    if (minutes < 0) {
                        minutes = 59;
                        editTextMinutes.setText(String.valueOf(minutes));
                        hours -= 1;
                        editTextHours.setText(String.valueOf(hours));
                    }
                }

                @Override
                public void onFinish() {
                    isCounting = false;

                    //register an intent to be broadcast by the receiver
                    Intent intent = new Intent(MainActivity.this, CountdownReceiver.class);
                    @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent
                            .getBroadcast(getApplicationContext(), 0, intent, 0);

                    //create an alarm manager to schedule the broadcast
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
                }
            };

            //check if not all fields are zero
            if ((!editTextHours.getText().toString().isEmpty() && !editTextHours.getText().toString().equals("0")) ||
                    (!editTextMinutes.getText().toString().isEmpty() && !editTextMinutes.getText().toString().equals("0")) ||
                    (!editTextSeconds.getText().toString().isEmpty() && !editTextSeconds.getText().toString().equals("0"))) {

                //start countdown
                countDownTimer.start();
                isCounting = true;

                //close virtual keyboard if start button is pressed
                if (editTextView != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(editTextView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                //disable input fields
                editTextHours.setEnabled(false);
                editTextMinutes.setEnabled(false);
                editTextSeconds.setEnabled(false);
            }
        }
    }

    //Reset the counter
    public void OnClickButtonReset(View view) {
        //stop countdown
        if (countDownTimer != null)
            countDownTimer.cancel();
        isCounting = false;

        //clear values
        hours = minutes = seconds = 0;

        //clear fields
        editTextHours.setText("");
        editTextMinutes.setText("");
        editTextSeconds.setText("");

        //show hints
        editTextHours.setHint("0");
        editTextMinutes.setHint("0");
        editTextSeconds.setHint("0");

        //enable input fields
        editTextHours.setEnabled(true);
        editTextMinutes.setEnabled(true);
        editTextSeconds.setEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //save state
        outState.putInt("hours", hours);
        outState.putInt("minutes", minutes);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isCounting", isCounting);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //restore  state
        isCounting = savedInstanceState.getBoolean("isCounting");
        hours = savedInstanceState.getInt("hours");
        minutes = savedInstanceState.getInt("minutes");
        seconds = savedInstanceState.getInt("seconds");

        //there was an orientation change
        //and the timer was counting
        if (isCounting) {
            //restart the counter
            isCounting = false;
            startCounter();
            isCounting = true;
        }
    }
}