package application.service.dbsakila;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

import application.MoviesRentalTest;
import application.integration.dbsakila.dao.FilmRepository;
import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
}