package ru.itis.pashin.website.common.model.catalogs.dto;

import lombok.*;


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
