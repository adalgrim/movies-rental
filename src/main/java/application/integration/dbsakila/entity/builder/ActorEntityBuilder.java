package application.integration.dbsakila.entity.builder;

import application.integration.dbsakila.entity.ActorEntity;
import application.integration.dbsakila.entity.FilmEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
public final class ActorEntityBuilder {

    private int id;
    private String firstname;
    private String lastname;
    private Date lastUpdate;
    private Set<FilmEntity> films = new HashSet<>();

    private ActorEntityBuilder() {
    }

    public static ActorEntityBuilder anActorEntity() {
        return new ActorEntityBuilder();
    }

    public ActorEntityBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ActorEntityBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ActorEntityBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ActorEntityBuilder withLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public ActorEntityBuilder withFilms(Set<FilmEntity> films) {
        this.films = films;
        return this;
    }

    public ActorEntity build() {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setId(id);
        actorEntity.setFirstname(firstname);
        actorEntity.setLastname(lastname);
        actorEntity.setLastUpdate(lastUpdate);
        actorEntity.setFilms(films);
        return actorEntity;
    }
}
