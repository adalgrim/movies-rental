package application.integration.dbsakila.dao;

import application.integration.dbsakila.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * UserRolesRepository
 *
 * Created by Adam_Skowron on 02.09.2016.
 */
public interface UserRolesRepository extends CrudRepository<UserRoleEntity, Long> {

    @Query("select a.role from UserRoleEntity a, UserEntity b where b.userName=?1 and a.userid=b.userId")
    public List<String> findRoleByUserName(String username);
}
