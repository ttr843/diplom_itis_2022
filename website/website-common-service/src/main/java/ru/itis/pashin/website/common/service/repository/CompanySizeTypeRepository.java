package ru.itis.pashin.website.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itis.pashin.website.common.model.catalogs.entity.CompanySizeType;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Repository
public interface CompanySizeTypeRepository extends JpaRepository<CompanySizeType, Long>, JpaSpecificationExecutor<CompanySizeType> {
}
