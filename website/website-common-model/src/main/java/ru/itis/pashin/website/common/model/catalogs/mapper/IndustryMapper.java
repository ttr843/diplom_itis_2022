package ru.itis.pashin.website.common.model.catalogs.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.catalogs.dto.IndustryDTO;
import ru.itis.pashin.website.common.model.catalogs.entity.Industry;


@Mapper(componentModel = "spring")
public interface IndustryMapper {

    IndustryDTO entityToDTO(Industry industry);

    Industry dtoToEntity(IndustryDTO industryDTO);
}
