package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
