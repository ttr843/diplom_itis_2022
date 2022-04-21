package ru.itis.pashin.website.common.model.loan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.pashin.website.common.model.catalogs.mapper.CompanySizeTypeMapper;
import ru.itis.pashin.website.common.model.catalogs.mapper.IndustryMapper;
import ru.itis.pashin.website.common.model.loan.dto.LoanApplicationDTO;
import ru.itis.pashin.website.common.model.loan.entity.LoanApplication;
import ru.itis.pashin.website.common.model.user.mapper.UserMapper;


@Mapper(componentModel = "spring")
public abstract class LoanApplicationMapper {

    @Autowired
    public UserMapper userMapper;
    @Autowired
    public CompanySizeTypeMapper companySizeTypeMapper;
    @Autowired
    public IndustryMapper industryMapper;

    @Mapping(target = "client", expression = "java(userMapper.entityToDTO(loanApplication.getClient()))")
    @Mapping(target = "banker", expression = "java(userMapper.entityToDTO(loanApplication.getBanker()))")
    @Mapping(target = "companySizeType", expression = "java(companySizeTypeMapper.entityToDTO(loanApplication.getCompanySizeType()))")
    @Mapping(target = "industry", expression = "java(industryMapper.entityToDTO(loanApplication.getIndustry()))")
    @Mapping(target = "createdAt", expression = "java(loanApplication.getCreatedAt().format(ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT))")
    @Mapping(target = "approvedByBankAt", expression = "java(loanApplication.getApprovedByBankAt() != null ? loanApplication.getApprovedByBankAt().format(ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT) : null)")
    public abstract LoanApplicationDTO entityToDTO(LoanApplication loanApplication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", expression = "java(userMapper.dtoToEntity(loanApplicationDTO.getClient()))")
    @Mapping(target = "banker", expression = "java(userMapper.dtoToEntity(loanApplicationDTO.getBanker()))")
    @Mapping(target = "companySizeType", expression = "java(companySizeTypeMapper.dtoToEntity(loanApplicationDTO.getCompanySizeType()))")
    @Mapping(target = "industry", expression = "java(industryMapper.dtoToEntity(loanApplicationDTO.getIndustry()))")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.parse(loanApplicationDTO.getCreatedAt(),ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT))")
    @Mapping(target = "approvedByBankAt", expression = "java(loanApplicationDTO.getApprovedByBankAt() != null ? java.time.LocalDateTime.parse(loanApplicationDTO.getApprovedByBankAt(),ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT) : null)")
    public abstract LoanApplication createLoan(LoanApplicationDTO loanApplicationDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", expression = "java(userMapper.dtoToEntity(loanApplicationDTO.getClient()))")
    @Mapping(target = "banker", expression = "java(userMapper.dtoToEntity(loanApplicationDTO.getBanker()))")
    @Mapping(target = "companySizeType", expression = "java(companySizeTypeMapper.dtoToEntity(loanApplicationDTO.getCompanySizeType()))")
    @Mapping(target = "industry", expression = "java(industryMapper.dtoToEntity(loanApplicationDTO.getIndustry()))")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.parse(loanApplicationDTO.getCreatedAt(),ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT))")
    @Mapping(target = "approvedByBankAt", expression = "java(loanApplicationDTO.getApprovedByBankAt() != null ? java.time.LocalDateTime.parse(loanApplicationDTO.getApprovedByBankAt(),ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT) : null)")
    public abstract void updateLoan(@MappingTarget LoanApplication loanApplication, LoanApplicationDTO loanApplicationDTO);
}
