package sng.survey.mapper;

import org.mapstruct.Mapper;
import sng.survey.model.dto.option.OptionDto;
import sng.survey.model.entity.OptionEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = TypeForOptionMapper.class)
public interface OptionMapper {
    OptionEntity toModel(OptionDto optionDto);

    OptionDto toDto(OptionEntity optionEntity);

    List<OptionDto> toListDto(List<OptionEntity> optionList);
}
