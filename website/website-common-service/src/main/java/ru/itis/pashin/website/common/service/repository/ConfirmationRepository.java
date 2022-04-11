package ru.itis.pashin.website.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itis.pashin.website.common.model.confirmation.entity.Confirmation;

import java.util.Optional;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long>, JpaSpecificationExecutor<Confirmation> {

    Optional<Confirmation> findConfirmationByConfirmCode(String confirmCode);

    Iterable<Confirmation> findAllByIsSendOrderByCreatedAsc(boolean isSend);
}
