package sng.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_type")
public class QuestionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String label;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "type_id")
    private List<OptionEntity> options = new ArrayList<>();
}
