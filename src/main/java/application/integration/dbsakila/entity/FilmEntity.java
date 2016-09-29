package application.integration.dbsakila.entity;

import application.common.types.Rating;
import application.integration.dbsakila.converter.RatingConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Film Entity.
 * <p>
 * Created by Adam_Skowron on 09.08.2016.
 */
@Entity
@Table(name = "film")
public class FilmEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private long id;

    private String title;

    private String description;

    @OneToOne(mappedBy = "filmEntity")
    private FilmTextEntity filmTextEntity;

    @Column(name = "release_year")
    private int releaseYear;

    private int length;

    @Convert(converter = RatingConverter.class)
    private Rating rating;

    private String poster;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity language;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<CategoryEntity> categories;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<ActorEntity> actors;

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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    public Set<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(Set<ActorEntity> actors) {
        this.actors = actors;
    }

    public FilmTextEntity getFilmTextEntity() {
        return filmTextEntity;
    }

    public void setFilmTextEntity(FilmTextEntity filmTextEntity) {
        this.filmTextEntity = filmTextEntity;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", filmTextEntity=" + filmTextEntity +
                ", releaseYear=" + releaseYear +
                ", length=" + length +
                ", rating=" + rating +
                ", poster='" + poster + '\'' +
                ", language=" + language +
                ", categories=" + categories +
                ", actors=" + actors +
                '}';
    }
}
