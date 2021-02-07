package gparap.cloud.notebook;

/**
 * Model class for notes.
 */
public class Note {
    private String id,
                   title,
                   details;

    //TODO: date, time
    public Note() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
