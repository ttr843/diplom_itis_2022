package ru.itis.pashin.website.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long>, JpaSpecificationExecutor<LoanApplication> {

    Iterable<LoanApplication> findAllByClientIdOrderByCreatedAt(Long clientId);

    Iterable<LoanApplication> findAllByMlStatusOrderByCreatedAt(MlStatus mlStatus);

    @Modifying
    @Transactional
    @Query("UPDATE LoanApplication loan SET loan.mlStatus = :status WHERE loan.id = :id")
    void setStatus(@Param("id") long id, @Param("status") MlStatus status);
}