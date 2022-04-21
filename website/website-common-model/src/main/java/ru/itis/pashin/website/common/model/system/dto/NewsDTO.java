package ru.itis.pashin.website.common.model.system.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewsDTO {

    private Long id;

    private String title;

    private String text;

    private boolean deleted;

    private String createdAt;
}
