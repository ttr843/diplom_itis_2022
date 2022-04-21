package ru.itis.pashin.website.common.model.loan.util;

import org.springframework.stereotype.Component;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.user.entity.User;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Component
public class LoanApplicationGuidGenerator {

    @PrePersist
    @PreUpdate
    public void prePersist(LoanApplication loanApplication) {
        if (loanApplication.getGuid() != null) {
            return;
        }
        loanApplication.setGuid(UUID.randomUUID());
    }
}
