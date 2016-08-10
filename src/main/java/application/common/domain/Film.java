package application.common.domain;

/**
 * Film DO.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class Film {

    private long id;

    private String title;

    private String description;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
