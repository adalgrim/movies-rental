package application.service.dbsakila;

import application.common.domain.Film;
import application.common.domain.FilmSearchResult;
import application.common.domain.MovieSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * MovieService interface.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public interface MovieService {

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
    Page<FilmSearchResult> getMovies(MovieSearchParams movieSearchParams, Pageable pageable);

    /**
     * Fetch all Movies with pages
     *
     * @return page object
     */
    Page<Film> getMovies(Pageable pageable);

    /**
     *
     * @param id movie id
     * @return Movie DTO
     */
    Film getMovie(Long id);

    /**
     * Save Movie to db.
     *
     * @param film dto
     */
    Film save(Film film);
}
