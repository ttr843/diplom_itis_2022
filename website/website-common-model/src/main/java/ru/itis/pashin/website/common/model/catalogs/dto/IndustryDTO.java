package ru.itis.pashin.website.common.model.catalogs.dto;

import lombok.*;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IndustryDTO {

    private Long id;

    private String code;

    private String name;
}
