package ru.itis.pashin.website.common.model.user.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleDTO {

    private Long id;

    private String code;

    private String name;
}
