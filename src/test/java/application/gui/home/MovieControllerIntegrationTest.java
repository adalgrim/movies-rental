package application.gui.home;

import application.MoviesRentalTest;
import application.common.types.Rating;
import application.service.dbsakila.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class MovieControllerIntegrationTest extends MoviesRentalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MovieService movieService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void testMovies() throws Exception {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/movies", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Sql("newMovieTestData.sql")
    public void testNewMovie() throws Exception {
        this.mockMvc.perform(post("/newmovie")
                .param("title", "Zoolandia")
                .param("description", "Magic movie about magic place!")
                .param("length", "100")
                .param("rating", Rating.PG13.toString())
                .param("language.id", "1")
                .param("actors", "1")
                .param("actors", "3")
                .param("categories", "3")
                .param("categories", "2")
                .param("categories", "1")
                .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("movie/1"));
    }

}