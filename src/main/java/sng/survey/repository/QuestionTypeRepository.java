package sng.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sng.survey.model.entity.QuestionTypeEntity;

import java.util.Optional;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionTypeEntity, Long> {
    Optional<QuestionTypeEntity> findByCode(String code);

    Integer deleteByCode(String code);
}
