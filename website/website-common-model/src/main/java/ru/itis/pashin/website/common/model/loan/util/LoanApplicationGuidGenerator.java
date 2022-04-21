package ru.itis.pashin.website.common.model.loan.util;

import org.springframework.stereotype.Component;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;


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
