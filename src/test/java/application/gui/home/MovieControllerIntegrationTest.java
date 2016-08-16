package application.gui.home;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testMovies() throws Exception {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/movies", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
    }


}
