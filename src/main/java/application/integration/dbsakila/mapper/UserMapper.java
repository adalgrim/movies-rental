package application.integration.dbsakila.mapper;

import application.common.domain.User;
import application.integration.dbsakila.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Adam_Skowron on 02.09.2016.
 */
@Mapper
public interface UserMapper  {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(User user);
}
