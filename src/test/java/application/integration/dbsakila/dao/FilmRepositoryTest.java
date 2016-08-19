package application.integration.dbsakila.dao;

import static org.assertj.core.api.Assertions.assertThat;

import application.MoviesRentalTest;
import application.integration.dbsakila.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * FilmRepositoryTest.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public class FilmRepositoryTest extends MoviesRentalTest {

    @Autowired
    private FilmRepository filmRepository;

    @BeforeClass
    public void setUp() throws Exception {

        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setTitle("AAA");
        filmEntity.setLength(100);

        FilmEntity filmEntity2 = new FilmEntity();
        filmEntity2.setTitle("BBB");
        filmEntity2.setLength(180);

        FilmEntity filmEntity3 = new FilmEntity();
        filmEntity3.setTitle("ZZZ");
        filmEntity3.setLength(90);

        filmRepository.save(filmEntity);
        filmRepository.save(filmEntity2);
        filmRepository.save(filmEntity3);
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



}