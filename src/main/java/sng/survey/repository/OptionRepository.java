package sng.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sng.survey.model.entity.OptionEntity;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

    Optional<OptionEntity> findByCode(String code);

    Integer deleteByCode(String code);

    Stream<OptionEntity> streamAllByCodeStartsWithIgnoreCase(String prefixCode);

    Stream<OptionEntity> streamAllBy();
}
