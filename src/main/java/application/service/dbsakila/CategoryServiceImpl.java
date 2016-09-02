package application.service.dbsakila;

import application.common.domain.Category;
import application.integration.dbsakila.dao.CategoryRepository;
import application.integration.dbsakila.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository
            .findAll()
            .stream()
            .map(CategoryMapper.INSTANCE::categoryEntityToCategory)
            .collect(Collectors.toList());
    }

    @Override
    public List<Category> findAll(Set<Long> ids) {
        return categoryRepository
            .findAll(ids)
            .stream()
            .map(CategoryMapper.INSTANCE::categoryEntityToCategory)
            .collect(Collectors.toList());
    }

    @Override
    public Category findOne(Long id) {
        return CategoryMapper.INSTANCE.categoryEntityToCategory(categoryRepository.findOne(id));
    }

}
