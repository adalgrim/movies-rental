package application.integration.dbsakila.entity;

import application.common.types.Rating;
import application.integration.dbsakila.converter.RatingConverter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Film Entity.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
@Entity
@Table(name = "film")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "film_id")
    private long id;

    private String title;

    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    private int length;

    @Convert(converter=RatingConverter.class)
    private Rating rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "FilmEntity{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }



}
