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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.fragment.app.Fragment;

import gparap.apps.multiplex_clock.R;

public class ChronometerFragment extends Fragment {
    private Chronometer chronometer;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonReset;
    private long stoppedTime;
    private boolean isRunning;
    private static long startTime = -1L;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


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
    }

    @Override
    public void onPause() {
        super.onPause();
        saveRunningTimePrefs();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (startTime == -1) {
            startTime = SystemClock.elapsedRealtime();
            chronometer.setBase(SystemClock.elapsedRealtime());
        }else{
            SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            isRunning = getBooleanPrefs("isRunning", false);

            if (!isRunning) {
                stoppedTime = getLongPrefs("stoppedTime", 0L);
                chronometer.setBase(SystemClock.elapsedRealtime() - stoppedTime);
            }else{
                resumeTimer();
            }
        }
    }

    private void resumeTimer() {
        //start clock from where it was before losing focus
        long runningTime = getLongPrefs("runningTime", 0L);
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
        }
    }

    private void stopTimer() {
        if (isRunning) {
            //hold time that passed since the start of the counting
            stoppedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isRunning = false;
        }
        saveStoppedTimePrefs();
    }

    private void resetTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        stoppedTime = 0L;
        chronometer.stop();
        isRunning = false;
        saveStoppedTimePrefs();
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

    private void saveStoppedTimePrefs() {
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putLong("stoppedTime", stoppedTime);
        editor.apply();
    }

    private void saveRunningTimePrefs() {
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("isRunning", isRunning);
        editor.putLong("runningTime", chronometer.getBase());
        editor.apply();
    }

    private Boolean getBooleanPrefs(String key, Boolean defaultValue) {
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    private Long getLongPrefs(String key, Long defaultValue) {
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return preferences.getLong(key, defaultValue);
    }
}