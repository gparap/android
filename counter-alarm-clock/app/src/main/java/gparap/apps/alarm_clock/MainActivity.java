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
package gparap.apps.alarm_clock;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import gparap.apps.alarm_clock.utils.Utils;

public class MainActivity extends AppCompatActivity {
    TextView textViewTimer;
    DateFormat dateFormat;  //helper to format system time to default locale
    TimePicker timePicker;
    Button buttonSetAlarm;
    TextView textViewAlarm;
    boolean isAlarmSet;     //helper to handle cases where the alarm is not set correctly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        displayCurrentTime();
    }

    private void init() {
        textViewTimer = findViewById(R.id.textViewTimer);
        timePicker = findViewById(R.id.timePicker);
        buttonSetAlarm = findViewById(R.id.buttonSetAlarm);
        textViewAlarm = findViewById(R.id.textViewAlarm);
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

                //keep running UI thread to simulate a real clock
                while (!isInterrupted()) {
                    try {
                        //1 second intervals
                        sleep(1000);

                        //run on the UI thread
                        runOnUiThread(() -> {
                            //update current time
                            textViewTimer.setText(dateFormat.format(System.currentTimeMillis()));
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    //set alarm
    public void OnClickSetAlarm(View view) {
        //display alarm in field
        textViewAlarm.setText(Utils.getInstance().getAlarmTime(timePicker));

        //register an intent to be broadcast by the alarm receiver
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        //get the time in milliseconds that the alarm should go off
        long triggerAtMillis = getTriggerAtMillis();

        //schedule an alarm if set correctly
        if (isAlarmSet) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + triggerAtMillis, pendingIntent);
        }
    }

    //get how many milliseconds is the difference between current time and alarm
    private long getTriggerAtMillis() {
        //get current time hours and minutes
        int hourNow;
        int minuteNow;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            java.time.LocalTime localTime = java.time.LocalTime.now();
            hourNow = localTime.getHour();
            minuteNow = localTime.getMinute();
        } else {
            java.util.Calendar calendar = new GregorianCalendar();
            hourNow = calendar.get(java.util.Calendar.HOUR_OF_DAY);
            minuteNow = calendar.get(Calendar.MINUTE);
        }

        //get alarm time hours and minutes
        int hourAlarm;
        int minuteAlarm;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hourAlarm = timePicker.getHour();
            minuteAlarm = timePicker.getMinute();
        } else {
            hourAlarm = timePicker.getCurrentHour();
            minuteAlarm = timePicker.getCurrentMinute();
        }

        isAlarmSet = true;

        //hour
        int hour = hourAlarm - hourNow;
        //check if hour set is correct
        if (hour < 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_error_setting_hours), Toast.LENGTH_SHORT).show();
            isAlarmSet = false;
        }

        //minute
        int minute = minuteAlarm - minuteNow;
        //check if minute set is correct
        if (hour == 0 && minute < 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_error_setting_minutes), Toast.LENGTH_SHORT).show();
            isAlarmSet = false;
        }

        //convert to milliseconds and return
        return Utils.getInstance().convertHoursToMillis(hour) + Utils.getInstance().convertMinutesToMillis(minute);
    }
}
