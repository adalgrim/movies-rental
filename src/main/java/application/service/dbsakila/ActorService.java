package application.service.dbsakila;

import application.common.domain.Actor;

import java.util.List;
import java.util.Set;

/**
 * Created by Adam_Skowron on 24.08.2016.
 */
public interface ActorService extends SearchService {

    /**
     * Search actors by name or surname
     *
     * @param name part of the name or surname
     * @return List of matched actors
     */
    List<Actor> searchActors(String name);

    /**
     * Find all actors based on their ids
     *
     * @param actorIds set of ids
     * @return list of actors
     */
    List<Actor> findAll(Set<Long> actorIds);

    /**
     * Find one
     *
     * @param id long id
     * @return actor DTO
     */
    Actor findOne(long id);
}
