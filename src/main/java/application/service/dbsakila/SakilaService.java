package application.service.dbsakila;

import application.common.domain.Film;

import java.util.List;

/**
 * SakilaService interface.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public interface SakilaService {

    /**
     * Get the number of all movies in movies rental database.
     *
     * @return long number of movies in database
     */
    long getMoviesCount();

    /**
     * Fetch all Movies
     *
     * @return list of movies
     */
    List<Film> getMovies();

    /**
     * Fetch all Movies
     *
     * @return list of movies
     */
    List<Film> getMovies2();
}
