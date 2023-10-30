package sng.survey.model.dto.relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionMappingDto {

    private Long id;

    @NonNull
    @JsonProperty("parent_id")
    private Long parentId;

    @NonNull
    @JsonProperty("option_id")
    private Long optionId;

    @NonNull
    @JsonProperty("child_id")
    private Long childId;
}
