package application.gui.home;

import static org.assertj.core.api.Assertions.assertThat;

import application.MoviesRentalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * Integration test for HomeController.
 *
 * Created by Adam_Skowron on 10.08.2016.
 */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerIntegrationTest extends MoviesRentalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void indexTest() {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void fakeAddressTest() {
        HttpStatus httpStatus = this.restTemplate.getForEntity("/fakeAddress", String.class).getStatusCode();
        assertThat(httpStatus).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
