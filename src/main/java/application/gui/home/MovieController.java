package application.gui.home;

import application.common.domain.MovieSearchParams;
import application.service.dbsakila.CategoryService;
import application.service.dbsakila.LanguageService;
import application.service.dbsakila.MovieService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    public MovieController(MovieService movieService, LanguageService languageService,
                           CategoryService categoryService) {
        this.movieService = movieService;
        this.languageService = languageService;
        this.categoryService = categoryService;
    }

    @Autowired
    public MovieViewModel movieViewModel;

    private final Sort.Order defaultSortOrder = new Sort.Order(Sort.Direction.ASC, "title");

    @RequestMapping("/movies")
    String movies(Model model, Pageable pageable, Sort sort, MovieSearchParams movieSearchParams,
                  @RequestParam
                      Map<String, String> requestParams) {

        sort = this.parseSortParams(sort);
        PageRequest pageRequest = new PageRequest(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            sort);

        String paginationUrl = this.preparePaginationUrl(sort, requestParams);
        model.addAttribute("sort", beautifyOrder(sort));
        model.addAttribute("page", new PageWrapper<>(movieService.getMovies(movieSearchParams, pageRequest),
                                                     "/movies" + paginationUrl));
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

    private String preparePaginationUrl(Sort sort, Map<String, String> requestParams) {
        List<String> url = new ArrayList<>();

        if (sort != null && !defaultSortOrder.toString().equalsIgnoreCase(sort.toString())) {
            url.add("sort=" + beautifyOrder(sort));
        }

        if (MapUtils.isNotEmpty(requestParams)) {
            url.addAll(requestParams.entrySet()
                           .stream()
                           .filter(stringStringEntry -> !"page".equals(stringStringEntry.getKey()))
                           .map(entry -> entry.getKey() + "=" + entry.getValue())
                           .collect(Collectors.toList()));
        }

        return CollectionUtils.isEmpty(url) ? "" : "?" + String.join("&", url);
    }

    private Sort parseSortParams(Sort sortRequest) {
        List<Sort.Order> sortResult = new ArrayList<>();
        if (sortRequest == null) {
            return null;
        }

        for (Sort.Order order : sortRequest) {
            if (movieViewModel.getSortItems().containsKey(beautifyOrder(order))) {
                sortResult.add(order);
            }
        }
        if (CollectionUtils.isEmpty(sortResult)) {
            sortResult.add(defaultSortOrder);
        }

        return new Sort(sortResult);
    }

    private String beautifyOrder(Object orderOption) {
        return (orderOption == null) ? null : orderOption.toString().replace(": ", "_");
    }

}
