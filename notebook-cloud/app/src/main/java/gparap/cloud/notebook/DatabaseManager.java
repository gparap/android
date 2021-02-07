package gparap.cloud.notebook;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

@SuppressWarnings("Convert2Lambda")
public class DatabaseManager {

    public DatabaseManager() {

    }

    public FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public FirebaseUser getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    /**
     * Registers user to database using email and password authentication.
     * @param email user's email
     * @param password user's password
     * @return task
     */
    public Task<AuthResult> linkUserWithCredential(String email, String password) {
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);
        return getFirebaseAuth().getCurrentUser().linkWithCredential(credential);
    }

    /**
     * Updates user profile information.
     *
     * @param username username
     * @return task
     */
    public Task<Void> updateUserProfile(String username) {

        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        return getFirebaseUser().updateProfile(profile);
    }

    /**
     * Gets Cloud Firestore database instance.
     *
     * @return FirebaseFirestore instance
     */
    public FirebaseFirestore getInstance() {
        return FirebaseFirestore.getInstance();
    }

    /**
     * Saves a note to database.
     *
     * @param note      note object
     * @param isNewNote if it is a new note or an existing one
     * @return task
     */
    public Task<Void> saveNote(Note note, boolean isNewNote, String userId) {
        CollectionReference collectionReference;
        DocumentReference documentReference;

        //create new note
        if (isNewNote && note.getId() == null) {
            collectionReference = getInstance().collection("notebook").document(userId).collection("notes");
            documentReference = collectionReference.document();
            note.setId(documentReference.getId());
            return documentReference.set(note);
        }
        //update existing note
        else {
            collectionReference = getInstance().collection("notebook").document(userId).collection("notes");
            documentReference = collectionReference.document(note.getId());
            return documentReference.update("title", note.getTitle(), "details", note.getDetails());
        }
    }

    /**
     * Gets a specific note title from the database.
     *
     * @param title note title
     * @return task
     */
    public Task<QuerySnapshot> getNoteTitle(String title, String userId) {
        CollectionReference collectionReference = getInstance().collection("notebook").document(userId).collection("notes");
        Query query = collectionReference.whereEqualTo("title", title);
        return query.get();
    }

    /**
     * Gets all notes from database as adapter configuration options.
     *
     * @return adapter configuration options
     */
    public FirestoreRecyclerOptions<Note> getNotes(String userId) {
        Query query = getInstance().collection("notebook").document(userId).collection("notes");
        return new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();
    }

    /**
     * Deletes a note from database.
     *
     * @param userId user id
     * @return task
     */
    public Task<Void> deleteNote(String noteId, String userId) {
        DocumentReference document = getInstance().collection("notebook").document(userId).collection("notes").document(noteId);
        return document.delete();
    }

    /**
     * Deletes user from database.
     *
     * @param userId user id
     * @return task
     */
    public Task<Void> deleteUser(String userId) {
        //delete user's inner documents (firestore can't remove document containing sub-collections)
        Task<QuerySnapshot> query = getInstance().collection("notebook").document(userId).collection("notes").get();
        query.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document : task.getResult().getDocuments()) {
                    DocumentReference documentReference = document.getReference();
                    documentReference.delete();
                }
            }
        });
        //delete user from database
        DocumentReference document = getInstance().collection("notebook").document(userId);
        return document.delete();
    }
}
