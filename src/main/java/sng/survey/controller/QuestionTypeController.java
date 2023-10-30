package sng.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sng.survey.model.dto.option.OptionDto;
import sng.survey.model.dto.type.AddOptionDto;
import sng.survey.model.dto.type.QuestionTypeDto;
import sng.survey.service.QuestionTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/V1/types")
public class QuestionTypeController {

    private final QuestionTypeService service;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionTypeDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getTypeDto(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody QuestionTypeDto questionTypeDto) {
        service.create(questionTypeDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<OptionDto> update(@RequestBody QuestionTypeDto questionTypeDto) {
        service.update(questionTypeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable("code") String code) {
        service.delete(code);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<QuestionTypeDto>> getAll() {
        return ResponseEntity.ok(service.getAllOptions());
    }

    @PatchMapping("/add/option")
    public ResponseEntity<Void> addExistOption(@RequestBody AddOptionDto addOptionDto) {
        service.addExistOption(addOptionDto);
        return ResponseEntity.ok().build();
    }
}
