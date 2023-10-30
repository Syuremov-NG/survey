package sng.survey.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sng.survey.exception.NotFoundException;
import sng.survey.mapper.QuestionTypeMapper;
import sng.survey.mapper.TypeForOptionMapper;
import sng.survey.model.dto.type.AddOptionDto;
import sng.survey.model.dto.type.QuestionTypeDto;
import sng.survey.model.entity.OptionEntity;
import sng.survey.model.entity.QuestionTypeEntity;
import sng.survey.repository.QuestionTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTypeService {

    final private QuestionTypeMapper typeMapper;
    final private QuestionTypeRepository typeRepository;
    final private OptionService optionService;
    final private TypeForOptionMapper typeForOptionMapper;

    @Transactional
    public void create(QuestionTypeDto questionTypeDto) {
        QuestionTypeEntity type = typeRepository.save(typeMapper.toModel(questionTypeDto));
        questionTypeDto.getOptions().forEach(optionDto -> {
            optionDto.setTypeId(type.getId());
            optionService.create(optionDto);
        });
    }

    public void update(QuestionTypeDto typeDto) {
        QuestionTypeEntity typeEntity = this.getType(typeDto.getId());
        typeEntity.setLabel(typeDto.getLabel());
        typeRepository.save(typeEntity);
    }

    @Transactional
    public void delete(String code) {
        if (!typeRepository.deleteByCode(code).equals(1)) {
            throw new NotFoundException(String.format("Question Type with '%s' code not found.", code));
        }
    }

    public void addExistOption(AddOptionDto addOptionDto) {
        QuestionTypeEntity typeEntity = this.getType(addOptionDto.getTypeId());
        OptionEntity optionEntity = this.optionService.getOptionEntity(addOptionDto.getOptionId());
        typeEntity.getOptions().add(optionEntity);
        typeRepository.save(typeEntity);
    }

    public QuestionTypeEntity getType(Long id) {
        return typeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Question Type with '%s' ID not found.", id))
        );
    }

    public QuestionTypeDto getTypeDto(Long id) {
        return typeMapper.toDto(this.getType(id));
    }

    public List<QuestionTypeDto> getAllOptions() {
        return typeMapper.toListDto(typeRepository.findAll());
    }
}
