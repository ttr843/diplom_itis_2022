package ru.itis.pashin.website.common.model.user.util;

import org.springframework.stereotype.Component;
import ru.itis.pashin.website.common.model.user.entity.User;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Component
public class UserGuidGenerator {

    @PrePersist
    @PreUpdate
    public void prePersist(User user) {
        if (user.getGuid() != null) {
            return;
        }
        user.setGuid(UUID.randomUUID());
    }
}
