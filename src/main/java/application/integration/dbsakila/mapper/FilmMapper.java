package application.integration.dbsakila.mapper;

import static fr.xebia.extras.selma.CollectionMappingStrategy.ALLOW_GETTER;

import application.common.domain.Film;
import application.integration.dbsakila.entity.FilmEntity;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;

/**
 * Film mapper.
 *
 * Created by Adam_Skowron on 10.08.2016.
 */
@Mapper(withIgnoreMissing = IgnoreMissing.ALL, withCollectionStrategy = ALLOW_GETTER)
public interface FilmMapper {

    /** Map FilmEntity to Film DTO **/
    Film toFilm(FilmEntity filmEntity);

    /** Map Film DTO to FilmEntity **/
    FilmEntity toFilmEntity(Film film);
}
