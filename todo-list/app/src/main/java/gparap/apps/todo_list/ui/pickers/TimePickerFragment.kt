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
package gparap.apps.todo_list.ui.pickers

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment

class TimePickerDialogFragment : DialogFragment() {
    private var listener: OnTimeSetListener? = null
    private var hourOfDay = 0
    private var minute = 0
    private var is24Hours = false

    fun setListener(listener: OnTimeSetListener?) {
        this.listener = listener
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get fragment arguments
        val fragmentArgs = arguments
        if (fragmentArgs != null) {
            hourOfDay = fragmentArgs.getInt("hourOfDay")
            minute = fragmentArgs.getInt("minute")
            is24Hours = fragmentArgs.getBoolean("is24hours")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(context, listener, hourOfDay, minute, is24Hours)
    }

    companion object {
        //initialize time picker dialog fragment
        fun getInstance(hourOfDay: Int, minute: Int, is24Hours: Boolean): TimePickerDialogFragment {
            val timePickerDialogFragment = TimePickerDialogFragment()

            //create a bundle with the arguments of this fragment
            val bundle = Bundle()
            bundle.putInt("hourOfDay", hourOfDay)
            bundle.putInt("minute", minute)
            bundle.putBoolean("is24hours", is24Hours)
            timePickerDialogFragment.arguments = bundle

            return timePickerDialogFragment
        }
    }
}