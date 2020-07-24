package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.SymptomAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomAnswersRepository extends JpaRepository<SymptomAnswers, Long> {
}
