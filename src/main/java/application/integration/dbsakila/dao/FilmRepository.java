package application.integration.dbsakila.dao;

import application.common.domain.FilmSearchResult;
import application.integration.dbsakila.entity.FilmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * FilmRepository.
 *
 * Created by Adam_Skowron on 09.08.2016.
 */
public interface FilmRepository extends JpaRepository<FilmEntity, Long>, JpaSpecificationExecutor<FilmEntity> {

    @Query("SELECT "
           + " new application.common.domain.FilmSearchResult(f.id, f.title, f.description, f.releaseYear, f.length, f.rating, f.poster) "
           + " FROM FilmEntity f "
           + " LEFT JOIN f.actors a "
           + " LEFT JOIN f.categories c "
           + " WHERE "
           + " (:title IS NULL OR LOWER(f.title) LIKE LOWER(:title)) "
           + " AND (:actor_name IS NULL "
           + "      OR LOWER(a.firstname) LIKE LOWER(:actor_name) "
           + "      OR LOWER(a.lastname) LIKE LOWER(:actor_name)"
           + "      OR LOWER(concat(a.firstname, ' ', a.lastname)) LIKE LOWER(:actor_name) "
           + "      OR LOWER(concat(a.lastname, ' ', a.firstname)) LIKE LOWER(:actor_name) "
           + " ) "
           + " AND (:lengthFrom IS NULL OR :lengthFrom = 0 OR f.length >= :lengthFrom) "
           + " AND (:lengthTo IS NULL OR :lengthTo = 0 OR f.length <= :lengthTo) "
           + " AND (:yearFrom IS NULL OR :yearFrom = 0 OR f.releaseYear >= :yearFrom) "
           + " AND (:yearTo IS NULL OR :yearTo = 0 OR f.releaseYear <= :yearTo) "
           + " AND (:language = 0 OR f.language = :language) "
           + " AND (:category = 0 OR c.id = :category)"
           + " GROUP BY f.id "
           + " ")
    Page<FilmSearchResult> searchAll(
        @Param("title") String title,
        @Param("actor_name") String actorName,
        @Param("lengthFrom") Integer lengthFrom,
        @Param("lengthTo") Integer lengthTo,
        @Param("yearFrom") Integer yearFrom,
        @Param("yearTo") Integer yearTo,
        @Param("language") int language,
        @Param("category") int category,
        Pageable pageable);

}
