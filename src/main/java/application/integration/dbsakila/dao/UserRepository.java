package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 *
 * Created by Adam_Skowron on 02.09.2016.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public UserEntity findByUserName(String username);
}
