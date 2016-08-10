package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FilmRepository.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

}
