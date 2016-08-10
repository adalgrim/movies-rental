package application.service.dbsakila;

import application.integration.dbsakila.dao.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}