package ru.itis.pashin.website.common.model.loan.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum MlStatus {
    NEW(1,"Новый"),
    IN_PROGRESS(2,"В процессе"),
    ERROR(3,"Завершено с ошибкой"),
    DONE_NOT_APPROVED(4,"Завершено, отказано системой"),
    DONE_APPROVED(5,"Завершено, разрешено системой");

    private int id;
    private String name;
}
