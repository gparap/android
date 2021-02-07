package gparap.apps.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Created by gparap on 2020-10-21.
 */
public class MainActivity extends AppCompatActivity {
    FloatingActionButton buttonNoteAdd;
    RecyclerView recyclerViewNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        buttonNoteAdd = findViewById(R.id.buttonNoteAdd);
        recyclerViewNotebook = findViewById(R.id.recyclerViewNotebook);

        //add a new note
        buttonNoteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goto new node activity leaving no history trace
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);   //leave no history trace
                startActivity(intent);
                finish();
            }
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