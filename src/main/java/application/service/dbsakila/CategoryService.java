package application.service.dbsakila;

import application.common.domain.Category;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
public interface CategoryService extends SearchService {

    /**
     * Fetch all categories
     *
     * @return list of categories
     */
    @Cacheable("categories")
    List<Category> findAllCategories();

    /**
     * Fetch all categories by ids
     * @param ids set of ids
     * @return list of categories
     */
    List<Category> findAll(Set<Long> ids);

    @Cacheable("category")
    Category findOne(Long id);

}
