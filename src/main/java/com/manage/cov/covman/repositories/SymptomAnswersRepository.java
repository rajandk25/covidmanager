package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.SymptomAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SymptomAnswersRepository extends JpaRepository<SymptomAnswers, Long> {

    SymptomAnswers findByStudentIdAndModificationModifiedAt(Long studentId, LocalDate today);
}
