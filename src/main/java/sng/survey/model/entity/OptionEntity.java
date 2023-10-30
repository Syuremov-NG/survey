package sng.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option_entity")
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String label;

    @Builder.Default
    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private QuestionTypeEntity type;
}
