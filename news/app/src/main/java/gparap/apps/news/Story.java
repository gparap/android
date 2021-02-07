package gparap.apps.news;

/**
 * Model class for all stories.
 * Created by gparap on 2020-11-18.
 */
public class Story {
    private String id, title, url;

    public Story() {

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
