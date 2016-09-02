package application.integration.dbsakila.mapper;

import application.common.domain.Actor;
import application.integration.dbsakila.entity.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * Created by Adam_Skowron on 22.08.2016.
 */
@Mapper
public interface ActorMapper {

    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

    Actor actorEntityToActor(ActorEntity categoryEntity);

    Set<Actor> actorEntitySetToActorSet(Set<ActorEntity> actorEntitySet);

    ActorEntity actorToActorEntity(Actor actor);

    Set<ActorEntity> actorSetToActorEntitySet(Set<Actor> actorSet);

}
