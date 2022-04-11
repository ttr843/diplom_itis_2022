package ru.itis.pashin.website.common.model.confirmation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationDTO {

    private Long id;

    private UUID confirmCode;

    private LocalDateTime created;

    private boolean send;

    private LocalDateTime sendTime;

    private String email;
}
