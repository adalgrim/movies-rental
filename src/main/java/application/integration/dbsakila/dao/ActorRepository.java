package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

    @Query("SELECT a FROM ActorEntity a "
           + " WHERE LOWER(a.firstname) LIKE LOWER(:name) "
           + "      OR LOWER(a.lastname) LIKE LOWER(:name)"
           + "      OR LOWER(concat(a.firstname, ' ', a.lastname)) LIKE LOWER(:name) "
           + "      OR LOWER(concat(a.lastname, ' ', a.firstname)) LIKE LOWER(:name) "
           + " ")
    List<ActorEntity> searchAll(@Param("name") String name);

}
