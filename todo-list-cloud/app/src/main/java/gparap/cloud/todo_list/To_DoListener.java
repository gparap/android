package gparap.cloud.todo_list;

import com.google.firebase.firestore.DocumentSnapshot;

public interface To_DoListener {
    /**
     * Changes the state of a to-do.
     *
     * @param isDone           to-do is done or not
     * @param documentSnapshot a document in Cloud Firestore database
     */
    void setCheckedChanged(boolean isDone, DocumentSnapshot documentSnapshot);

    /**
     * Deletes a to-do.
     *
     * @param documentSnapshot a document in Cloud Firestore database
     */
    void deleteTODO(DocumentSnapshot documentSnapshot);

    /**
     * Edits a to-do.
     *
     * @param documentSnapshot a document in Cloud Firestore database
     */
    void updateTODO(DocumentSnapshot documentSnapshot);
}
