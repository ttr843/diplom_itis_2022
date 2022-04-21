package ru.itis.pashin.website.common.model.catalogs.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.catalogs.dto.CompanySizeTypeDTO;
import ru.itis.pashin.website.common.model.catalogs.entity.CompanySizeType;

@Mapper(componentModel = "spring")
public interface CompanySizeTypeMapper {

    CompanySizeTypeDTO entityToDTO(CompanySizeType companySizeType);

    CompanySizeType dtoToEntity(CompanySizeTypeDTO companySizeTypeDTO);
}
