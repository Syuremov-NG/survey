package sng.survey.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sng.survey.model.dto.option.OptionRelationDto;
import sng.survey.model.dto.relation.QuestionOptionMappingDto;
import sng.survey.model.entity.OptionEntity;
import sng.survey.model.entity.QuestionEntity;
import sng.survey.service.OptionService;
import sng.survey.service.QuestionOptionMappingService;
import sng.survey.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/questions/options")
public class QuestionOptionMapperController {

    private final QuestionOptionMappingService mappingService;
    private final QuestionService questionService;
    private final OptionService optionService;

    @Transactional
    @PutMapping("/save/all")
    public ResponseEntity<Void> saveAll(@RequestBody List<QuestionOptionMappingDto> mappingDtoList) {
        mappingDtoList.forEach(mappingDto -> {
            QuestionEntity childQuestion = questionService.getQuestion(mappingDto.getChildId());
            QuestionEntity parentQuestion = questionService.getQuestion(mappingDto.getParentId());
            OptionEntity option = optionService.getOptionEntity(mappingDto.getOptionId());

            mappingService.save(parentQuestion, option, childQuestion);
        });

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        mappingService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/relations/{id}")
    public ResponseEntity<List<OptionRelationDto>> getRelations(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mappingService.getRelations(id));
    }
}
