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

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton buttonNoteAdd;
    RecyclerView recyclerViewNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        buttonNoteAdd = findViewById(R.id.buttonNoteAdd);
        recyclerViewNotebook = findViewById(R.id.recyclerViewNotebook);

        //add a new note
        buttonNoteAdd.setOnClickListener(v -> {
            //goto new node activity leaving no history trace
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);   //leave no history trace
            startActivity(intent);
            finish();
        });

        //setup adapter (get all notes from database)
        NoteAdapter noteAdapter = new NoteAdapter(this);
        DatabaseManager databaseManager = new DatabaseManager(this);
        noteAdapter.setNotes(databaseManager.selectAllNotes());

        //setup recycler view with adapter
        recyclerViewNotebook.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotebook.setAdapter(noteAdapter);
    }
}