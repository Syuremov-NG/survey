package sng.survey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sng.survey.model.dto.type.TypeForResponseDto;
import sng.survey.model.entity.QuestionTypeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeForOptionMapper {
    TypeForResponseDto toDto(QuestionTypeEntity typeEntity);
}
