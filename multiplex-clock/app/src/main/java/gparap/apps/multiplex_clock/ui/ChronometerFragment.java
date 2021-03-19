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
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import gparap.apps.multiplex_clock.R;
import gparap.apps.multiplex_clock.CircularProgress;
import gparap.apps.multiplex_clock.utils.PreferencesManager;

public class ChronometerFragment extends Fragment {
    private Chronometer chronometer;
    private ProgressBar progressBar;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonReset;
    private long stoppedTime;
    private boolean isRunning;
    private static long startTime = -1L;
    CircularProgress circularProgress;

    public ChronometerFragment() {
    }

    public static ChronometerFragment newInstance() {
        ChronometerFragment fragment = new ChronometerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        //create a circular progree object
        circularProgress = new CircularProgress();
        circularProgress.setProgressBar(progressBar);
        circularProgress.setupProgress();
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferencesManager.getInstance().saveBoolean(getActivity(), "isRunning", isRunning);
        PreferencesManager.getInstance().saveLong(getActivity(), "runningTime", chronometer.getBase());
    }

    @Override
    public void onResume() {
        super.onResume();

        if (startTime == -1) {
            startTime = SystemClock.elapsedRealtime();
            chronometer.setBase(SystemClock.elapsedRealtime());
        } else {
            isRunning = PreferencesManager.getInstance().getBoolean(getActivity(), "isRunning", false);

            if (!isRunning) {
                stoppedTime = PreferencesManager.getInstance().getLong(getActivity(), "stoppedTime", 0L);
                chronometer.setBase(SystemClock.elapsedRealtime() - stoppedTime);
                circularProgress.resumeProgress(chronometer.getText().toString());
            } else {
                resumeTimer();
                circularProgress.resumeProgress(chronometer.getText().toString());

                // !!! do NOT remove !!!
                // (thread becomes null when UI no longer exists)
                if (circularProgress.getProgressThread() == null) {
                    circularProgress.startProgress();
                }
            }
        }
    }

    private void resumeTimer() {
        //start clock from where it was before losing focus
        long runningTime = PreferencesManager.getInstance().getLong(getActivity(), "runningTime", 0L);
        chronometer.setBase(runningTime);
        chronometer.start();
        isRunning = true;
    }

    private void startTimer() {
        if (!isRunning) {
            //start clock from now on (not app boot)
            chronometer.setBase(SystemClock.elapsedRealtime() - stoppedTime);
            chronometer.start();
            isRunning = true;
            circularProgress.startProgress();
        }
    }

    private void stopTimer() {
        if (isRunning) {
            //hold time that passed since the start of the counting
            stoppedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isRunning = false;
        }
        PreferencesManager.getInstance().saveLong(getActivity(), "stoppedTime", stoppedTime);
        circularProgress.stopProgress(chronometer.getText().toString());
    }

    private void resetTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        stoppedTime = 0L;
        chronometer.stop();
        isRunning = false;
        PreferencesManager.getInstance().saveLong(getActivity(), "stoppedTime", stoppedTime);
        circularProgress.resetProgress();
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
        progressBar = view.findViewById(R.id.progressBarChronometer);
        buttonStart = view.findViewById(R.id.buttonStart);
        buttonStop = view.findViewById(R.id.buttonStop);
        buttonReset = view.findViewById(R.id.buttonReset);
    }
}