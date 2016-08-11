package application.service.dbsakila;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

import application.MoviesRentalTest;
import application.common.domain.Film;
import application.integration.dbsakila.dao.FilmRepository;
import application.integration.dbsakila.entity.FilmEntity;
import org.assertj.core.util.Compatibility;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * SakilaServiceImplTest.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class SakilaServiceImplTest extends MoviesRentalTest {

    //private FilmRepository filmRepository = PowerMockito.mock(FilmRepository.class);

    @Autowired
    private FilmRepository filmRepository;

    private SakilaService sakilaService;

    @BeforeMethod
    public void setUp() {
        sakilaService = new SakilaServiceImpl(filmRepository);
    }

//    @Test
//    public void testGetMoviesCount() throws Exception {
//        when(filmRepository.count()).thenReturn(2L);
//        assertThat(sakilaService.getMoviesCount()).isEqualTo(2L);
//    }



    @Test
    public void testGetMovies2() throws Exception {

        System.out.println("#Count: "+this.sakilaService.getMoviesCount());

        long start = System.nanoTime();
        for(int i = 0; i < 100; i++) {
            List<Film> films = this.sakilaService.getMovies2();
        }

        System.out.println("Sum: "+((System.nanoTime()-start)/10E-9));
    }

    @Test
    public void testGetMovies() throws Exception {

        System.out.println("#Count: "+this.sakilaService.getMoviesCount());

        long start = System.nanoTime();
        for(int i = 0; i < 100; i++) {
            List<Film> films = this.sakilaService.getMovies();
        }

        System.out.println("Sum: "+((System.nanoTime()-start)/10E-9));
    }

//    @DataProvider
//    public Object[][] provider() {
//
//        int numberOfEntities = 10000;
//
//        final Object[][] data = new FilmEntity[numberOfEntities][1];
//        for (int i = 0; i < 5; i++) {
//            data[i][0] = new FilmEntity(r.nextInt(5), i, "test string");
//        }
//
//
//    }




}