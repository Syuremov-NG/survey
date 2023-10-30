package sng.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_entity")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private QuestionTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "default_child")
    private QuestionEntity defaultChild;
}
