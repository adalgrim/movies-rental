package application.gui.home;

import application.common.domain.Actor;
import application.common.domain.RestIdNamePair;
import application.service.dbsakila.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Actor rest controller.
 *
 * Created by Adam_Skowron on 24.08.2016.
 */
@RestController
public class ActorRestController {

    private ActorService actorService;

    @Autowired
    public ActorRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    @RequestMapping(value = "/searchActors", produces = "application/json")
    public List<RestIdNamePair> searchActors(@RequestParam("name") String name) {
        List<RestIdNamePair> list = new ArrayList<>();

        if (name == null || name.length() < 3) {
            return list;
        }

        return actorService.searchActors(name)
            .stream()
            .map(actor -> {
                RestIdNamePair restIdNamePair = new RestIdNamePair();
                restIdNamePair.setId(actor.getId());
                restIdNamePair.setName(actor.getFirstname() + " " + actor.getLastname());
                return restIdNamePair;
            })
            .collect(Collectors.toList());
    }


}
