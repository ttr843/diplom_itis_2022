package ru.itis.pashin.website.common.model.loan.entity;

import lombok.*;
import ru.itis.pashin.website.common.model.annotation.FieldDescription;
import ru.itis.pashin.website.common.model.catalogs.entity.CompanySizeType;
import ru.itis.pashin.website.common.model.catalogs.entity.Industry;
import ru.itis.pashin.website.common.model.loan.enumeration.MlStatus;
import ru.itis.pashin.website.common.model.loan.util.LoanApplicationGuidGenerator;
import ru.itis.pashin.website.common.model.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({LoanApplicationGuidGenerator.class})
@Table(schema = "loan", name = "loan_application")
public class LoanApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID guid;

    @FieldDescription(description = "название компании")
    private String companyName;

    @FieldDescription(description = "ИНН компании")
    private String companyInn;

    @FieldDescription(description = "ОГРН компании")
    private String companyOgrn;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "industry_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldDescription(description = "Вид дейтельности, отрасль")
    private Industry industry;

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

    @FieldDescription(description = "Дата создания")
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @FieldDescription(description = "Статус обработки ML")
    private MlStatus mlStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_size_type_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldDescription(description = "Размер компании")
    private CompanySizeType companySizeType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User client;
}
