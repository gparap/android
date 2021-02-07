package gparap.apps.notebook;

/**
 * Model class for a Note object.
 * Created by gparap on 2020-10-21.
 */
public class Note {
    private Long id;
    private String title,
                   details;

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
