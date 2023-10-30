package sng.survey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sng.survey.model.dto.relation.QuestionOptionMappingDto;
import sng.survey.model.entity.QuestionOptionMapping;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface QuestionOptionMappingMapper {
    QuestionOptionMapping toModel(QuestionOptionMappingDto mappingDto);

    QuestionOptionMappingDto toDto(QuestionOptionMapping mapping);

    List<QuestionOptionMappingDto> toListDto(List<QuestionOptionMapping> mappingList);

    List<QuestionOptionMapping> toListModel(List<QuestionOptionMappingDto> mappingDtoList);

}
