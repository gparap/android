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
package gparap.apps.multiplex_clock.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

@SuppressWarnings("FieldCanBeLocal")
public class CircularProgress {
    private ProgressBar progressBar;
    private int progress;
    private Thread progressThread;
    private Handler progressHandler;
    private static int PROGRESS_MIN = 0;
    private static short PROGRESS_SLEEP_INTERVAL = 1000;
    private int progressMax;

    public CircularProgress() {
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setProgressMax(int progressMax) { this.progressMax = progressMax; }

    public Thread getProgressThread() {
        return progressThread;
    }

    public int getProgress() {
        progress++;
        //cycle progress every minute
        if (progress < progressMax) {
            delayProgress(PROGRESS_SLEEP_INTERVAL);
        } else {
            delayProgress(PROGRESS_SLEEP_INTERVAL);
            progress = PROGRESS_MIN;
            progressBar.setProgress(progress);

            //for extra sync
            delayProgress(PROGRESS_SLEEP_INTERVAL);
        }
        return progress;
    }

    public void setupProgress() {
        progress = PROGRESS_MIN;
        progressBar.setMax(progressMax);
        progressBar.setProgress(progress);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setupProgress(String timer) {
        progress = calculateProgress(timer);
        progressBar.setMax(progressMax);
        progressBar.setProgress(progress);
        if (progress == PROGRESS_MIN) {
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void resumeProgress(String timer) {
        progress = calculateProgress(timer);
        progressBar.setProgress(progress);
        if (progress == PROGRESS_MIN) {
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);

        //handle progress
        progressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressThread != null && progress < progressMax) {
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

    public void stopProgress(String timer) {
        if (progressHandler != null) {
            progressHandler.removeCallbacks(progressThread);
        }
        stopProgressThread();
        progress = calculateProgress(timer);
        progressBar.setProgress(progress);
    }

    public void resetProgress() {
        if (progressHandler != null) {
            progressHandler.removeCallbacks(progressThread);
        }
        stopProgressThread();
        setupProgress();
    }

    //Causes a thread to sleep for the given interval of time (in millis)
    //  to sync progress with a timer
    private void delayProgress(short interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    //Calculates progress based on seconds left every time the chronometer is stopped
    //  for syncing the chronometer with the progress bar
    private int calculateProgress(String timer) {
        String progress = timer.substring(timer.lastIndexOf(':') + 1, timer.length());
        return Integer.parseInt(progress);
    }
}
