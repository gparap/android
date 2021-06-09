package gparap.apps.todo_list.ui.add_todo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import gparap.apps.todo_list.R
import gparap.apps.todo_list.ui.pickers.DatePickerFragment
import gparap.apps.todo_list.ui.pickers.TimePickerFragment
import gparap.apps.todo_list.utils.Utils

class AddToDoFragment : Fragment() {
    private lateinit var viewModel: AddToDoViewModel
    private lateinit var textViewToDoTimeSet: TextView
    private lateinit var textViewToDoDateSet: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()

        //get existing (or create new) ViewModel associated with the given Fragment
        viewModel = ViewModelProvider(this).get(AddToDoViewModel::class.java)
        // TODO: Use the ViewModel

        //display dialogs to set the time and date for to-do
        setTimeForToDo(view)
        setDateForToDo(view)

        //observe to-do time set
        textViewToDoTimeSet = view.findViewById(R.id.textViewToDoTimeSet)
        viewModel.getToDoTime().observe(viewLifecycleOwner, {
            textViewToDoTimeSet.text = it
        })

        //observe to-do date set
        textViewToDoDateSet = view.findViewById(R.id.textViewToDoDateSet)
        viewModel.getToDoDate().observe(viewLifecycleOwner, {
            textViewToDoDateSet.text = it
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setDateForToDo(view: View) {
        //callback for handling the date set for a to-do
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            @Suppress("NAME_SHADOWING") val month = month + 1
            viewModel.setToDoDate("$day/$month/$year")
        }

        //display date picker and set date for the to-do
        val buttonShowDatePickerDialog = view.findViewById<Button>(R.id.buttonShowDatePickerDialog)
        buttonShowDatePickerDialog.setOnClickListener {
            val datePickerDialogFragment: DialogFragment = DatePickerFragment(dateSetListener)
            datePickerDialogFragment.show(activity?.supportFragmentManager!!, "DatePickerDialog")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTimeForToDo(view: View) {
        //callback for handling the time set for a to-do
        val timeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
            viewModel.setToDoTime(
                Utils.fillInZeroInFront(hourOfDay).plus(":").plus(Utils.fillInZeroInFront(minute))
            )
        }

        //display time picker and set time for the to-do
        val buttonShowTimePickerDialog = view.findViewById<Button>(R.id.buttonShowTimePickerDialog)
        buttonShowTimePickerDialog.setOnClickListener {
            val timePickerDialogFragment: DialogFragment = TimePickerFragment(timeSetListener)
            timePickerDialogFragment.show(activity?.supportFragmentManager!!, "TimePickerDialog")
        }
    }

    private fun setupActionBar() {
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.toolbar)
        if (toolbar != null) {
            toolbar.title = resources.getString(R.string.fragment_add_todo)

            //TODO: handle light/dark themes (later in polishing)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite, null))
            } else {
                toolbar.setTitleTextColor(Color.WHITE)
            }
        }
    }
}