package ru.itis.pashin.website.common.model.confirmation.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itis.pashin.website.common.model.confirmation.dto.ConfirmationDTO;
import ru.itis.pashin.website.common.model.confirmation.entity.Confirmation;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConfirmationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "confirmCode", source = "confirmCode")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "send", source = "send")
    @Mapping(target = "sendTime", source = "sendTime")
    @Mapping(target = "email", source = "email")
    ConfirmationDTO entityToDTO(Confirmation confirmation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "confirmCode", source = "confirmCode")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "send", source = "send")
    @Mapping(target = "sendTime", source = "sendTime")
    @Mapping(target = "email", source = "email")
    void update(@MappingTarget Confirmation entity, ConfirmationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "confirmCode", source = "confirmCode")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now(java.time.ZoneOffset.UTC))")
    @Mapping(target = "send", source = "send")
    @Mapping(target = "sendTime", source = "sendTime")
    @Mapping(target = "email", source = "email")
    Confirmation create(ConfirmationDTO dto);
}
