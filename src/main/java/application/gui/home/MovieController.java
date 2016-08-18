package application.gui.home;

import application.service.dbsakila.SakilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    public MovieViewModel movieViewModel;

    private final Sort.Order defaultSortOrder = new Sort.Order(Sort.Direction.ASC, "title");

    @RequestMapping("/movies")
    String movies(Model model, Pageable pageable, Sort sort) {

        sort = this.parseSortParams(sort);
        PageRequest pageRequest = new PageRequest(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            sort);

        String sortUrl = this.prepareSortUrl(sort);
        model.addAttribute("sort", beautifyOrder(sort));
        model.addAttribute("page", new PageWrapper<>(sakilaService.getMovies(pageRequest), "/movies" + sortUrl));
        model.addAttribute("sortItems", movieViewModel.getSortItems());
        return "pages/movieList";
    }

    private String prepareSortUrl(Sort sort) {
        String sortUrl = "";

        if (!defaultSortOrder.toString().equalsIgnoreCase(sort.toString())){
            sortUrl = "?sort=" + beautifyOrder(sort);
        }

        return sortUrl;
    }

    private Sort parseSortParams(Sort sortRequest) {
        List<Sort.Order> sortResult = new ArrayList<>();
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
        return orderOption.toString().replace(": ", "_");
    }

}
