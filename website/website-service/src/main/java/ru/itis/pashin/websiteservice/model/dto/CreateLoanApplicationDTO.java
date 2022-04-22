package ru.itis.pashin.websiteservice.model.dto;

import lombok.*;
import ru.itis.pashin.website.common.model.annotation.FieldDescription;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateLoanApplicationDTO {

    @FieldDescription(description = "название компании")
    private String companyName;

    @FieldDescription(description = "ИНН компании")
    private String companyInn;

    @FieldDescription(description = "ОГРН компании")
    private String companyOgrn;

    @FieldDescription(description = "Вид дейтельности, отрасль")
    private String industryId;

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

    @FieldDescription(description = "Размер компании")
    private Long companySizeTypeId;

}
