package application;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Adam_Skowron on 10.08.2016.
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public abstract class MoviesRentalTest {

}
