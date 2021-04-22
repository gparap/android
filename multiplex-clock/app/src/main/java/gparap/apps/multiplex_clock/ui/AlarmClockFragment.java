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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import java.text.DateFormat;

import gparap.apps.multiplex_clock.services.AlarmReceiver;
import gparap.apps.multiplex_clock.utils.TimeUtils;

@SuppressWarnings("Convert2Lambda")
public class AlarmClockFragment extends Fragment {
    TextView textViewTimer;
    DateFormat dateFormat;  //helper to format system time to default locale
    TimePicker timePicker;
    Button buttonSetAlarm;
    TextView textViewAlarm;
    boolean isAlarmSet;     //helper to handle cases where the alarm is not set correctly

    public AlarmClockFragment() {
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
                setAlarm();
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

    private void setAlarm() {
        displayAlarmTime();

        //get current time
        int hourNow = TimeUtils.getInstance().getCurrentHour();
        int minuteNow = TimeUtils.getInstance().getCurrentMinute();

        //get alarm time
        int hourAlarm = TimeUtils.getInstance().getPickedHour(timePicker);
        int minuteAlarm = TimeUtils.getInstance().getPickedMinute(timePicker);

        //get the time in milliseconds that the alarm should go off
        long triggerAtMillis = 0L;
        if (validateAlarm(hourNow, minuteNow, hourAlarm, minuteAlarm)) {
            int hour = hourAlarm - hourNow;
            int minute = minuteAlarm - minuteNow;
            triggerAtMillis = getTriggerAtMillis(hour, minute);
        }

        //register an intent to be broadcasted by the alarm receiver
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent, 0);

        //schedule an alarm if set correctly
        if (isAlarmSet) {
            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + triggerAtMillis, pendingIntent);
        }
    }

    //display time picked (as alarm) using 12-hour format
    private void displayAlarmTime() {
        boolean isPM = false;
        int hour;
        int minute;
        StringBuilder stringBuilder = new StringBuilder();

        //get time from picker
        hour = TimeUtils.getInstance().getPickedHour(timePicker);
        minute = TimeUtils.getInstance().getPickedMinute(timePicker);

        //handle 12-hour format
        if (hour > 12) {
            isPM = true;
            hour %= 12;
        }
        stringBuilder.append(hour).append(":");

        //handle append minutes with "0" ie 7 -> 07
        if (minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minute);

        //handle AM or PM display
        if (isPM) {
            stringBuilder.append(" PM");
        } else {
            stringBuilder.append(" AM");
        }

        //update alarm display field
        textViewAlarm.setText(stringBuilder);
    }

    //get how many milliseconds is the difference between current time and alarm time
    private long getTriggerAtMillis(int hour, int minute) {
        return TimeUtils.getInstance().convertToMillis(hour, minute, 0);
    }

    //check if alarm is set on future time
    private boolean validateAlarm(int hourNow, int minuteNow, int hourAlarm, int minuteAlarm) {
        isAlarmSet = true;

        //validate hour
        int hour = hourAlarm - hourNow;
        if (hour < 0) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.toast_error_alarm_hours, Toast.LENGTH_SHORT).show();
            isAlarmSet = false;
            return false;
        }

        //validate minute
        int minute = minuteAlarm - minuteNow;
        if (hour == 0 && minute <= 0) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.toast_error_alarm_minutes, Toast.LENGTH_SHORT).show();
            isAlarmSet = false;
            return false;
        }
        return true;
    }
}