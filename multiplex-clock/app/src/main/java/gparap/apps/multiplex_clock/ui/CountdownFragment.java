/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.multiplex_clock.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import gparap.apps.multiplex_clock.utils.PreferencesManager;

public class CountdownFragment extends Fragment {
    private EditText editTextHours, editTextMinutes, editTextSeconds;
    private Button buttonStart, buttonStop, buttonReset;
    private int hours, minutes, seconds;
    private CountDownTimer countDownTimer;
    private boolean isCounting;

    public CountdownFragment() {
    }

    public static CountdownFragment newInstance() {
        CountdownFragment fragment = new CountdownFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);
        getFragmentWidgets(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupCountdown();
        addOnClickListeners();
        addTextChangedListeners();
    }

    @Override
    public void onPause() {
        super.onPause();

        //save state
        PreferencesManager.getInstance().saveInteger(getActivity(), "hours",hours);
        PreferencesManager.getInstance().saveInteger(getActivity(), "minutes",minutes);
        PreferencesManager.getInstance().saveInteger(getActivity(), "seconds",seconds);
        PreferencesManager.getInstance().saveBoolean(getActivity(), "isCounting",isCounting);
    }

    @Override
    public void onResume() {
        super.onResume();

        //restore  state
        isCounting = PreferencesManager.getInstance().getBoolean(getActivity(), "isCounting", false);
        hours = PreferencesManager.getInstance().getInteger(getActivity(),"hours",0);
        minutes = PreferencesManager.getInstance().getInteger(getActivity(),"minutes",0);
        seconds = PreferencesManager.getInstance().getInteger(getActivity(),"seconds",0);

        //there was an orientation change
        //and the timer was counting
        if (isCounting){
            //restart the counter
            isCounting = false;
            if (countDownTimer != null)
                countDownTimer.cancel();
            startCountdown();
            isCounting = true;
        }
    }

    private void setupCountdown() {
        isCounting = PreferencesManager.getInstance().getBoolean(getActivity(), "isCounting", false);
        hours = PreferencesManager.getInstance().getInteger(getActivity(),"hours",0);
        minutes = PreferencesManager.getInstance().getInteger(getActivity(),"minutes",0);
        seconds = PreferencesManager.getInstance().getInteger(getActivity(),"seconds",0);
        editTextHours.setText(String.valueOf(hours));
        editTextMinutes.setText(String.valueOf(minutes));
        editTextSeconds.setText(String.valueOf(seconds));

        //set colors when input fields are disabled (because of countdown)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editTextHours.setTextColor(getResources().getColor(R.color.black, null));
            editTextMinutes.setTextColor(getResources().getColor(R.color.black, null));
            editTextSeconds.setTextColor(getResources().getColor(R.color.black, null));
        }else{
            editTextHours.setTextColor(getResources().getColor(R.color.black));
            editTextMinutes.setTextColor(getResources().getColor(R.color.black));
            editTextSeconds.setTextColor(getResources().getColor(R.color.black));
        }
    }

    private void startCountdown() {
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
            long millisInFuture = 0L;
            millisInFuture = seconds * 1000;
            millisInFuture += (minutes * 60 * 1000);
            millisInFuture += (hours * 60 * 60 * 1000);

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

//                    //register an intent to be broadcast by the receiver
//                    Intent intent = new Intent(MainActivity.this, CountdownReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent
//                            .getBroadcast(getApplicationContext(), 0, intent, 0);
//
//                    //create an alarm manager to schedule the broadcast
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
                }
            };

            //check if not all fields are zero
            if ((!editTextHours.getText().toString().isEmpty() && !editTextHours.getText().toString().equals("0"))     ||
                    (!editTextMinutes.getText().toString().isEmpty() && !editTextMinutes.getText().toString().equals("0")) ||
                    (!editTextSeconds.getText().toString().isEmpty() && !editTextSeconds.getText().toString().equals("0"))) {

                //start countdown
                countDownTimer.start();
                isCounting = true;

//                //close virtual keyboard if start button is pressed
//                if (focusedView != null){
//                    InputMethodManager inputMethodManager = (InputMethodManager) this.getActivity().getSystemService(INPUT_METHOD_SERVICE);
//                    inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                }

                //disable input fields
                editTextHours.setEnabled(false);
                editTextMinutes.setEnabled(false);
                editTextSeconds.setEnabled(false);
            }
        }
    }

    private void stopCountdown() {
        //stop countdown
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        isCounting = false;
    }

    private void resetCountdown() {
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

    private void addTextChangedListeners() {
        editTextHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                //fix hours (must be <= 23)
                if (!isCounting && !editTextHours.getText().toString().isEmpty()) {
                    if (Integer.parseInt(editTextHours.getText().toString()) > 23) {
                        editTextHours.setText(R.string.value_23);
                    }
                }
            }
        });
        editTextMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                //fix minutes (must be <= 59)
                if (!isCounting && !editTextMinutes.getText().toString().isEmpty()) {
                    if (Integer.parseInt(editTextMinutes.getText().toString()) > 59) {
                        editTextMinutes.setText(R.string.value_59);
                    }
                }
            }
        });
        editTextSeconds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                //fix seconds (must be <= 59)
                if (!isCounting && !editTextSeconds.getText().toString().isEmpty()) {
                    if (Integer.parseInt(editTextSeconds.getText().toString()) > 59) {
                        editTextSeconds.setText(R.string.value_59);
                    }
                }
            }
        });
    }

    private void addOnClickListeners() {
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountdown();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCountdown();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCountdown();
            }
        });
    }

    private void getFragmentWidgets(View view) {
        editTextHours = view.findViewById(R.id.editTextHours);
        editTextMinutes = view.findViewById(R.id.editTextMinutes);
        editTextSeconds = view.findViewById(R.id.editTextSeconds);
        buttonStart = view.findViewById(R.id.buttonStartCountdown);
        buttonStop = view.findViewById(R.id.buttonStopCountdown);
        buttonReset = view.findViewById(R.id.buttonResetCountdown);
    }
}