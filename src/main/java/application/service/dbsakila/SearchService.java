package application.service.dbsakila;

import java.util.List;
import java.util.Set;

/**
 * search service
 * Created by Adam_Skowron on 30.08.2016.
 */
public interface SearchService {

    List findAll(Set<Long> ids);
}
