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

import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {
    private static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public int getCurrentHour() {
        int hour;
        int minuteNow;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            java.time.LocalTime localTime = java.time.LocalTime.now();
            hour = localTime.getHour();
        } else {
            java.util.Calendar calendar = new GregorianCalendar();
            hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        }
        return hour;
    }

    public int getCurrentMinute() {
        int minute;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            java.time.LocalTime localTime = java.time.LocalTime.now();
            minute = localTime.getMinute();
        } else {
            java.util.Calendar calendar = new GregorianCalendar();
            minute = calendar.get(Calendar.MINUTE);
        }
        return minute;
    }

    public int getPickedHour(TimePicker timePicker) {
        int hour;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
        }else{
            hour = timePicker.getCurrentHour();
        }
        return hour;
    }

    public int getPickedMinute(TimePicker timePicker) {
        int minute;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            minute = timePicker.getMinute();
        }else{
            minute = timePicker.getCurrentMinute();
        }
        return minute;
    }

    public long convertToMillis(int hours, int minutes, int seconds) {
        return (hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000);
    }
}
