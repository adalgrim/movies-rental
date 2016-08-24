package application.service.dbsakila;

import application.common.domain.Category;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
public interface CategoryService {

    /**
     * Fetch all categories
     *
     * @return list of categories
     */
    @Cacheable("categories")
    List<Category> findAllCategories();

}
