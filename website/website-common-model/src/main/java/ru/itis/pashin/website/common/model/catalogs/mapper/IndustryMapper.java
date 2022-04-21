package ru.itis.pashin.website.common.model.catalogs.mapper;

import org.mapstruct.Mapper;
import ru.itis.pashin.website.common.model.catalogs.dto.IndustryDTO;
import ru.itis.pashin.website.common.model.catalogs.entity.Industry;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Mapper(componentModel = "spring")
public interface IndustryMapper {

    IndustryDTO entityToDTO(Industry industry);

    Industry dtoToEntity(IndustryDTO industryDTO);
}
