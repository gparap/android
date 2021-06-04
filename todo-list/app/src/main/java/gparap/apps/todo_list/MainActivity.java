package gparap.apps.todo_list;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import gparap.apps.todo_list.adapter.ToDoAdapter;
import gparap.apps.todo_list.data.DatabaseManager;
import gparap.apps.todo_list.model.ToDoModel;
import gparap.apps.todo_list.utils.TouchManager;

/**
 * Created by gparap on 2020-10-16.
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewToDo;
    ToDoAdapter adapter;
    String todoTime,
            todoDate;
    Dialog dialogTodo;  //Dialog for adding a new to_do
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //create sqllite database
        databaseManager = new DatabaseManager(this, null);

        //setup recycler view
        recyclerViewToDo = findViewById(R.id.recyclerViewTodo);
        recyclerViewToDo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDoAdapter(databaseManager);
        recyclerViewToDo.setAdapter(adapter);

        //add swipe behaviour to recycler view
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TouchManager(0, ItemTouchHelper.LEFT, adapter, MainActivity.this));
        itemTouchHelper.attachToRecyclerView(recyclerViewToDo);

        //get all todos from database
        adapter.setTodosList(databaseManager.selectAllToDos());
    }

    @Override
    protected void onStop() {
        super.onStop();

        //close open database
        databaseManager.close();
    }

    /**
     * Handles "Add", "Cancel" and "Clear" button of To_Do dialog.
     *
     * @param view button clicked
     */
    @SuppressLint("NonConstantResourceId")
    public void onClickToDo(View view) {
        switch (view.getId()) {
            //add new To_Do
            case R.id.buttonAdd:
                //check if empty
                if (((TextView) dialogTodo.findViewById(R.id.editTextTodo)).getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please add a TODO.", Toast.LENGTH_SHORT).show();
                } else {
                    //create a new to-do
                    ToDoModel todo = new ToDoModel();
                    if (todoTime != null) {
                        todo.setTime(todoTime);
                    }
                    if (todoDate != null) {
                        todo.setDate(todoDate);
                    }
                    todo.setTodo(((TextView) dialogTodo.findViewById(R.id.editTextTodo)).getText().toString());
                    todo.setDone(false);

                    //insert to_do to database
                    long rowId = databaseManager.insertTodo(todo);
                    if (rowId != -1) {
                        todo.setId((int) rowId);
                        adapter.addToDo(todo);
                    }

                    //close dialog
                    clearDateTimeValues();
                    dialogTodo.dismiss();
                }
                break;

            //close dialog
            case R.id.buttonCancel:
                clearDateTimeValues();
                dialogTodo.dismiss();
                break;

            //clear all text from views
            case R.id.buttonClear:
                ((TextView) dialogTodo.findViewById(R.id.textViewPickTodoTime)).setText("");
                ((TextView) dialogTodo.findViewById(R.id.textViewPickTodoDate)).setText("");
                ((TextView) dialogTodo.findViewById(R.id.editTextTodo)).setText("");
                clearDateTimeValues();
                break;
        }
    }

    /**
     * Opens a dialog for the user to add a new To_Do
     *
     * @param view buttonAddNewTODO
     */
    public void onClickAddNewToDo(View view) {
        dialogTodo = new Dialog(this);
        dialogTodo.setContentView(R.layout.todo_view);
        dialogTodo.show();

        //set To_Do time and date
        handleDialogPicker(dialogTodo, R.id.textViewPickTodoTime, R.layout.time_picker_view);
        handleDialogPicker(dialogTodo, R.id.textViewPickTodoDate, R.layout.date_picker_view);
    }

    /**
     * Displays a picker dialog and sets the value picked.
     *
     * @param parentDialog parent dialog of picker dialog
     * @param pickerId     view that calls picker dialog
     * @param layoutId     picker dialog's layout
     */
    private void handleDialogPicker(final Dialog parentDialog, final int pickerId, final int layoutId) {
        TextView textView = parentDialog.findViewById(pickerId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show picker dialog view
                final Dialog dialogPicker = new Dialog(MainActivity.this);
                dialogPicker.setContentView(layoutId);
                dialogPicker.show();

                Button buttonSetPicker = dialogPicker.findViewById(R.id.buttonSetPicker);
                buttonSetPicker.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public void onClick(View v) {
                        switch (pickerId) {
                            //pick time
                            case R.id.textViewPickTodoTime:
                                final TimePicker timePicker = dialogPicker.findViewById(R.id.timePickerTodo);
                                timePicker.setIs24HourView(true);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    todoTime = "By " + timePicker.getHour() + ":" + timePicker.getMinute();
                                }else{
                                    todoTime = "By " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
                                }
                                //fix zeros
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (timePicker.getMinute() < 10) {
                                        todoTime = "By " + timePicker.getHour() + ":0" + timePicker.getMinute();
                                    }
                                }else{
                                    if (timePicker.getCurrentMinute() < 10) {
                                        todoTime = "By " + timePicker.getCurrentHour() + ":0" + timePicker.getCurrentMinute();
                                    }
                                }
                                TextView textViewPickTodoTime = parentDialog.findViewById(R.id.textViewPickTodoTime);
                                textViewPickTodoTime.setText(todoTime);
                                break;

                            //pick date
                            case R.id.textViewPickTodoDate:
                                final DatePicker datePicker = dialogPicker.findViewById(R.id.datePickerTodo);
                                todoDate = "On " + datePicker.getDayOfMonth() + "/" + datePicker.getMonth();
                                TextView textViewPickTodoDate = parentDialog.findViewById(R.id.textViewPickTodoDate);
                                textViewPickTodoDate.setText(todoDate);
                                break;
                        }
                        dialogPicker.dismiss();
                    }
                });
            }
        });
    }

    /**
     * Sets global values for time and date to null.
     */
    private void clearDateTimeValues() {
        todoTime = null;
        todoDate = null;
    }
}