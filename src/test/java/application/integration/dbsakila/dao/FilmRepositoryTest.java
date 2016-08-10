package application.integration.dbsakila.dao;

import static org.assertj.core.api.Assertions.assertThat;

import application.MoviesRentalTest;
import application.integration.dbsakila.entity.FilmEntity;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * FilmRepositoryTest.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class FilmRepositoryTest extends MoviesRentalTest {

    @Autowired
    private FilmRepository filmRepository;

    @BeforeMethod
    public void setUp() throws Exception {

        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setTitle("AAA");

        FilmEntity filmEntity2 = new FilmEntity();
        filmEntity2.setTitle("BBB");

        filmRepository.save(filmEntity);
        filmRepository.save(filmEntity2);
    }

    @Test
    public void testGetMoviesCount() throws Exception {
        long moviesCount = filmRepository.count();
        assertThat(moviesCount).isEqualTo(2);
    }


}