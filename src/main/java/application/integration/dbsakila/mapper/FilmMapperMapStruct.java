package application.integration.dbsakila.mapper;

import application.common.domain.Film;
import application.integration.dbsakila.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * mapper2
 * Created by Adam_Skowron on 10.08.2016.
 */
@Mapper
public interface FilmMapperMapStruct {

    FilmMapperMapStruct INSTANCE = Mappers.getMapper(FilmMapperMapStruct.class);

    Film FilmEntityToFilm(FilmEntity filmEntity);

    FilmEntity FilmToFilmEntity(Film film);
}
