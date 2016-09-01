package application.integration.dbsakila.mapper;

import application.common.domain.Film;
import application.integration.dbsakila.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * FilmMapper
 * Created by Adam_Skowron on 10.08.2016.
 */
@Mapper(uses = {LanguageMapper.class, CategoryMapper.class, ActorMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Mappings({
        @Mapping(target = "fullDescription", source = "filmTextEntity.description")
    })
    Film filmEntityToFilm(FilmEntity filmEntity);

    FilmEntity filmToFilmEntity(Film film);
}