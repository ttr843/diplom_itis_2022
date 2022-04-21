package ru.itis.pashin.website.common.model.exception.enumeration;

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
    ERROR_CODE_0003("Ошибка при создании записи о подтверждении УЗ"),
    ERROR_CODE_0004("Ошибка при создании новости"),
    ERROR_CODE_0005("Ошибка при разблокирование УЗ"),
    ERROR_CODE_0006("Ошибка при блокироваки УЗ"),
    ERROR_CODE_0007("Ошибка при удалении Новости"),
    ERROR_CODE_0008("Ошибка при создании заявки"),
    ERROR_CODE_0009("Указанная отрасль компании не найдена"),
    ERROR_CODE_0010("Указанный размер компании не найден"),
    ERROR_CODE_0011("Указанная заявка не найдена"),
    ERROR_CODE_0012("Ошибка при одобрении заявки"),
    ERROR_CODE_0013("Указанная заявка не одобрена системой"),
    ERROR_CODE_0014("Указанный банк не найден");

    private final String message;
}
