package ru.itis.pashin.website.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itis.pashin.website.common.model.catalogs.entity.CompanySizeType;


@Repository
public interface CompanySizeTypeRepository extends JpaRepository<CompanySizeType, Long>, JpaSpecificationExecutor<CompanySizeType> {
}
