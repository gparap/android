package gparap.cloud.todo_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener, To_DoListener {
    final static String LOG = "MainActivity: ";
    Toolbar toolbar;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    To_DoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //init toolbar
        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white, null));
        setSupportActionBar(toolbar);

        //init progress bar
        progressBar = findViewById(R.id.progressBarMain);

        //init button add
        FloatingActionButton fabAdd = findViewById(R.id.fabAddToDo);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDo();
            }
        });

        //init recycler view with adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        Query query = FirebaseFirestore.getInstance().collection("todos")
                .whereEqualTo("userID", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        FirestoreRecyclerOptions<To_Do> options = new FirestoreRecyclerOptions.Builder<To_Do>()
                .setQuery(query, To_Do.class)
                .build();
        adapter = new To_DoAdapter(options, this);
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        //add sign in/out listener
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    /**
     * Opens up a dialog prompt for the user to add a new to-do.
     */
    private void addToDo() {
        //init input for a to_do
        final EditText textInput = new EditText(MainActivity.this);
        textInput.setHint("enter todo here");

        //create dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Add ToDo")
                .setView(textInput)
                .setPositiveButton("Add", null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        //override dialog's positive button's default behaviour
        //do not close if input is empty
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //check if input is empty
                        if (textInput.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this, "To-Do cannot be empty.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //get user
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        assert user != null;

                        //create To_Do object
                        To_Do todoObj = new To_Do();
                        todoObj.setTodo(textInput.getText().toString());
                        todoObj.setIsDone(false);   //default
                        todoObj.setTimestamp(new Timestamp(new Date()));
                        todoObj.setUserID(user.getUid());

                        //add todoObj to database
                        FirebaseFirestore.getInstance().collection("todos")
                                .document()
                                .set(todoObj)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "TODO added successfully.", Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();
                                        } else {
                                            Log.e(LOG, Objects.requireNonNull(task.getException()).toString());
                                            Toast.makeText(MainActivity.this, "Cannot add TODO.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
            }
        });
        alertDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        //remove sign in/out listener
        FirebaseAuth.getInstance().removeAuthStateListener(this);

        //stop listening for database changes
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //sign out existing user
        if (id == R.id.menu_action_logout) {
            progressBar.setVisibility(View.VISIBLE);

            //sign out
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                AuthUI.getInstance().signOut(this);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        //user signed-out or token expired
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(MainActivity.this, "Signing out...", Toast.LENGTH_SHORT).show();

            //goto login activity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.e(LOG, "There was a problem signing out.");
            if (progressBar.isShown()) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void setCheckedChanged(boolean isDone, DocumentSnapshot documentSnapshot) {
        //update the state of a to-do (if it's done or not)
        Task<Void> task = documentSnapshot.getReference().update("isDone", isDone);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Update successful.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Update failed.", Toast.LENGTH_SHORT).show();
                Log.e(LOG, e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void deleteTODO(DocumentSnapshot documentSnapshot) {
        //get to-do object
        To_Do to_do = documentSnapshot.toObject(To_Do.class);

        //prompt user to delete a to-do
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Delete todo?")
                .setMessage("Current todo will be removed from database. Are you sure?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete to-do
                Task<Void> taskDelete = documentSnapshot.getReference().delete();
                taskDelete.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //create an "undo" action to restore the deleted to-do
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "To-Do deleted successfully.", Snackbar.LENGTH_LONG);
                        snackbar.setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //undo delete
                                assert to_do != null;
                                Task<Void> taskUndo = documentSnapshot.getReference().set(to_do);
                                taskUndo.addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(MainActivity.this, "Undo delete...", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this, "Cannot undo deletion.", Toast.LENGTH_SHORT).show();
                                        Log.e(LOG, e.getLocalizedMessage());
                                    }
                                });
                            }
                        }).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Delete failed.", Toast.LENGTH_SHORT).show();
                        Log.e(LOG, e.getLocalizedMessage());
                    }
                });
            }
        }).create().show();
    }

    @Override
    public void updateTODO(DocumentSnapshot documentSnapshot) {
        //get to-do object
        To_Do to_do = documentSnapshot.toObject(To_Do.class);
        EditText editText = new EditText(MainActivity.this);
        assert to_do != null;
        editText.setText(to_do.getTodo());

        //create dialog
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Edit To-Do: ")
                .setView(editText)
                .setPositiveButton("Update", null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        //override dialog's positive button's default behaviour
        //do not close if input is empty
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //check if input is empty
                        if (editText.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this, "To-Do cannot be empty.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //update to-do's timestamp
                        to_do.setTimestamp(new Timestamp(new Date()));

                        //update to-do
                        to_do.setTodo(editText.getText().toString());
                        Task<Void> task = documentSnapshot.getReference().set(to_do);
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Update to-do failed.", Toast.LENGTH_SHORT).show();
                                Log.e(LOG, e.getLocalizedMessage());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Update successful.", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });
            }
        });
        alertDialog.show();
    }
}