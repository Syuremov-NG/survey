package sng.survey.model.dto.option;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OptionRelationDto extends OptionDto {

    @NonNull
    private Long referenceId;
}
