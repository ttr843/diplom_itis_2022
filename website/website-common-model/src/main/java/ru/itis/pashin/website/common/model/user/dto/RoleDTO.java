package ru.itis.pashin.website.common.model.user.dto;

import lombok.Data;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
public class RoleDTO {

    private Long id;

    private String code;

    private String name;
}
