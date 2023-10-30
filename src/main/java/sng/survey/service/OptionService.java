package sng.survey.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sng.survey.exception.AlreadyExistException;
import sng.survey.exception.NotFoundException;
import sng.survey.mapper.OptionMapper;
import sng.survey.model.dto.option.OptionDto;
import sng.survey.model.entity.OptionEntity;
import sng.survey.repository.OptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {

    final private OptionMapper optionMapper;
    final private OptionRepository repository;

    public OptionEntity create(OptionDto optionDto) {
        repository.findByCode(optionDto.getCode()).ifPresent(foundEntity -> {
            throw new AlreadyExistException(
                    String.format("Option with '%s' code already exist.", optionDto.getCode())
            );
        });

        return repository.save(optionMapper.toModel(optionDto));
    }

    public void update(OptionDto optionDto) {
        OptionEntity optionEntity = this.getOptionEntity(optionDto.getId());
        optionEntity.setLabel(optionDto.getLabel());
        optionEntity.setSortOrder(optionDto.getSortOrder());

        repository.save(optionEntity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(String.format("Option with '%s' ID not found.", id));
        }
        repository.deleteById(id);
    }

    public OptionEntity getOptionEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Option with '%s' ID not found.", id))
        );
    }

    public List<OptionDto> getAllOptions() {
        return optionMapper.toListDto(repository.findAll());
    }

    public OptionDto getOptionDto(Long id) {
        return optionMapper.toDto(this.getOptionEntity(id));
    }
}
