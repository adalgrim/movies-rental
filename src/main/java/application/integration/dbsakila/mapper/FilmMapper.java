package application.integration.dbsakila.mapper;

import application.common.domain.Film;
import application.integration.dbsakila.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;

/**
 * FilmMapper
 * Created by Adam_Skowron on 10.08.2016.
 */
@Mapper
public interface FilmMapper extends Converter<FilmEntity, Film> {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    Film FilmEntityToFilm(FilmEntity filmEntity);

    FilmEntity FilmToFilmEntity(Film film);

    default Film convert(FilmEntity filmEntity) {
        return this.FilmEntityToFilm(filmEntity);
    }

}
