package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Language repo
 * Created by Adam_Skowron on 22.08.2016.
 */
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

}
