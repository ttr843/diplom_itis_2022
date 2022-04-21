package ru.itis.pashin.website.common.model.catalogs.dto;

import lombok.*;

import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankDTO {

    private Long id;

    private UUID guid;

    private String code;

    private String name;
}
