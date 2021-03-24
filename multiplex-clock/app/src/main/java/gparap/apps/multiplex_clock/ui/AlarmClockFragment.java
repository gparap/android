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

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;

public class AlarmClockFragment extends Fragment {
    TextView textViewTimer;
    DateFormat dateFormat;  //helper to format system time to default locale
    TimePicker timePicker;
    Button buttonSetAlarm;
    TextView textViewAlarm;
    boolean isAlarmSet;     //helper to handle cases where the alarm is not set correctly

    public AlarmClockFragment() {
    }

    public static AlarmClockFragment newInstance() {
        AlarmClockFragment fragment = new AlarmClockFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_clock, container, false);
        getFragmentWidgets(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        displayCurrentTime();
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: setAlarm
            }
        });
    }

    private void getFragmentWidgets(View view) {
        textViewTimer = view.findViewById(R.id.textViewTimeNow);
        timePicker = view.findViewById(R.id.timePicker);
        buttonSetAlarm = view.findViewById(R.id.buttonSetAlarm);
        textViewAlarm = view.findViewById(R.id.textViewAlarm);
        isAlarmSet = false;

        //format system time to default locale
        dateFormat = DateFormat.getTimeInstance();
    }

    //simulate a real clock
    private void displayCurrentTime() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();

                //keep running thread to simulate a real clock
                while (!isInterrupted()) {
                    try {
                        //1 second intervals
                        sleep(1000);

                        //update current time
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                textViewTimer.setText(dateFormat.format(System.currentTimeMillis()));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}