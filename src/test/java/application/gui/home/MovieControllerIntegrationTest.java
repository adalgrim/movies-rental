package application.gui.home;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import application.MoviesRentalTest;
import application.common.domain.Film;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest extends MoviesRentalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testMovies() throws Exception {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/movies", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
    }

//    @Test
//    @Ignore
//    @SuppressWarnings("unchecked")
//    public void getPosters() {
//        List<Film> filmList = sakilaService.getMovies();
//        for (Film film : filmList) {
//            String[] titleParts = film.getTitle().split(" ");
//            for (String part : titleParts) {
//                HashMap<String, String>
//                    hashMap =
//                    restTemplate.getForObject("http://www.omdbapi.com/?t=" + part.toLowerCase(), HashMap.class);
//                if (hashMap.containsKey("Poster") && !"N/A".equalsIgnoreCase(hashMap.get("Poster"))) {
//                    film.setPoster(hashMap.get("Poster"));
//                    break;
//                }
//            }
//            if (!StringUtils.isEmpty(film.getPoster())) {
//                sakilaService.saveFilm(film);
//            }
//
//        }
//    }


}
