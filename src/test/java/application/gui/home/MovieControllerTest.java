package application.gui.home;

import application.MoviesRentalTest;
import application.common.domain.Film;
import application.common.domain.FilmSearchResult;
import application.common.domain.Language;
import application.common.domain.MovieSearchParams;
import application.common.types.Rating;
import application.service.dbsakila.ActorService;
import application.service.dbsakila.CategoryService;
import application.service.dbsakila.LanguageService;
import application.service.dbsakila.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.internal.verification.VerifyNoMoreInteractions.verifyNoMoreInteractions;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
public class MovieControllerTest extends MoviesRentalTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Mock
    private MovieViewModel movieViewModel;

    @Mock
    private CategoryService categoryService;

    @Mock
    private LanguageService languageService;

    @Mock
    private ActorService actorService;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private SortHandlerMethodArgumentResolver sortArgumentResolver;

    @InjectMocks
    MovieController movieController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(movieController)
                .setCustomArgumentResolvers(pageableArgumentResolver, sortArgumentResolver)
                .build();
    }

    @Test
    public void newMovieTest() throws Exception {

        Film film = new Film();
        film.setId(1l);
        when(movieService.save(any(Film.class))).thenReturn(film);

        Language language = new Language();
        language.setId(1);
        language.setName("English");

        RequestBuilder request = post("/newmovie")
                .param("title", "Zoolandia")
                .param("description", "Magic movie about magic place!")
                .param("length", "100")
                .param("rating", Rating.PG13.toString())
                .param("language.id", "1")
                .param("actors", "1")
                .param("actors", "7")
                .param("categories", "3")
                .param("categories", "2")
                .param("categories", "1")
            .with(csrf());

        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(redirectedUrl("movie/1"));

    }

    @Test
    public void testMovies() throws Exception {

        FilmSearchResult filmSearchResult = new FilmSearchResult(1, "A", "", 0, 0, Rating.R, "");
        FilmSearchResult filmSearchResult2 = new FilmSearchResult(2, "B", "", 0, 0, Rating.PG13, "link");
        List<FilmSearchResult> filmSearchResultList = new ArrayList<>();
        filmSearchResultList.add(filmSearchResult);
        filmSearchResultList.add(filmSearchResult2);

        when(movieService.getMovies(any(), any())).thenReturn(new PageImpl<>(filmSearchResultList));

        this.mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/movieList"))
                .andExpect(model()
                        .attribute("page", hasProperty("content", hasSize(2))
                        )
                )
                .andExpect(model()
                        .attribute("page",
                                hasProperty("content",
                                        hasItem(
                                                allOf(
                                                        hasProperty("id", is(1L)),
                                                        hasProperty("title", is("A"))
                                                )
                                        )
                                )
                        )
                )
                .andExpect(model()
                        .attribute("page",
                                hasProperty("content",
                                        hasItem(
                                                allOf(
                                                        hasProperty("id", is(2L)),
                                                        hasProperty("title", is("B"))
                                                )
                                        ))));

        verify(movieService, times(1)).getMovies(any(MovieSearchParams.class), any(Pageable.class));
        verifyNoMoreInteractions(movieService);
    }

}