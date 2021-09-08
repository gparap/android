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
package gparap.apps.alarm_clock.utils;

import android.widget.TimePicker;

public class Utils {
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    private Utils() {
    }

    public long convertHoursToMillis(int hours) {
        return (long) hours * 60 * 60 * 1000;
    }

    public long convertMinutesToMillis(int minutes) {
        return (long) minutes * 60 * 1000;
    }

    /**
     * Returns the time picked in a 12-hour format.
     *
     * @param timePicker the time picker
     * @return time picked
     */
    public String getAlarmTime(TimePicker timePicker) {
        boolean isPM = false;
        int hour;
        int minute;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }
        StringBuilder stringBuilder = new StringBuilder();
        //hours
        if (hour > 12) {
            isPM = true;
            hour %= 12;

        }
        stringBuilder.append(hour).append(":");

        //minutes
        if (minute < 10) {
            stringBuilder.append("0");

        }
        stringBuilder.append(minute);

        //AM or PM
        if (isPM) {
            stringBuilder.append(" PM");
        } else {
            stringBuilder.append(" AM");
        }

        //return time picked
        return String.valueOf(stringBuilder);
    }
}
