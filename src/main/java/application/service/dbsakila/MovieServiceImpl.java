package application.service.dbsakila;

import application.common.domain.Film;
import application.common.domain.FilmSearchResult;
import application.common.domain.MovieSearchParams;
import application.integration.dbsakila.dao.FilmRepository;
import application.integration.dbsakila.entity.FilmEntity;
import application.integration.dbsakila.mapper.FilmMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sakila Service Impl.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
@Service
public class MovieServiceImpl implements MovieService {

    private FilmRepository filmRepository;

    @Autowired
    public MovieServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public long getMoviesCount() {
        return filmRepository.count();
    }

    @Override
    public Page<FilmSearchResult> getMovies(MovieSearchParams movieSearchParams, Pageable pageable) {
        return filmRepository.searchAll(StringUtils.isNotEmpty(movieSearchParams.getTitle()) ? "%" + movieSearchParams.getTitle() + "%" : null,
                StringUtils.isNotEmpty(movieSearchParams.getActorName()) ? "%" + movieSearchParams.getActorName() + "%" : null,
                movieSearchParams.getLengthFrom(),
                movieSearchParams.getLengthTo(),
                movieSearchParams.getYearFrom(),
                movieSearchParams.getYearTo(),
                movieSearchParams.getLanguageId(),
                movieSearchParams.getCategoryId(),
                pageable);
    }

    @Override
    public Page<Film> getMovies(Pageable pageable) {
        return filmRepository
                .findAll(pageable)
                .map(FilmMapper.INSTANCE::filmEntityToFilm);
    }

    @Override
    public Film getMovie(Long id) {
        return FilmMapper.INSTANCE.filmEntityToFilm(filmRepository.findOne(id));
    }

    @Override
    @Transactional
    public Film save(Film film) {
        FilmEntity filmEntity = filmRepository.save(FilmMapper.INSTANCE.filmToFilmEntity(film));
        return FilmMapper.INSTANCE.filmEntityToFilm(filmEntity);
    }

}