package sng.survey.mapper;

import org.mapstruct.Mapper;
import sng.survey.model.dto.type.QuestionTypeDto;
import sng.survey.model.entity.QuestionTypeEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = TypeForOptionMapper.class)
public interface QuestionTypeMapper {
    QuestionTypeEntity toModel(QuestionTypeDto questionTypeDto);

    QuestionTypeDto toDto(QuestionTypeEntity questionTypeEntity);

    List<QuestionTypeDto> toListDto(List<QuestionTypeEntity> typeList);
}
