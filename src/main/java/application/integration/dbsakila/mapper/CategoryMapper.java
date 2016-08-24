package application.integration.dbsakila.mapper;

import application.common.domain.Category;
import application.integration.dbsakila.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * Category Mapper
 * Created by Adam_Skowron on 22.08.2016.
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category CategoryEntityToCategory(CategoryEntity categoryEntity);

    Set<Category> CategoryEntitySetToCategorySet(Set<CategoryEntity> categoryEntitySet);

    CategoryEntity CategoryToCategoryEntity(Category category);

    Set<CategoryEntity> CategorySetToCategoryEntitySet(Set<Category> categorySet);

}
