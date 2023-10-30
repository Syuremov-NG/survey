package sng.survey.model.dto.option;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {

    private Long id;

    @NotBlank
    private String code;

    private String label;

    @JsonProperty("sort_order")
    private Integer sortOrder;

    @JsonProperty("type_id")
    private Long typeId;
}
