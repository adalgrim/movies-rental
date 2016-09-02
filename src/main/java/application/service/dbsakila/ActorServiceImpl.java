package application.service.dbsakila;

import application.common.domain.Actor;
import application.integration.dbsakila.dao.ActorRepository;
import application.integration.dbsakila.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Adam_Skowron on 24.08.2016.
 */
@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> searchActors(String name) {
        return actorRepository
            .searchAll("%" + name + "%")
            .stream()
            .map(ActorMapper.INSTANCE::actorEntityToActor)
            .collect(Collectors.toList());
    }

    @Override
    public List<Actor> findAll(Set<Long> actorIds) {
        return actorRepository
            .findAll(actorIds)
            .stream()
            .map(ActorMapper.INSTANCE::actorEntityToActor)
            .collect(Collectors.toList());
    }

    @Override
    public Actor findOne(long id) {
        return ActorMapper.INSTANCE.actorEntityToActor(actorRepository.findOne(id));
    }
}
