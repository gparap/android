package gparap.apps.calendar;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

/**
 * Created by gparap on 2020-10-08.
 */
public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    Button buttonAddEvent,
            buttonClear;
    EditText editTextAddEventName,
            editTextAddEventDetails;
    DatabaseHelper dbHelper;
    String eventDate,
            eventName,
            eventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        calendarView = findViewById(R.id.calendarView);
        buttonAddEvent = findViewById(R.id.buttonAddEvent);
        buttonClear = findViewById(R.id.buttonRemoveEvent);
        editTextAddEventName = findViewById(R.id.editTextEventName);
        editTextAddEventDetails = findViewById(R.id.editTextEventDetails);

        //create database table
        dbHelper = new DatabaseHelper(this, "calendarDB", null, 1);

        //notify when date change
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            //get date of event
            eventDate = String.format("%s%s%s", year, month, dayOfMonth);

            //display event with details
            HashMap<String, String> hashMap = dbHelper.dbSelect(eventDate);
            editTextAddEventName.setText(hashMap.get("event_name"));
            editTextAddEventDetails.setText(hashMap.get("event_details"));
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        //close database
        dbHelper.close();
    }

    /**
     * Clears text from edit fields.
     *
     * @param view buttonClearText
     */
    public void onClickClearText(View view) {
        editTextAddEventName.setText("");
        editTextAddEventDetails.setText("");
    }

    /**
     * Adds a new event and its details.
     *
     * @param view buttonAddEvent
     */
    public void onClickAddEvent(View view) {
        //helpers
        eventName = editTextAddEventName.getText().toString();
        eventDetails = editTextAddEventDetails.getText().toString();

        //add event to database
        if (eventName.isEmpty()) {
            Toast.makeText(this, "Please add an event name.", Toast.LENGTH_SHORT).show();
        } else {
            //insert event values to database
            dbHelper.dbInsert(getContentValues(), "event_date");
        }
    }

    /**
     * Removes an event and its details.
     *
     * @param view buttonRemoveEvent
     */
    public void onClickRemoveEvent(View view) {
        dbHelper.dbDelete("event_name", eventName);

        //update edit fields
        editTextAddEventName.setText("");
        editTextAddEventDetails.setText("");
    }

    /**
     * Gets event content values.
     *
     * @return content values
     */
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_date", eventDate);
        contentValues.put("event_name", eventName);
        contentValues.put("event_details", eventDetails);
        return contentValues;
    }
}