package application.service.dbsakila;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

import application.MoviesRentalTest;
import application.common.domain.Film;
import application.common.types.Rating;
import application.integration.dbsakila.dao.FilmRepository;
import application.integration.dbsakila.entity.FilmEntity;
import application.integration.dbsakila.entity.builder.FilmEntityBuilder;
import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * SakilaServiceImplTest.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class SakilaServiceImplTest extends MoviesRentalTest {

    private FilmRepository filmRepository = PowerMockito.mock(FilmRepository.class);

    private SakilaService sakilaService;

    @BeforeMethod
    public void setUp() {
        sakilaService = new SakilaServiceImpl(filmRepository);
    }

    @Test
    public void testGetMoviesCount() throws Exception {
        when(filmRepository.count()).thenReturn(2L);
        assertThat(sakilaService.getMoviesCount()).isEqualTo(2L);
    }

    @Test
    public void testGetMovies() throws Exception {
        when(filmRepository.findAll()).thenReturn(this.findAll());

        assertThat(sakilaService.getMovies()).isNotEmpty().first().isInstanceOf(Film.class);
        assertThat(sakilaService.getMovies()).size().isEqualTo(5);
        assertThat(sakilaService.getMovies()).filteredOn("rating", Rating.PG13).size().isEqualTo(1);
        assertThat(sakilaService.getMovies()).filteredOn("rating", Rating.R).size().isEqualTo(4);
    }

    private List<FilmEntity> findAll() {
        List<FilmEntity> filmEntities = new ArrayList<>();

        FilmEntityBuilder filmEntityBuilder = FilmEntityBuilder.aFilmEntity().withId(1L).withTitle("AAA").withRating(
            Rating.R).withLength(100).withDescription("description");

        filmEntities.add(filmEntityBuilder.build());
        filmEntities.add(filmEntityBuilder.but().withId(2L).withTitle("BBB").build());
        filmEntities.add(filmEntityBuilder.but().withId(3L).withTitle("CCC").build());
        filmEntities.add(filmEntityBuilder.but().withId(4L).withTitle("DDD").withLength(92).build());
        filmEntities.add(filmEntityBuilder.but().withId(5L).withTitle("EEE").withRating(Rating.PG13).build());

        return filmEntities;
    }


}