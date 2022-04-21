package ru.itis.pashin.website.common.model.system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.pashin.website.common.model.system.dto.NewsDTO;
import ru.itis.pashin.website.common.model.system.entity.News;


@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "createdAt", expression = "java(news.getCreatedAt().format(ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT))")
    NewsDTO entityToDTO(News news);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.parse(newsDTO.getCreatedAt(),ru.itis.pashin.website.common.model.Util.DateFormatConstants.DATE_TIME_FORMAT))")
    News dtoToEntity(NewsDTO newsDTO);


}
