package application.service.dbsakila;

import application.common.domain.Film;
import application.integration.dbsakila.dao.FilmRepository;
import application.integration.dbsakila.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sakila Service Impl.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
@Service
public class SakilaServiceImpl implements SakilaService {

    private FilmRepository filmRepository;

    @Autowired
    public SakilaServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public long getMoviesCount() {
        return filmRepository.count();
    }

    @Override
    public Page<Film> getMovies(Pageable pageable) {
        return filmRepository
            .findAll(pageable)
            .map(FilmMapper.INSTANCE::FilmEntityToFilm);
    }

    @Override
    public List<Film> getMovies() {
        return filmRepository
            .findAll()
            .stream()
            .map(FilmMapper.INSTANCE::FilmEntityToFilm)
            .collect(Collectors.toList());
    }

    @Override
    public void saveFilm(Film film) {
        filmRepository.save(FilmMapper.INSTANCE.FilmToFilmEntity(film));
    }
}