package sng.survey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sng.survey.model.dto.question.QuestionDto;
import sng.survey.model.entity.QuestionEntity;
import sng.survey.service.QuestionTypeService;

import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionTypeService.class})
public interface QuestionMapper {

    @Mapping(target = "type", source = "typeId")
    QuestionEntity toModel(QuestionDto questionDto);

    @Mapping(target = "typeId", source = "type.id")
    @Mapping(target = "childId", source = "defaultChild.id")
    QuestionDto toDto(QuestionEntity questionEntity);

    List<QuestionDto> toListDto(List<QuestionEntity> questionList);
}
