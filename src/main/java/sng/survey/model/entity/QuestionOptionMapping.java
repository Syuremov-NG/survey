package sng.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "question_option_mapping",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"parent_id", "option_id"})
        }
)
public class QuestionOptionMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long mappingId;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private QuestionEntity parentQuestion;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private OptionEntity option;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private QuestionEntity childQuestion;
}
