package application.gui.home;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import application.MoviesRentalTest;
import application.common.domain.FilmSearchResult;
import application.common.domain.MovieSearchParams;
import application.common.types.Rating;
import application.service.dbsakila.CategoryService;
import application.service.dbsakila.LanguageService;
import application.service.dbsakila.MovieService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieControllerTest extends MoviesRentalTest {

    @Autowired
    private WebApplicationContext webCtx;

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Mock
    private MovieViewModel movieViewModel;

    @Mock
    private CategoryService categoryService;

    @Mock
    private LanguageService languageService;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private SortHandlerMethodArgumentResolver sortArgumentResolver;

    @InjectMocks
    MovieController movieController;

    @BeforeMethod
    public void setUp() {
        //mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(movieController)
            .setCustomArgumentResolvers(pageableArgumentResolver, sortArgumentResolver)
            .build();
    }

    @Test
    public void testMovies() throws Exception {

        FilmSearchResult filmSearchResult = new FilmSearchResult(1, "A", "", 0, 0, Rating.R, "");
        FilmSearchResult filmSearchResult2 = new FilmSearchResult(2, "B", "", 0, 0, Rating.R, "");
        List<FilmSearchResult> filmSearchResultList = new ArrayList<>();
        filmSearchResultList.add(filmSearchResult);
        filmSearchResultList.add(filmSearchResult2);

        when(movieService.getMovies(Mockito.any(), Mockito.any())).thenReturn(new PageImpl<>(filmSearchResultList));

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

        verify(movieService, times(1)).getMovies(Mockito.any(MovieSearchParams.class), Mockito.any(Pageable.class));
        //verifyNoMoreInteractions(todoServiceMock);



    }

}