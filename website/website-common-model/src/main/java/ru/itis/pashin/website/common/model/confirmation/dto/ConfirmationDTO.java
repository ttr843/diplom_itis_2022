package ru.itis.pashin.website.common.model.confirmation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


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
