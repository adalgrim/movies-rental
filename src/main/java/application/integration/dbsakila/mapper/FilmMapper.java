package application.integration.dbsakila.mapper;

import application.common.domain.Film;
import application.integration.dbsakila.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * FilmMapper
 * Created by Adam_Skowron on 10.08.2016.
 */
@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    Film FilmEntityToFilm(FilmEntity filmEntity);

    FilmEntity FilmToFilmEntity(Film film);
}
