package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.repositories.EmployeeRepository;
import com.manage.cov.covman.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllTeachers() {
        return employeeRepository.findByUserRole(RoleEnum.TEACHER);
    }
}
