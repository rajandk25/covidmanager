package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.SymptomAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomAnswerRepository extends JpaRepository<SymptomAnswer, Long> {
}
