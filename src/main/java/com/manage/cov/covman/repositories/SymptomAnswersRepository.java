package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.SymptomAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SymptomAnswersRepository extends JpaRepository<SymptomAnswers, Long> {

    SymptomAnswers findByStudentIdAndModificationModifiedAtAfter(Long studentId, LocalDateTime start);

    List<SymptomAnswers> findByStudentId(Long studentId);
}
