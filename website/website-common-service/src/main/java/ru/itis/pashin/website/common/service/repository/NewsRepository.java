package ru.itis.pashin.website.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.pashin.website.common.model.system.entity.News;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE \"system\".news set deleted = true where id = :id")
    void deleteNews(@Param("id") Long id);

    Iterable<News> findAllByDeletedOrderByCreatedAtDesc(boolean deleted);
}
