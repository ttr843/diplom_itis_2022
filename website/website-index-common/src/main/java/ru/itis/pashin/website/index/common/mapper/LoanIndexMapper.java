package ru.itis.pashin.website.index.common.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.user.dto.UserDTO;
import ru.itis.pashin.website.index.common.index.LoanIndex;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface LoanIndexMapper {

    @Mapping(target = "companySizeTypeName", source = "companySizeType.name")
    @Mapping(target = "companySizeTypeCode", source = "companySizeType.code")
    @Mapping(target = "industryName", source = "industry.name")
    @Mapping(target = "industryCode", source = "industry.code")
    @Mapping(target = "mlStatusId", expression = "java(dto.getMlStatus().getId())")
    @Mapping(target = "mlStatusName", expression = "java(dto.getMlStatus().getName())")
    @Mapping(target = "createdByFullName", expression = "java(getFullNameByUser(dto.getClient()))")
    @Mapping(target = "createdById", source = "client.id")
    @Mapping(target = "bankerPosition", source = "banker.position")
    @Mapping(target = "bankerFullName", expression = "java(getFullNameByUser(dto.getBanker()))")
    @Mapping(target = "bankerId", source = "banker.id")
    @Mapping(target = "bankerEmail", source = "banker.email")
    @Mapping(target = "bankName", source = "banker.bank.name")
    @Mapping(target = "bankCode", source = "banker.bank.code")
    LoanIndex dtoToIndex(LoanApplicationDTO dto);

    default String getFullNameByUser(UserDTO dto) {
        if (Objects.nonNull(dto)) {
            return dto.getFirstName() +
                    StringUtils.SPACE +
                    dto.getMiddleName() +
                    StringUtils.SPACE +
                    dto.getLastName();
        } else {
            return StringUtils.EMPTY;
        }
    }
}
