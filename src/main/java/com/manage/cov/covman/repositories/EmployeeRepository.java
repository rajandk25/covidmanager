package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
