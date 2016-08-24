package application.service.dbsakila;

import application.common.domain.Language;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
public interface LanguageService {

    /**
     * Fetch all languages
     *
     * @return list of languages
     */
    @Cacheable("languages")
    List<Language> findAllLanguages();

}
