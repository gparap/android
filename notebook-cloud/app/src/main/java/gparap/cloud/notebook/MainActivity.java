package gparap.cloud.notebook;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;

@SuppressWarnings({"Convert2Lambda", "unchecked"})
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    FloatingActionButton fabAdd;
    DatabaseManager databaseManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init manager for database operations
        databaseManager = new DatabaseManager();
        databaseManager.getInstance();

        //check user authentication
        if (getCallingActivity() != null && getCallingActivity().getClassName().contains("LoginActivity")) {
            //user is a guest
            if (getIntent().getStringExtra("user_auth").equals("anonymous")) {
                Task<AuthResult> authResultTask = databaseManager.getFirebaseAuth().signInAnonymously();
                authResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Continue as Guest", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //init toolbar
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        //init drawer toggle
        drawerLayout = findViewById(R.id.activityMain);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        //init navigation view with menu item selected listener
        navigationView = findViewById(R.id.drawerNavigation);
        navigationView.setNavigationItemSelectedListener(this);

        //display username on navigation header
        View view = navigationView.getHeaderView(0);    //gets ConstraintLayout
        TextView textView = view.findViewById(R.id.textViewUser);
        try {
            if (databaseManager.getFirebaseUser().isAnonymous()) {
                textView.setText(R.string.guest);   //default
            } else {
                textView.setText(databaseManager.getFirebaseUser().getDisplayName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //init recycler view with notes from database
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            noteAdapter = new NoteAdapter(databaseManager.getNotes(databaseManager.getFirebaseUser().getUid()), this);
            noteAdapter.startListening();   //start listening to database
            recyclerView.setAdapter(noteAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //add a new note
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNote();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //synchronize drawer toggle with layout
        drawerToggle.syncState();

        //init progress bar
        progressBar = findViewById(R.id.progressBarMain);
    }

    @Override
    public void onStop() {
        super.onStop();

        //stop listening to database
        if (noteAdapter != null)
            noteAdapter.stopListening();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //view all notes
            case R.id.notesDisplay:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            //add new note
            case R.id.noteAdd:
                addNewNote();
                break;

            //goto registration form if user is not registered
            case R.id.noteLogin:
                gotoRegister();
                break;

            //if user is a guest prompt them to login or register
            // because their notes will be removed from database
            case R.id.noteLogout:
                if (databaseManager.getFirebaseUser().isAnonymous()) {
                    drawerLayout.closeDrawer(GravityCompat.START);

                    //init dialog to prompt user about erase
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Exit without Connection?")
                            .setMessage("If you exit now without connecting, your notes will not be saved on database. Are you sure?")
                            .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    gotoRegister();
                                }
                            })
                            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //delete user from database
                                    Task<Void> voidTask = databaseManager.deleteUser(databaseManager.getFirebaseUser().getUid());
                                    voidTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(MainActivity.this, "User data successfully deleted.", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.VISIBLE);

                                            //delete user from authentication
                                            if (databaseManager.getFirebaseUser() != null) {
                                                Task<Void> task = databaseManager.getFirebaseUser().delete();
                                                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(MainActivity.this, "User Successfully deleted.", Toast.LENGTH_SHORT).show();
                                                        gotoLogin();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            gotoLogin();
                                        }
                                    });
                                }
                            });
                    //show dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                //if user is already registered, logout
                else {
                    //init dialog to prompt user for logout
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Logout")
                            .setMessage("You will be logged out. Are you sure?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    databaseManager.getFirebaseAuth().signOut();
                                    Toast.makeText(MainActivity.this, "You are logging out...", Toast.LENGTH_SHORT).show();
                                    gotoLogin();
                                }
                            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    //show dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                progressBar.setVisibility(View.INVISIBLE);
                break;

            default:
                Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        //deactivated
    }

    /**
     * Goes to note activity with transition effects.
     */
    private void addNewNote() {
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
        finish();
    }

    /**
     * Goes back to login and finishes current activity.
     */
    private void gotoLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Goes to registration form if user is not registered.
     */
    private void gotoRegister() {
        if (databaseManager.getFirebaseUser().isAnonymous()) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.putExtra("parent", "MainActivity");
            startActivity(intent);
        } else {
            Toast.makeText(this, "You are already signed in.", Toast.LENGTH_SHORT).show();
        }
    }
}