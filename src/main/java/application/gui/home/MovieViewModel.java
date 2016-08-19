package application.gui.home;

import groovy.lang.Singleton;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Model View.
 * Created by Adam_Skowron on 17.08.2016.
 */
@Component
@Singleton
public class MovieViewModel {

    @SuppressWarnings("unchecked")
    private LinkedHashMap<String, String> sortItems = new LinkedHashMap() {{
        put("title_ASC", "by title, ascending");
        put("title_DESC", "by title, descending");
        put("length_ASC", "shortest first");
        put("length_DESC", "longest first");
    }};

    public Map<String, String> getSortItems() {
        return sortItems;
    }

}
