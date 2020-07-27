package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.utils.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByUserRole(RoleEnum role);

    Employee findByUserId(Long userId);
}
