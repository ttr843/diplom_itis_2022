package ru.itis.pashin.website.index.common.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.UUID;


@Data
@Document(indexName = "loan")
@Setting(settingPath = "/settings/lowercase-normalizer.json")
@Mapping(mappingPath = "/mappings/company-name.json")
public class LoanIndex {

    @Id
    private Long id;

    private UUID guid;

    @Field(type = FieldType.Keyword)
    private String companyName;

    @Field(type = FieldType.Keyword)
    private String companyInn;

    @Field(type = FieldType.Keyword)
    private String companyOgrn;

    @Field(type = FieldType.Keyword)
    private String companySizeTypeName;

    @Field(type = FieldType.Keyword)
    private String companySizeTypeCode;

    @Field(type = FieldType.Keyword)
    private String industryName;

    @Field(type = FieldType.Keyword)
    private String industryCode;

    private Long creditLimit;

    private Long amountOfLawsuits;

    private Long amountOfProceedings;

    private Long amountOfWorkers;

    private Long capital;

    private Long revenue;

    private Long netProfit;

    @Field(type = FieldType.Keyword)
    private String createdAt;

    private Integer mlStatusId;

    @Field(type = FieldType.Keyword)
    private String mlStatusName;

    @Field(type = FieldType.Keyword)
    private String createdByFullName;

    private Long createdById;

    private Boolean approvedByBank = false;

    @Field(type = FieldType.Keyword)
    private String approvedByBankAt;

    @Field(type = FieldType.Keyword)
    private String bankerFullName;

    private Long bankerId;

    @Field(type = FieldType.Keyword)
    private String bankerPosition;

    @Field(type = FieldType.Keyword)
    private String bankName;

    @Field(type = FieldType.Keyword)
    private String bankCode;
}
