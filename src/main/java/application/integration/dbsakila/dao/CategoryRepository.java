package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Category Repo
 * Created by Adam_Skowron on 22.08.2016.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
