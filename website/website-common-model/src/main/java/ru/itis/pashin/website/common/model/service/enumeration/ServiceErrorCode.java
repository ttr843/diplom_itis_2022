package ru.itis.pashin.website.common.model.service.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@RequiredArgsConstructor
@Getter
public enum ServiceErrorCode {

    ERROR_CODE_0000("Пользователь не найден");


    private final String message;
}
