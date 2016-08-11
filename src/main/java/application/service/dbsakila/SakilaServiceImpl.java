package application.service.dbsakila;

import application.common.domain.Film;
import application.integration.dbsakila.dao.FilmRepository;
import application.integration.dbsakila.entity.FilmEntity;
import application.integration.dbsakila.mapper.FilmMapper;
import application.integration.dbsakila.mapper.FilmMapperMapStruct;
import fr.xebia.extras.selma.Selma;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Film> getMovies() {
        FilmMapper filmMapper = Selma.builder(FilmMapper.class).build();
        return filmRepository
            .findAll()
            .stream()
            .map(filmMapper::toFilm)
            .collect(Collectors.toList());
    }

    @Override
    public List<Film> getMovies2() {
        return filmRepository
            .findAll()
            .stream()
            .map(FilmMapperMapStruct.INSTANCE::FilmEntityToFilm)
            .collect(Collectors.toList());
    }
}