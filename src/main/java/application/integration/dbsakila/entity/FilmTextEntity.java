package application.integration.dbsakila.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Adam_Skowron on 24.08.2016.
 */
@Entity
@Table(name = "film_text")
public class FilmTextEntity {

    @Id
    @Column(name = "film_id")
    private long id;

    private String description;

    @OneToOne(mappedBy="filmTextEntity")
    @PrimaryKeyJoinColumn
    private FilmEntity filmEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FilmEntity getFilmEntity() {
        return filmEntity;
    }

    public void setFilmEntity(FilmEntity filmEntity) {
        this.filmEntity = filmEntity;
    }
}
