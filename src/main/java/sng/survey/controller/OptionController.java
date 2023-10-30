package sng.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sng.survey.model.dto.option.OptionDto;
import sng.survey.service.OptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/options")
public class OptionController {

    private final OptionService service;

    @GetMapping("/{id}")
    public ResponseEntity<OptionDto> getByCode(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOptionDto(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody OptionDto optionDto) {
        service.create(optionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<OptionDto> update(@RequestBody OptionDto optionDto) {
        service.update(optionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OptionDto>> getAll() {
        return ResponseEntity.ok(service.getAllOptions());
    }
}
