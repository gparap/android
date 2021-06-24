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
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment

class DatePickerDialogFragment : DialogFragment() {
    private var listener: OnDateSetListener? = null
    private var year = 0
    private var month = 0
    private var dayOfMonth = 0

    fun setListener(listener: OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get fragment arguments
        val fragmentArgs = arguments
        if (fragmentArgs != null) {
            year = fragmentArgs.getInt("year")
            month = fragmentArgs.getInt("month")
            dayOfMonth = fragmentArgs.getInt("dayOfMonth")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(requireContext(), listener, year, month, dayOfMonth)
    }

    companion object {
        //initialize date picker dialog fragment
        fun getInstance(year: Int, month: Int, dayOfMonth: Int): DatePickerDialogFragment {
            val datePickerDialogFragment = DatePickerDialogFragment()

            //create a bundle with the arguments of this fragment
            val bundle = Bundle()
            bundle.putInt("year", year)
            bundle.putInt("month", month)
            bundle.putInt("dayOfMonth", dayOfMonth)
            datePickerDialogFragment.arguments = bundle

            return datePickerDialogFragment
        }
    }
}