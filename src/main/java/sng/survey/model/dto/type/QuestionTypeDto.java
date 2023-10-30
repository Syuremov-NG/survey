package sng.survey.model.dto.type;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sng.survey.model.dto.option.OptionDto;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTypeDto {

    private Long id;

    @NotBlank
    private String code;

    private String label;

    private List<OptionDto> options;
}
