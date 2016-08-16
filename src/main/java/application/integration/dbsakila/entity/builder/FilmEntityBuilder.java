package application.integration.dbsakila.entity.builder;

import application.common.types.Rating;
import application.integration.dbsakila.entity.FilmEntity;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
public final class FilmEntityBuilder {

    private long id;
    private String title;
    private String description;
    private int releaseYear;
    private int length;
    private Rating rating;

    private FilmEntityBuilder() {
    }

    public static FilmEntityBuilder aFilmEntity() {
        return new FilmEntityBuilder();
    }

    public FilmEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public FilmEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FilmEntityBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FilmEntityBuilder withReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public FilmEntityBuilder withLength(int length) {
        this.length = length;
        return this;
    }

    public FilmEntityBuilder withRating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public FilmEntityBuilder but() {
        return aFilmEntity().withId(id).withTitle(title).withDescription(description).withReleaseYear(releaseYear)
            .withLength(length).withRating(rating);
    }

    public FilmEntity build() {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setId(id);
        filmEntity.setTitle(title);
        filmEntity.setDescription(description);
        filmEntity.setReleaseYear(releaseYear);
        filmEntity.setLength(length);
        filmEntity.setRating(rating);
        return filmEntity;
    }
}
