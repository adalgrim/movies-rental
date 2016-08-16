package application.gui.home;

import application.service.dbsakila.SakilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Movies Controller.
 *
 * Created by Adam_Skowron on 11.08.2016.
 */
@Controller
public class MovieController {

    private SakilaService sakilaService;

    @Autowired
    public MovieController(SakilaService sakilaService) {
        this.sakilaService = sakilaService;
    }

    @RequestMapping("/movies")
    String movies(Model model) {
        model.addAttribute("movies", sakilaService.getMovies());
        return "pages/movieList";
    }

}
