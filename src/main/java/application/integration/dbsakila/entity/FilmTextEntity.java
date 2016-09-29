package application.integration.dbsakila.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Adam_Skowron on 24.08.2016.
 */
@Entity
@Table(name = "film_text")
public class FilmTextEntity implements Serializable {

    private String title;

    private String description;

    @Id
    @OneToOne
    @JoinColumn(name = "film_id")
    private FilmEntity filmEntity;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public FilmEntity getFilmEntity() {
        return filmEntity;
    }


}
