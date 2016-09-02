package application.gui.home;

import groovy.lang.Singleton;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        }
    };

    public Map<String, String> getSortItems() {
        return sortItems;
    }

    @SuppressWarnings("unchecked")
    public String getPoster(String title) {
        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, String>
            hashMap =
            restTemplate.getForObject("http://www.omdbapi.com/?t=" + title.toLowerCase(), HashMap.class);
        if (hashMap.containsKey("Poster") && !"N/A".equalsIgnoreCase(hashMap.get("Poster"))) {
            return hashMap.get("Poster");
        }

        String[] titleParts = title.split(" ");
        for (String part : titleParts) {
            hashMap = restTemplate.getForObject("http://www.omdbapi.com/?t=" + part.toLowerCase(), HashMap.class);
            if (hashMap.containsKey("Poster") && !"N/A".equalsIgnoreCase(hashMap.get("Poster"))) {
                return hashMap.get("Poster");
            }
        }
        return "";
    }

}
