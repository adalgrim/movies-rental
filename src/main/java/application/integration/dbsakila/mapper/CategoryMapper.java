package application.integration.dbsakila.mapper;

import application.common.domain.Category;
import application.integration.dbsakila.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * Category Mapper
 * Created by Adam_Skowron on 22.08.2016.
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryEntityToCategory(CategoryEntity categoryEntity);

    Set<Category> categoryEntitySetToCategorySet(Set<CategoryEntity> categoryEntitySet);

    CategoryEntity categoryToCategoryEntity(Category category);

    Set<CategoryEntity> categorySetToCategoryEntitySet(Set<Category> categorySet);

}
