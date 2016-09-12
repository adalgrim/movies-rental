package application.gui.home;

import application.MoviesRentalTest;
import application.common.types.Rating;
import application.service.dbsakila.MovieService;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
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
    @Ignore
    public void testMovies() throws Exception {


        HttpStatus httpStatus = this.restTemplate.getForEntity("/movies", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testFakeAddress() throws Exception {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/fakeMovieAddress", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testRedirection() throws Exception {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/movies", String.class).getStatusCode();
        assertTrue(httpStatus.is3xxRedirection());
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

    private void loginBefore() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.set("username", "admin");
        form.set("password", "admin");
        getCsrf(form, headers);
        ResponseEntity<String> entity = this.restTemplate.exchange("/",
                HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(form, headers),
                String.class);
    }

    private void getCsrf(MultiValueMap<String, String> form, HttpHeaders headers) {
        ResponseEntity<String> page = this.restTemplate.getForEntity("/", String.class);
        String cookie = page.getHeaders().getFirst("Set-Cookie");
        headers.set("Cookie", cookie);
        String body = page.getBody();
        Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*")
                .matcher(body);
        matcher.find();
        form.set("_csrf", matcher.group(1));
    }

}