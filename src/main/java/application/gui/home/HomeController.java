package application.gui.home;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import application.service.dbsakila.SakilaService;

/**
 * RestController of Movies Rental.
 *
 * Created by Adam_Skowron on 08.08.2016.
 */
@Controller
public class HomeController {

    private SakilaService sakilaService;

    @Autowired
    public HomeController(SakilaService sakilaService) {
        this.sakilaService = sakilaService;
    }

    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("moviesCount", sakilaService.getMoviesCount());
        return "pages/home";
    }

}
