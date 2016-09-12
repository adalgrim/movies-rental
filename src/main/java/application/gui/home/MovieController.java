package application.gui.home;

import application.common.domain.Film;
import application.common.domain.MovieSearchParams;
import application.common.types.Rating;
import application.gui.propertyeditor.StringArrayToLongSetEditor;
import application.gui.utils.PageWrapper;
import application.gui.utils.PaginationHelper;
import application.service.dbsakila.ActorService;
import application.service.dbsakila.CategoryService;
import application.service.dbsakila.LanguageService;
import application.service.dbsakila.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

/**
 * Movies Controller.
 *
 * Created by Adam_Skowron on 11.08.2016.
 */
@Controller
public class MovieController {

    private MovieService movieService;

    private LanguageService languageService;

    private CategoryService categoryService;

    private ActorService actorService;

    @Autowired
    private MovieViewModel movieViewModel;

    @Autowired
    public MovieController(MovieService movieService, LanguageService languageService,
                           CategoryService categoryService, ActorService actorService) {
        this.movieService = movieService;
        this.languageService = languageService;
        this.categoryService = categoryService;
        this.actorService = actorService;
    }

    @RequestMapping("/movies")
    String movies(Model model, Sort sort, Pageable pageable, MovieSearchParams movieSearchParams,
                  @RequestParam Map<String, String> requestParams) {

        PaginationHelper paginationHelper = new PaginationHelper(sort, requestParams, movieViewModel.getSortItems());
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), paginationHelper.parseSortParams());

        //model.addAttribute("sort", beautifyOrder(sort));
        model.addAttribute("page", new PageWrapper<>(movieService.getMovies(movieSearchParams, pageRequest),
                                                     "/movies" + paginationHelper.getUrl()));
        model.addAttribute("sortItems", movieViewModel.getSortItems());
        model.addAttribute("yearMinMax", "1970;2016");
        model.addAttribute("lengthMinMax", "0;360");
        model.addAttribute("movieSearchParams", movieSearchParams);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("languages", languageService.findAllLanguages());

        requestParams.remove("sort");
        model.addAttribute("params", requestParams);

        return "pages/movieList";
    }

    @RequestMapping("/movie/{id}")
    String showMovie(Model model, @PathVariable("id") Long id) {
        model.addAttribute("movie", movieService.getMovie(id));
        return "pages/movie";
    }

    @RequestMapping("/movie")
    String showMovie() {
        return "redirect:movies";
    }

    @RequestMapping("/newmovie")
    String addMovie(Model model) {
        model.addAttribute(new Film());
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("languages", languageService.findAllLanguages());
        model.addAttribute("ratings", Rating.values());
        return "pages/movieAdd";
    }

    @RequestMapping(path = "/newmovie", method = RequestMethod.POST)
    String saveMovie(@ModelAttribute Film film, BindingResult bindingResult) {
        film.setPoster(movieViewModel.getPoster(film.getTitle()));
        Film savedFilmDto = movieService.save(film);
        return "redirect:movie/" + savedFilmDto.getId();
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "categories", new StringArrayToLongSetEditor(categoryService));
        binder.registerCustomEditor(Set.class, "actors", new StringArrayToLongSetEditor(actorService));
    }

}
