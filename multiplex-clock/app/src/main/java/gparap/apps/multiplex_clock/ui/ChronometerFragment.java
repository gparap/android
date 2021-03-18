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
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import gparap.apps.multiplex_clock.R;
import gparap.apps.multiplex_clock.utils.PreferencesManager;

public class ChronometerFragment extends Fragment { //TODO: save state for progress
    private Chronometer chronometer;
    private ProgressBar progressBar;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonReset;
    private long stoppedTime;
    private boolean isRunning;
    private static long startTime = -1L;
    private volatile int progress;
    private Thread progressThread;
    private Handler progressHandler;
    private static int PROGRESS_MIN = 0;
    private static int PROGRESS_MAX = 60;

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
        initProgress();
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
            } else {
                resumeTimer();
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
            startProgress();
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
        stopProgress();
    }

    private void resetTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        stoppedTime = 0L;
        chronometer.stop();
        isRunning = false;
        PreferencesManager.getInstance().saveLong(getActivity(), "stoppedTime", stoppedTime);
        resetProgress();
    }

    private void initProgress() {
        progress = PROGRESS_MIN;
        progressBar.setMax(PROGRESS_MAX);
        progressBar.setProgress(progress);
    }

    private void startProgress() {
        progressBar.setVisibility(View.VISIBLE);

        //handle progress
        progressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressThread != null && progress < PROGRESS_MAX) {
                    progress = getProgress();

                    //update progress bar
                    progressHandler = new Handler(Looper.getMainLooper());
                    progressHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                }
            }
        });
        progressThread.start();
    }

    private void stopProgress() {
        progressHandler.removeCallbacks(progressThread);
        stopProgressThread();
        progress = reCalculateProgress();
        progressBar.setProgress(progress);
    }

    private void resetProgress() {
        initProgress();
        progressHandler.removeCallbacks(progressThread);
        stopProgressThread();
    }

    private synchronized void stopProgressThread() {
        try {
            progressThread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (progressThread != null) {
                progressThread = null;
            }
        }
    }

    private int getProgress() {
        progress++;

        //cycle progress every minute
        if (progress < PROGRESS_MAX) {
            stepProgress();
        } else {
            stepProgress();
            progress = PROGRESS_MIN;
            progressBar.setProgress(progress);
        }
        return progress;
    }

    private void stepProgress() {
        //step progress every second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Re-Calculates progress based on seconds left every time the chronometer is stopped
    //  for syncing the chronometer with the progress bar
    private int reCalculateProgress() {
        String timer = chronometer.getText().toString();
        String seconds = timer.substring(timer.lastIndexOf(':') + 1, timer.length());
        return Integer.parseInt(seconds);
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