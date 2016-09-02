package application.gui.utils;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Adam_Skowron on 30.08.2016.
 */
public class PaginationHelper {

    private final Sort.Order defaultSortOrder = new Sort.Order(Sort.Direction.ASC, "title");

    private Sort sort;
    private Map<String, String> requestParams;
    private Map<String, String> availableSortItems;

    public PaginationHelper(Sort sort,
                            @RequestParam
                                Map<String, String> requestParams, Map<String, String> sortItems) {
        this.sort = sort;
        this.requestParams = requestParams;
        this.availableSortItems = sortItems;
    }

    public String getUrl() {
        List<String> url = new ArrayList<>();

        if (sort != null && !defaultSortOrder.toString().equalsIgnoreCase(sort.toString())) {
            url.add("sort=" + beautifyOrder(sort));
        }

        if (MapUtils.isNotEmpty(this.requestParams)) {
            url.addAll(this.requestParams.entrySet()
                           .stream()
                           .filter(stringStringEntry -> !"page".equals(stringStringEntry.getKey()))
                           .map(entry -> entry.getKey() + "=" + entry.getValue())
                           .collect(Collectors.toList()));
        }

        return CollectionUtils.isEmpty(url) ? "" : "?" + String.join("&", url);
    }

    public Sort parseSortParams() {
        List<Sort.Order> sortResult = new ArrayList<>();
        if (sort == null) {
            return null;
        }

        for (Sort.Order order : sort) {
            if (this.availableSortItems.containsKey(beautifyOrder(order))) {
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
