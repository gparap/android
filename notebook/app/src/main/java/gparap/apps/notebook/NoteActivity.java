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
package gparap.apps.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class NoteActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    EditText editTextNoteTitle, editTextNoteDetails;
    long noteID = -1;   //helper to choose if to update existing note or insert a new one

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get toolbar as ActionBar
        toolbar = findViewById(R.id.toolbar_note_add);
        setSupportActionBar(toolbar);

        //enable "Home" button to goto main activity
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get widgets
        editTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        editTextNoteDetails = findViewById(R.id.editTextNoteDetails);

        //get note data from intent (if any) from RecycleView's selected item
        Intent intent = getIntent();
        noteID = intent.getLongExtra("id", -1);
        editTextNoteTitle.setText(intent.getStringExtra("title"));
        editTextNoteDetails.setText(intent.getStringExtra("details"));

        //set toolbar default title
        if (noteID == -1)
            toolbar.setTitle("Add New Note");
        else {
            toolbar.setTitle(editTextNoteTitle.getText());
        }

        //update toolbar with note's title on the fly
        editTextNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                toolbar.setTitle(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu options to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //goto main activity
            case android.R.id.home:     //home button
            case R.id.menuOptionCancel: //cancel button
                gotoHome();
                break;

            //save note
            case R.id.menuOptionSave:
                if (saveNote(noteID)) {
                    gotoHome();
                }
                break;

            //clear fields
            case R.id.menuOptionClear:
                editTextNoteTitle.setText("");
                editTextNoteDetails.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickButtonSave(View view) {
        if (saveNote(noteID)) {
            gotoHome();
        }
    }

    /**
     * Saves a note object to database.
     *
     * @param noteID note's id
     * @return boolean whether there was an actual insert or update
     */
    private boolean saveNote(long noteID) {
        //check if note is empty
        if (editTextNoteTitle.getText().toString().isEmpty() || editTextNoteDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter note.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //create database manager
            DatabaseManager databaseManager = new DatabaseManager(this);

            //create a new note object
            Note note = new Note();
            note.setTitle(editTextNoteTitle.getText().toString());
            note.setDetails(editTextNoteDetails.getText().toString());

            //insert a new node into database
            if (noteID == -1) {
                if (databaseManager.insert(note)) {
                    Toast.makeText(this, "Note created successfully.", Toast.LENGTH_SHORT).show();
                }
            }
            //update existing note in database
            else {
                //update current note based on id
                note.setId(noteID);

                //update note in database
                if (databaseManager.update(note)) {
                    Toast.makeText(this, "Note updated successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return true;
    }

    /**
     * Goes back to Main Activity.
     */
    private void gotoHome() {
        Intent intent = new Intent(NoteActivity.this, MainActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);   //leave no history trace
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoHome();
    }
}