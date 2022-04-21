package ru.itis.pashin.website.common.model.loan.dto;

import lombok.*;
import ru.itis.pashin.website.common.model.annotation.FieldDescription;
import ru.itis.pashin.website.common.model.catalogs.dto.CompanySizeTypeDTO;
import ru.itis.pashin.website.common.model.catalogs.dto.IndustryDTO;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;

import java.time.LocalDateTime;
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
public class LoanApplicationDTO {

    private Long id;

    private UUID guid;

    @FieldDescription(description = "Название компании")
    private String companyName;

    @FieldDescription(description = "ИНН компании")
    private String companyInn;

    @FieldDescription(description = "ОГРН компании")
    private String companyOgrn;

    @FieldDescription(description = "Вид дейтельности, отрасль")
    private IndustryDTO industry;

    @FieldDescription(description = "Кредитный лимит, RUB")
    private Long creditLimit;

    @FieldDescription(description = "Сумма незавершенных исков в роли ответчика, RUB")
    private Long amountOfLawsuits;

    @FieldDescription(description = "Сумма исполнительных производств, RUB")
    private Long amountOfProceedings;

    @FieldDescription(description = "Среднесписочная численность работников")
    private Long amountOfWorkers;

    @FieldDescription(description = "Уставный капитал, RUB")
    private Long capital;

    @FieldDescription(description = "Выручка, RUB")
    private Long revenue;

    @FieldDescription(description = "Чистая прибыль (убыток), RUB")
    private Long netProfit;

    private String createdAt;

    private MlStatus mlStatus;

    @FieldDescription(description = "Размер компании")
    private CompanySizeTypeDTO companySizeType;

    private UserDTO client;
}