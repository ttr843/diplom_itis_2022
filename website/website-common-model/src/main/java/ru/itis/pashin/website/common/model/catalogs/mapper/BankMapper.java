package ru.itis.pashin.website.common.model.catalogs.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.catalogs.dto.BankDTO;
import ru.itis.pashin.website.common.model.catalogs.entity.Bank;


@Mapper(componentModel = "spring")
public interface BankMapper {

    BankDTO entityToDTO(Bank bank);

    Bank dtoToEntity(BankDTO bankDTO);
}
