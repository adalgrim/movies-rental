package application.integration.dbsakila.mapper;

import application.common.domain.Language;
import application.integration.dbsakila.dao.LanguageRepository;
import application.integration.dbsakila.entity.LanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;

/**
 * Language mapper
 *
 * Created by Adam_Skowron on 22.08.2016.
 */
@Mapper
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    Language LanguageEntityToLanguage(LanguageEntity languageEntity);

    LanguageEntity LanguageEntityToLanguage(Language language);

}
