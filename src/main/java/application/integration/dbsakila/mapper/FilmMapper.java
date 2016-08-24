package application.integration.dbsakila.mapper;

import application.common.domain.Actor;
import application.common.domain.Category;
import application.common.domain.Film;
import application.common.domain.Language;
import application.integration.dbsakila.entity.ActorEntity;
import application.integration.dbsakila.entity.CategoryEntity;
import application.integration.dbsakila.entity.FilmEntity;
import application.integration.dbsakila.entity.LanguageEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.Set;

/**
 * FilmMapper
 * Created by Adam_Skowron on 10.08.2016.
 */
@Mapper(uses = {LanguageMapper.class, CategoryMapper.class, ActorMapper.class})
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    Film FilmEntityToFilm(FilmEntity filmEntity);

    FilmEntity FilmToFilmEntity(Film film);

}