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

import android.content.res.Configuration;
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
import gparap.apps.multiplex_clock.utils.TimeUtils;

public class CountdownFragment extends Fragment {
    private EditText editTextHours, editTextMinutes, editTextSeconds;
    private Button buttonStart, buttonStop, buttonReset;
    private CountDownTimer countDownTimer;
    private boolean isCounting;
    private long millisInFuture;
    private long backroungTime;

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

        //restore countdown state
        isCounting = PreferencesManager.getInstance().getBoolean(getActivity(), "isCounting", false);
        millisInFuture = PreferencesManager.getInstance().getLong(getActivity(), "millisInFuture", 0L);
        if (isCounting) {
            backroungTime = PreferencesManager.getInstance().getLong(getActivity(), "backroungTime", 0L);
            millisInFuture = backroungTime - System.currentTimeMillis();
            if (millisInFuture < 0) {
                millisInFuture = 0;
                isCounting = false;
            } else {
                updateTextFieldsInputState(false);
                isCounting = false;
                startCountdown();
            }
        }
        updateTextFieldsToHHMMSS();
        addOnClickListeners();
        addTextChangedListeners();
    }

    @Override
    public void onPause() {
        super.onPause();

        //save state
        PreferencesManager.getInstance().saveBoolean(getActivity(), "isCounting", isCounting);
        PreferencesManager.getInstance().saveLong(getActivity(), "millisInFuture", millisInFuture);
        PreferencesManager.getInstance().saveLong(getActivity(), "backroungTime", backroungTime);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startCountdown() {
        if (!isCounting) {

            //setup timer
            if (isInputEnabled()) {
                getTimeOutputFromTextFields();
                getMillisInFuture();
            }
            backroungTime = System.currentTimeMillis() + millisInFuture;

            //start the countdown
            isCounting = true;
            updateTextFieldsInputState(false);
            countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    millisInFuture = millisUntilFinished;
                    updateTextFieldsToHHMMSS();
                }

                @Override
                public void onFinish() {
                    resetCountdown();
                }
            };
            countDownTimer.start();
        }
    }

    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isCounting = false;
        updateTextFieldsInputState(true);
    }

    private void resetCountdown() {
        if (countDownTimer != null)
            countDownTimer.cancel();
        isCounting = false;
        millisInFuture = 0;
        updateTextFieldsInputState(true);
        clearTextFields();
    }

    private void addTextChangedListeners() {
        editTextHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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

    private boolean isInputEnabled() {
        if (editTextHours.isEnabled() || editTextMinutes.isEnabled() || editTextSeconds.isEnabled()) {
            return true;
        }
        return false;
    }

    private void updateTextFieldsInputState(boolean state) {
        editTextHours.setEnabled(state);
        editTextMinutes.setEnabled(state);
        editTextSeconds.setEnabled(state);

        //don't use the default color for disabled state
        if (state == false) {
            restoreEditTextEnabledStateColor();
        }
    }

    private void restoreEditTextEnabledStateColor() {
        int nightModeEnabled = getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        //set color for day mode to black
        if (nightModeEnabled == Configuration.UI_MODE_NIGHT_NO) {
            editTextHours.setTextColor(getResources().getColor(R.color.black));
            editTextMinutes.setTextColor(getResources().getColor(R.color.black));
            editTextSeconds.setTextColor(getResources().getColor(R.color.black));
            return;
        }
        //set color for dark mode to white
        if (nightModeEnabled == Configuration.UI_MODE_NIGHT_YES) {
            editTextHours.setTextColor(getResources().getColor(R.color.white));
            editTextMinutes.setTextColor(getResources().getColor(R.color.white));
            editTextSeconds.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void clearTextFields() {
        editTextHours.setText(getString(R.string.value_00));
        editTextMinutes.setText(getString(R.string.value_00));
        editTextSeconds.setText(getString(R.string.value_00));
    }

    //compute the total number of milliseconds in the future until the countdown is done
    private void getMillisInFuture() {
        millisInFuture = 0L;
        if (!editTextSeconds.getText().toString().isEmpty()) {
            millisInFuture += TimeUtils.getInstance().convertToMillis(0, 0, Integer.parseInt(editTextSeconds.getText().toString()));
        }
        if (!editTextMinutes.getText().toString().isEmpty()) {
            millisInFuture += TimeUtils.getInstance().convertToMillis(0, Integer.parseInt(editTextMinutes.getText().toString()), 0);
        }
        if (!editTextHours.getText().toString().isEmpty()) {
            millisInFuture += TimeUtils.getInstance().convertToMillis(Integer.parseInt(editTextHours.getText().toString()), 0, 0);
        }
    }

    private void getTimeOutputFromTextFields() {
        if (!editTextHours.getText().toString().equals("")) {
            int hours = Integer.parseInt(editTextHours.getText().toString());
            editTextHours.setText(String.valueOf(hours));
        }
        if (!editTextMinutes.getText().toString().equals("")) {
            int minutes = Integer.parseInt(editTextMinutes.getText().toString());
            editTextMinutes.setText(String.valueOf(minutes));
        }
        if (!editTextSeconds.getText().toString().equals("")) {
            int seconds = Integer.parseInt(editTextSeconds.getText().toString());
            editTextSeconds.setText(String.valueOf(seconds));
        }
    }

    //converts from total number of millis to hours-minutes-seconds and update text fields
    private void updateTextFieldsToHHMMSS() {
        //get total seconds remaining
        int countdownTime = (int) (millisInFuture / 1000);

        //calculate hours-minutes-seconds
        int hours = (countdownTime / 3600);
        int minutesRemaining = countdownTime % 3600;
        int minutes = (minutesRemaining / 60);
        int seconds = minutesRemaining % 60;

        //update fields
        editTextHours.setText(String.format("%s%s", handleZeroPreffix(hours), String.valueOf(hours)));
        editTextMinutes.setText(String.format("%s%s", handleZeroPreffix(minutes), String.valueOf(minutes)));
        editTextSeconds.setText(String.format("%s%s", handleZeroPreffix(seconds), String.valueOf(seconds)));
    }

    //checks if an integer is less than ten and returns a zero string
    private String handleZeroPreffix(int value) {
        if (value < 10) {
            return getString(R.string.value_0);
        }
        return "";
    }
}