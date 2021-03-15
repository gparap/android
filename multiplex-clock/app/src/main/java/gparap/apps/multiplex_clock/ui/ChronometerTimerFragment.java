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

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import gparap.apps.multiplex_clock.R;

public class ChronometerTimerFragment extends Fragment {
    private Chronometer chronometer;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonReset;
    private long stoppedTime;
    private boolean isRunning;
    private boolean hasStarted;

    public ChronometerTimerFragment() {
    }

    public static ChronometerTimerFragment newInstance() {
        ChronometerTimerFragment fragment = new ChronometerTimerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container, false);
        getFragmentWidgets(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        addOnClickListenersToFragmentWidgets();
    }

    @Override
    public void onPause() {
        super.onPause();
        hasStarted = isRunning;
        stopTimer();
    }

    @Override
    public void onResume() {
        //restore state
        super.onResume();
        if (hasStarted){
            startTimer();
        }
    }

    private void initTimer() {
        stoppedTime = 0L;
        isRunning = false;
        hasStarted = false;
    }

    private void startTimer() {
        if (!isRunning) {
            //start clock from now on (not app boot)
            chronometer.setBase(SystemClock.elapsedRealtime() - stoppedTime);
            chronometer.start();
            isRunning = true;
        }
    }

    private void stopTimer() {
        if (isRunning) {
            //hold time that passed since the start of the counting
            stoppedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isRunning = false;
        }
    }

    private void resetTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        stoppedTime = 0L;
        chronometer.stop();
        isRunning = false;
        hasStarted = false;
    }

    private void addOnClickListenersToFragmentWidgets() {
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void getFragmentWidgets(View view) {
        chronometer = view.findViewById(R.id.chronometer);
        buttonStart = view.findViewById(R.id.buttonStart);
        buttonStop = view.findViewById(R.id.buttonStop);
        buttonReset = view.findViewById(R.id.buttonReset);
    }
}