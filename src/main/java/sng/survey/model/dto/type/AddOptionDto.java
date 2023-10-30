package sng.survey.model.dto.type;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOptionDto {

    @NotBlank
    private Long typeId;

    @NotBlank
    private Long optionId;
}
