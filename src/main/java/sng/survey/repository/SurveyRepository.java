package sng.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sng.survey.model.entity.SurveyEntity;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
}
