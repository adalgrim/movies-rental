package application.integration.dbsakila.dao;

import application.MoviesRentalTest;
import application.common.domain.FilmSearchResult;
import application.integration.dbsakila.entity.ActorEntity;
import application.integration.dbsakila.entity.CategoryEntity;
import application.integration.dbsakila.entity.FilmEntity;
import application.integration.dbsakila.entity.LanguageEntity;
import application.integration.dbsakila.entity.builder.ActorEntityBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FilmRepositoryTest.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class FilmRepositoryTest extends MoviesRentalTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ActorRepository actorRepository;

    private static boolean isInitialized = false;

    @Before
    public void setUp() throws Exception {

        if (isInitialized) {
            return;
        }

        System.out.println("Initilizing db:");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        categoryEntity.setName("Action");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2);
        categoryEntity2.setName("Drama");

        categoryRepository.save(categoryEntity);
        categoryRepository.save(categoryEntity2);

        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(1);
        languageEntity.setName("English");

        LanguageEntity languageEntity2 = new LanguageEntity();
        languageEntity2.setId(2);
        languageEntity2.setName("Polish");

        languageRepository.save(languageEntity);
        languageRepository.save(languageEntity2);

        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setTitle("AAA");
        filmEntity.setLength(100);
        filmEntity.setLanguage(languageEntity); // English
        filmEntity.setReleaseYear(2013);
        filmEntity.setCategories(new HashSet<CategoryEntity>() {{
            add(categoryEntity);
        }}); // Action

        FilmEntity filmEntity2 = new FilmEntity();
        filmEntity2.setTitle("BBB");
        filmEntity2.setLength(180);
        filmEntity2.setLanguage(languageEntity); // English
        filmEntity2.setReleaseYear(2014);
        filmEntity2.setCategories(new HashSet<CategoryEntity>() {{
            add(categoryEntity2);
        }}); // Drama

        FilmEntity filmEntity3 = new FilmEntity();
        filmEntity3.setTitle("ZZZ");
        filmEntity3.setLength(90);
        filmEntity3.setLanguage(languageEntity2); // Polish
        filmEntity3.setReleaseYear(2015);
        filmEntity3.setCategories(new HashSet<CategoryEntity>() {{
            add(categoryEntity2);
        }}); // Drama

        ActorEntity actorEntity = ActorEntityBuilder.anActorEntity()
                .withFirstname("Matt").withLastname("Damon")
                .withFilms(new HashSet<FilmEntity>() {{
                    add(filmEntity);
                }}).build();

        ActorEntity actorEntity2 = ActorEntityBuilder.anActorEntity()
                .withFirstname("Judy").withLastname("Foster")
                .withFilms(new HashSet<FilmEntity>() {{
                    add(filmEntity);
                }}).build();

        actorRepository.save(actorEntity);
        actorRepository.save(actorEntity2);

        filmEntity.setActors(new HashSet<ActorEntity>() {{
            add(actorEntity);
        }});
        filmEntity2.setActors(new HashSet<ActorEntity>() {{
            add(actorEntity2);
        }});

        filmRepository.save(filmEntity);
        filmRepository.save(filmEntity2);
        filmRepository.save(filmEntity3);

        isInitialized = true;
    }

    @Test
    public void testGetMoviesCount() throws Exception {
        long moviesCount = filmRepository.count();
        assertThat(moviesCount).isEqualTo(3);
    }

    @Test
    public void testGetMovies() throws Exception {
        Page<FilmEntity> filmEntityPage = filmRepository.findAll(new PageRequest(0, 10));
        assertThat(filmEntityPage).isNotEmpty().size().isEqualTo(3);
        assertThat(filmEntityPage).extracting("title").contains("AAA", "BBB").doesNotContain("CCC");
    }

    @Test
    public void testGetMoviesPageable() throws Exception {
        Page<FilmEntity> filmEntityPage = filmRepository.findAll(new PageRequest(0, 1));
        Page<FilmEntity> filmEntityPage2 = filmRepository.findAll(new PageRequest(1, 1));

        assertThat(filmEntityPage).isNotEmpty().size().isEqualTo(1);
        assertThat(filmEntityPage).extracting("title").contains("AAA").doesNotContain("BBB", "ZZZ");

        assertThat(filmEntityPage2).isNotEmpty().size().isEqualTo(1);
        assertThat(filmEntityPage2).extracting("title").contains("BBB").doesNotContain("AAA", "ZZZ");
    }

    @Test
    public void testSortMovies() {
        Page<FilmEntity> filmEntityPageSortedByTitleAsc = filmRepository.findAll(new PageRequest(0, 1, new Sort(new Sort.Order(
                Sort.Direction.ASC, "title"))));
        Page<FilmEntity> filmEntityPageSortedByTitleDesc = filmRepository.findAll(new PageRequest(0, 1, new Sort(new Sort.Order(
                Sort.Direction.DESC, "title"))));
        Page<FilmEntity> filmEntityPageSortedByLengthAsc = filmRepository.findAll(new PageRequest(0, 1, new Sort(new Sort.Order(
                Sort.Direction.ASC, "length"))));
        Page<FilmEntity> filmEntityPageSortedByLengthDesc = filmRepository.findAll(new PageRequest(0, 1, new Sort(new Sort.Order(
                Sort.Direction.DESC, "length"))));

        assertThat(filmEntityPageSortedByTitleAsc).isNotEmpty().size().isEqualTo(1);
        assertThat(filmEntityPageSortedByTitleDesc).isNotEmpty().size().isEqualTo(1);
        assertThat(filmEntityPageSortedByLengthAsc).isNotEmpty().size().isEqualTo(1);
        assertThat(filmEntityPageSortedByLengthDesc).isNotEmpty().size().isEqualTo(1);

        assertThat(filmEntityPageSortedByTitleAsc).extracting("title").contains("AAA");
        assertThat(filmEntityPageSortedByTitleDesc).extracting("title").contains("ZZZ");
        assertThat(filmEntityPageSortedByLengthAsc).extracting("title").contains("ZZZ");
        assertThat(filmEntityPageSortedByLengthDesc).extracting("title").contains("BBB");
    }

    @Test
    public void testSearchMoviesByTitle() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll("%Z%", null, 0, 0, 0, 0, 0, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(1);
        assertThat(filmSearchResults).extracting("title").contains("ZZZ");
    }

    @Test
    public void testSearchMoviesByActor() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll(null, "%Damon Matt%", 0, 0, 0, 0, 0, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(1);
        assertThat(filmSearchResults).extracting("title").contains("AAA");
    }

    @Test
    public void testSearchMoviesByLength() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll(null, null, 160, 0, 0, 0, 0, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(1);
        assertThat(filmSearchResults).extracting("title").contains("BBB");

        Page<FilmSearchResult> filmSearchResults2 = filmRepository.searchAll(null, null, 0, 110, 0, 0, 0, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults2).isNotEmpty().size().isEqualTo(2);
        assertThat(filmSearchResults2).extracting("title").doesNotContain("BBB");
    }

    @Test
    public void testSearchMoviesByYear() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll(null, null, 0, 0, 2015, 0, 0, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(1);
        assertThat(filmSearchResults).extracting("title").contains("ZZZ");

        Page<FilmSearchResult> filmSearchResults2 = filmRepository.searchAll(null, null, 0, 0, 0, 2014, 0, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults2).isNotEmpty().size().isEqualTo(2);
        assertThat(filmSearchResults2).extracting("title").doesNotContain("ZZZ");
    }

    @Test
    public void testSearchMoviesByLanguage() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll(null, null, 0, 0, 0, 0, 2, 0, new PageRequest(0, 5));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(1);
        assertThat(filmSearchResults).extracting("title").contains("ZZZ");
    }

    @Test
    public void testSearchMoviesByCategory() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll(null, null, 0, 0, 0, 0, 0, 2, new PageRequest(0, 5));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(2);
        assertThat(filmSearchResults).extracting("title").doesNotContain("AAA");
    }

    @Test
    public void testSearchMoviesByManyParams() {
        Page<FilmSearchResult> filmSearchResults = filmRepository.searchAll("%B%", "%Judy%", 170, 190, 2014, 2014, 1, 2, new PageRequest(0, 1));

        assertThat(filmSearchResults).isNotEmpty().size().isEqualTo(1);
        assertThat(filmSearchResults).extracting("title").contains("BBB");
    }

}