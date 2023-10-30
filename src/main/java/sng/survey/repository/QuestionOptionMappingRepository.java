package sng.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sng.survey.model.entity.QuestionOptionMapping;

import java.util.List;

@Repository
public interface QuestionOptionMappingRepository extends JpaRepository<QuestionOptionMapping, Long> {
    List<QuestionOptionMapping> getAllByParentQuestion_Id(Long questionId);
}
