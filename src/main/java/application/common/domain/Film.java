package application.common.domain;

import application.common.types.Rating;
import application.integration.dbsakila.entity.FilmEntity;

/**
 * Film DO.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class Film {

    private long id;

    private String title;

    private String description;

    private int releaseYear;

    private int length;

    private Rating rating;

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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


}
