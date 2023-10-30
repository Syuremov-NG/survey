package sng.survey.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sng.survey.exception.CycleWasFoundException;
import sng.survey.exception.NotFoundException;
import sng.survey.mapper.QuestionMapper;
import sng.survey.model.dto.option.OptionRelationDto;
import sng.survey.model.dto.question.QuestionDto;
import sng.survey.model.entity.QuestionEntity;
import sng.survey.repository.QuestionRepository;
import sng.survey.utils.Graph;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    final private QuestionTypeService typeService;
    final private QuestionMapper questionMapper;
    final private QuestionRepository questionRepository;
    final private QuestionOptionMappingService mappingService;
    final private Graph graph;

    public QuestionDto getQuestionById(Long id) {
        QuestionDto questionDto = questionMapper.toDto(this.getQuestion(id));
        questionDto.setOptions(mappingService.getRelations(questionDto.getId()));

        return questionDto;
    }

    public void create(QuestionDto questionDto) {
        QuestionEntity questionEntity = questionMapper.toModel(questionDto);
        Optional<Long> childId = Optional.ofNullable(questionDto.getChildId());

        if (childId.isEmpty()) {
            questionRepository.save(questionEntity);
            return;
        }

        this.fillGraph(questionEntity, childId);
        questionRepository.save(questionEntity);
    }

    public void update(QuestionDto questionDto) {
        QuestionEntity questionEntity = this.getQuestion(questionDto.getId());
        questionEntity.setContent(questionDto.getContent());
        questionEntity.setDescription(questionDto.getDescription());
        questionEntity.setType(typeService.getType(questionDto.getTypeId()));
        Optional<Long> childId = Optional.ofNullable(questionDto.getChildId());
        childId.ifPresentOrElse(
                aLong -> questionEntity.setDefaultChild(this.getQuestion(aLong)),
                () -> questionEntity.setDefaultChild(null)
        );

        this.fillGraph(questionEntity, childId);

        questionRepository.save(questionEntity);
    }

    @Transactional
    public void delete(Long id) {
        try {
            questionRepository.deleteById(id);
        } catch (IllegalArgumentException exception) {
            throw new NotFoundException(String.format("Question with '%s' ID not found.", id));
        }
    }

    public QuestionEntity getQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Question with '%s' ID not found.", id))
        );
    }

    public List<QuestionDto> getAll() {
        List<QuestionDto> questionList = questionMapper.toListDto(questionRepository.findAll());
        questionList.forEach(question -> question.setOptions(mappingService.getRelations(question.getId())));

        return questionList;
    }

    private void fillGraph(QuestionEntity questionEntity, Optional<Long> childId) {
        while (childId.isPresent()) {
            Long childIdValue = childId.get();
            if (!graph.addEdge(questionEntity.getId(), null, childId.get())) {
                throw new CycleWasFoundException("Cycle was found in default branch");
            }
            QuestionEntity childQuestion = this.getQuestion(childIdValue);
            QuestionEntity defaultChild = childQuestion.getDefaultChild();
            if (Optional.ofNullable(defaultChild).isPresent()) {
                childId = Optional.ofNullable(defaultChild.getId());
                continue;
            }
            childId = Optional.empty();
        }
    }
}

