package application.gui.propertyeditor;

import application.service.dbsakila.SearchService;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * StringArrayToLongSetEditor class.
 *
 * Created by Adam_Skowron on 30.08.2016.
 */
public class StringArrayToLongSetEditor extends PropertyEditorSupport {

    private SearchService searchService;

    public StringArrayToLongSetEditor(SearchService searchService) {
        this.searchService = searchService;
    }

    @SuppressWarnings("unchecked")
    public void setValue(Object value) {
        if (value == null) {
            super.setValue(new HashSet<>());
        } else {
            if (value instanceof String) {
                HashSet set = new HashSet<>();
                set.add(value);
                super.setValue(set);
            } else {
                super.setValue(new HashSet<>(this.searchService.findAll(parseStringArrayToLongSet(value))));
            }
        }
    }

    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(text);
    }

    private Set<Long> parseStringArrayToLongSet(Object value) {
        return new HashSet<>(Arrays.stream((String[]) value).map(Long::parseLong).collect(Collectors.toList()));
    }
}
