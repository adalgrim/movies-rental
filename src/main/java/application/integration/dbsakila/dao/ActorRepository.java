package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

}
