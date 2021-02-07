package gparap.cloud.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.transition.Explode;
import android.transition.Transition;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

@SuppressWarnings("Convert2Lambda")
public class NoteActivity extends AppCompatActivity {
    EditText editTextNoteTitle,
            editTextNoteDetails;
    DatabaseManager databaseManager;
    ProgressBar progressBarNoteSave;
    String noteId;              //in case of an update this is the key
    boolean isNewNote = false;  //flag for if a note (title) is new or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_note);

        //init toolbar
        Toolbar toolbar = findViewById(R.id.toolbarNote);
        setSupportActionBar(toolbar);

        //enable toolbar home button
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.default_title_activity_note);

        //init manager for database operations
        databaseManager = new DatabaseManager();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        editTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        editTextNoteDetails = findViewById(R.id.editTextNoteDetails);
        progressBarNoteSave = findViewById(R.id.progressBarNoteSave);

        //transition
        Transition transition = new Explode();
        transition.setDuration(getResources().getInteger(R.integer.transition_duration_default));
        getWindow().setEnterTransition(transition);

        //get note from intent and fill fields
        noteId = getIntent().getStringExtra("note_id");
        editTextNoteTitle.setText(getIntent().getStringExtra("note_title"));
        editTextNoteDetails.setText(getIntent().getStringExtra("note_details"));

        //change toolbar text when typing note title
        editTextNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getSupportActionBar().setTitle(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //save note to database
        FloatingActionButton fabSave = findViewById(R.id.fabSave);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNewNote = true;   //default

                //check if input not empty
                if (editTextNoteTitle.getText().toString().isEmpty() || editTextNoteDetails.getText().toString().isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Please add a note.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //show progress bar
                progressBarNoteSave.setVisibility(View.VISIBLE);

                //create new note
                Note note = new Note();
                note.setId(noteId);
                note.setTitle(editTextNoteTitle.getText().toString());
                note.setDetails(editTextNoteDetails.getText().toString());

                //check if note title exists in database and update or create a new one
                Task<QuerySnapshot> queryTask = databaseManager.getNoteTitle(note.getTitle(), databaseManager.getFirebaseUser().getUid());
                queryTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //check if same note title exists
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                //same id (should update)
                                if (document.getId().equals(note.getId())) {
                                    isNewNote = false;
                                }
                                //different id (trying to update different existing note)
                                else {
                                    Toast.makeText(NoteActivity.this, "Title already exists.", Toast.LENGTH_SHORT).show();

                                    //hide progress bar
                                    progressBarNoteSave.setVisibility(View.INVISIBLE);
                                    return;
                                }
                            }

                            //save to database
                            Task<Void> saveTask = databaseManager.saveNote(note, isNewNote, databaseManager.getFirebaseUser().getUid());
                            saveTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(NoteActivity.this, "Note saved successfully.", Toast.LENGTH_SHORT).show();
                                    gotoHome();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(NoteActivity.this, "Sorry, cannot save note.", Toast.LENGTH_SHORT).show();

                                    //hide progress bar
                                    progressBarNoteSave.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /**
     * Goes back to main activity.
     */
    public void gotoHome() {
        startActivity(new Intent(this, MainActivity.class));
        finishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        gotoHome();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        gotoHome();
        super.onBackPressed();
    }
}