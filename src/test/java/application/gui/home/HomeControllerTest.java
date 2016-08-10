package application.gui.home;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import application.MoviesRentalTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * HomeControllerTest.
 *
 * Created by Adam_Skowron on 10.08.2016.
 */
public class HomeControllerTest extends MoviesRentalTest {

    @Autowired
    private WebApplicationContext webCtx;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void testWrongPath() throws Exception {
        this.mockMvc.perform(get("/fakeAddress")).andExpect(status().isNotFound());
    }

}