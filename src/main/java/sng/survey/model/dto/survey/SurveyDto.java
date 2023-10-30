package sng.survey.model.dto.survey;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import sng.survey.model.dto.question.QuestionDto;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {

    @Nullable
    private Long id;

    @NonNull
    private String code;

    @NonNull
    private String title;

    @NonNull
    @JsonProperty("first_question")
    private QuestionDto firstQuestion;
}
