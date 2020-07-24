package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.SymptomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomQuestionRepository extends JpaRepository<SymptomQuestion, Long> {
}
