package ru.itis.pashin.website.common.model.service.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@RequiredArgsConstructor
@Getter
public enum ServiceErrorCode {

    ERROR_CODE_0000("Пользователь не найден"),
    ERROR_CODE_0001("Роль не найдена"),
    ERROR_CODE_0002("Ошибка при создании пользователя"),
    ERROR_CODE_0003("Ошибка при создании записи о подтверждении УЗ");


    private final String message;
}
