package gparap.cloud.todo_list;

import com.google.firebase.Timestamp;

/**
 * Model class for a to-do.
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public class To_Do {
    private String content;
    private boolean isDone;
    private Timestamp timestamp;
    private String userID;

    public To_Do() {
    }

    public To_Do(String content, boolean isDone, Timestamp timestamp, String userID) {
        this.content = content;
        this.isDone = isDone;
        this.timestamp = timestamp;
        this.userID = userID;
    }

    public String getTodo() {
        return content;
    }

    public void setTodo(String content) {
        this.content = content;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
