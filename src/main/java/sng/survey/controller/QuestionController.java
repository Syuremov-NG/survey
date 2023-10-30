package sng.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sng.survey.mapper.QuestionMapper;
import sng.survey.model.dto.question.QuestionDto;
import sng.survey.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/questions")
public class QuestionController {

    private final QuestionService service;

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getQuestionById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody QuestionDto questionDto) {
        service.create(questionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<QuestionDto> update(@RequestBody QuestionDto questionDto) {
        service.update(questionDto);
        return ResponseEntity.ok().build();
    }
}
