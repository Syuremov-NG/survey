package sng.survey.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sng.survey.exception.NotFoundException;
import sng.survey.mapper.QuestionOptionMappingMapper;
import sng.survey.model.dto.option.OptionRelationDto;
import sng.survey.model.entity.OptionEntity;
import sng.survey.model.entity.QuestionEntity;
import sng.survey.model.entity.QuestionOptionMapping;
import sng.survey.repository.QuestionOptionMappingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionOptionMappingService {

    final private QuestionOptionMappingRepository mappingRepository;
    final private QuestionOptionMappingMapper mappingMapper;
    final private OptionService optionService;
    private Map<Long, List<QuestionOptionMapping>> mappingList = new HashMap<>();

    public void save(
            QuestionEntity parentQuestion,
            OptionEntity option,
            QuestionEntity childQuestion
    ) {
        QuestionOptionMapping mapping = QuestionOptionMapping.builder()
                .childQuestion(childQuestion)
                .parentQuestion(parentQuestion)
                .option(option)
                .build();

        this.updateMapping(mapping);

        mappingRepository.save(mapping);
    }

    private void updateMapping(QuestionOptionMapping mapping) {
        Long parentId = mapping.getParentQuestion().getId();
        if (!mappingList.containsKey(parentId)) {
            mappingList.put(parentId, mappingRepository.getAllByParentQuestion_Id(parentId));
        }
        List<QuestionOptionMapping> mappingList = mappingRepository.getAllByParentQuestion_Id(
                mapping.getParentQuestion().getId()
        );

    }

    @Transactional
    public void delete(Long id) {
        try {
            mappingRepository.deleteById(id);
        } catch (IllegalArgumentException exception) {
            throw new NotFoundException(String.format("Relation with '%s' id not found.", id));
        }
    }

    public List<OptionRelationDto> getRelations(Long questionId) {
        List<QuestionOptionMapping> mappingList = mappingRepository.getAllByParentQuestion_Id(questionId);
        List<OptionRelationDto> optionRelationList = new ArrayList<>();

        mappingList.forEach(mapping -> {
            OptionEntity option = optionService.getOptionEntity(mapping.getOption().getId());
            optionRelationList.add(
                    OptionRelationDto.builder()
                            .id(option.getId())
                            .code(option.getCode())
                            .label(option.getLabel())
                            .sortOrder(option.getSortOrder())
                            .referenceId(mapping.getChildQuestion().getId())
                            .typeId(option.getType().getId())
                            .build()
            );
        });

        return optionRelationList;
    }
}
