package application.gui.home;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.testng.Assert.*;

import application.MoviesRentalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Adam_Skowron on 16.08.2016.
 */
public class MovieControllerTest extends MoviesRentalTest {

    @Autowired
    private WebApplicationContext webCtx;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
    }

    @Test
    public void testMovies() throws Exception {
        this.mockMvc.perform(get("/movies"))
            .andExpect(status().isOk())
            .andExpect(view().name("pages/movieList"));
    }

}