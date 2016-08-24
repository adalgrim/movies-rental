package application.service.dbsakila;

import application.common.domain.Language;
import application.integration.dbsakila.dao.LanguageRepository;
import application.integration.dbsakila.mapper.LanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Adam_Skowron on 23.08.2016.
 */
@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> findAllLanguages() {
        return languageRepository
            .findAll()
            .stream()
            .map(LanguageMapper.INSTANCE::LanguageEntityToLanguage)
            .collect(Collectors.toList());
    }

}
