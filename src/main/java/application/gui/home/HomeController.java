package application.gui.home;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import application.service.dbsakila.MovieService;

/**
 * RestController of Movies Rental.
 *
 * Created by Adam_Skowron on 08.08.2016.
 */
@Controller
public class HomeController {

    private MovieService movieService;

    @Autowired
    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("moviesCount", movieService.getMoviesCount());
        return "pages/home";
    }

}
