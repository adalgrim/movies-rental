package application.service.dbsakila;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

import application.MoviesRentalTest;
import application.common.domain.Film;
import application.common.domain.FilmSearchResult;
import application.common.domain.MovieSearchParams;
import application.common.types.Rating;
import application.integration.dbsakila.dao.CategoryRepository;
import application.integration.dbsakila.dao.FilmRepository;
import application.integration.dbsakila.dao.LanguageRepository;
import application.integration.dbsakila.entity.FilmEntity;
import application.integration.dbsakila.entity.builder.FilmEntityBuilder;
import application.integration.dbsakila.mapper.FilmMapper;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * MovieServiceImplTest.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class MovieServiceImplTest extends MoviesRentalTest {

    private FilmRepository filmRepository = PowerMockito.mock(FilmRepository.class);
    private CategoryRepository categoryRepository = PowerMockito.mock(CategoryRepository.class);
    private LanguageRepository languageRepository = PowerMockito.mock(LanguageRepository.class);

    private MovieService movieService;

    @BeforeMethod
    public void setUp() {
        movieService = new MovieServiceImpl(filmRepository);
    }

    @Test
    public void testGetMoviesCount() throws Exception {
        when(filmRepository.count()).thenReturn(2L);
        assertThat(movieService.getMoviesCount()).isEqualTo(2L);
    }

    @Test
    public void testGetMovies() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 20);

        when(filmRepository.findAll(pageRequest)).thenReturn(this.findAllAsPage());
        assertThat(movieService.getMovies(pageRequest)).isNotEmpty().first().isInstanceOf(Film.class);
        assertThat(movieService.getMovies(pageRequest)).size().isEqualTo(5);
        assertThat(movieService.getMovies(pageRequest)).filteredOn("rating", Rating.PG13).size().isEqualTo(1);
        assertThat(movieService.getMovies(pageRequest)).filteredOn("rating", Rating.R).size().isEqualTo(4);
    }

    @Test
    public void testGetMovie() {
        FilmEntity filmEntity = FilmEntityBuilder.aFilmEntity().withId(1L).withTitle("AAA").withRating(
            Rating.R).withLength(100).withDescription("description").build();
        when(filmRepository.findOne(anyLong())).thenReturn(filmEntity);

        assertThat(movieService.getMovie(1L)).isEqualToComparingFieldByField(FilmMapper.INSTANCE.FilmEntityToFilm(filmEntity));
    }

    @Test
    public void searchMovies() {
        PageRequest pageRequest = new PageRequest(0, 20);
        when(filmRepository.searchAll(
            Mockito.anyString(),
            Mockito.anyString(),
            Mockito.anyInt(),
            Mockito.anyInt(),
            Mockito.anyInt(),
            Mockito.anyInt(),
            Mockito.anyInt(),
            Mockito.anyInt(),
            Mockito.any(Pageable.class)
        )).thenReturn(this.searchResult());

        assertThat(movieService.getMovies(new MovieSearchParams(), pageRequest)).isNotEmpty().first().isInstanceOf(FilmSearchResult.class);
    }

    private Page<FilmSearchResult> searchResult() {
        List<FilmSearchResult> searchResult = new ArrayList<>();
        searchResult.add(new FilmSearchResult(1, "Star wars", "A long time ago in a galaxy far, far away....", 2016, 180, Rating.R, ""));
        searchResult.add(new FilmSearchResult(2, "Star trek", "A long time ago in a galaxy far, far away....", 2016, 170, Rating.R, ""));
        return  new PageImpl<>(searchResult);
    }


    private Page<FilmEntity> findAllAsPage() {
        List<FilmEntity> filmEntities = new ArrayList<>();

        FilmEntityBuilder filmEntityBuilder = FilmEntityBuilder.aFilmEntity().withId(1L).withTitle("AAA").withRating(
            Rating.R).withLength(100).withDescription("description");

        filmEntities.add(filmEntityBuilder.build());
        filmEntities.add(filmEntityBuilder.but().withId(2L).withTitle("BBB").build());
        filmEntities.add(filmEntityBuilder.but().withId(3L).withTitle("CCC").build());
        filmEntities.add(filmEntityBuilder.but().withId(4L).withTitle("DDD").withLength(92).build());
        filmEntities.add(filmEntityBuilder.but().withId(5L).withTitle("EEE").withRating(Rating.PG13).build());

        return new PageImpl<>(filmEntities);
    }


}