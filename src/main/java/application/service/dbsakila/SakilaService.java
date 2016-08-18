package application.service.dbsakila;

import application.common.domain.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Fetch all Movies with pages
     *
     * @return page object
     */
    Page<Film> getMovies(Pageable pageable);

    /**
     * Fetch all Movies without page
     *
     * @return list of movies
     */
    List<Film> getMovies();

    /**
     * Save Film
     *
     * @param film
     */
    void saveFilm(Film film);
}
