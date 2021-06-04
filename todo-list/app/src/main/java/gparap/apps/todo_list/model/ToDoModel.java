package gparap.apps.todo_list.model;

/**
 * Model of a To_Do.
 * Created by gparap on 2020-10-15.
 */
public class ToDoModel {
    private long id;
    private boolean isDone;
    private String todo,
                   time,
                   date;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDone(int done) {
        isDone = done == 1;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ToDoModel() {
    }
}
