package gparap.apps.wallpaper;

/**
 * Model class for wallpaper.
 * Created by gparap on 2020-10-24.
 */
public class Wallpaper {
    private int id;
    private String url, photographer;

    public Wallpaper() {
    }

    @SuppressWarnings({"unused", "RedundantSuppression"})
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }
}