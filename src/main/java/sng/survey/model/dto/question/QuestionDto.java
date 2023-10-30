package sng.survey.model.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import sng.survey.model.dto.option.OptionRelationDto;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long id;

    private String content;

    private String description;

    @NonNull
    @JsonProperty("type_id")
    private Long typeId;

    @JsonProperty("child_id")
    private Long childId;

    private List<OptionRelationDto> options;
}
