package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.entity.Student;
import com.manage.cov.covman.repositories.EmployeeRepository;
import com.manage.cov.covman.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
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

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //only get today's check-In for all the students
    public Employee getEmployee(Long employeeUserId) {
        return employeeRepository.findByUserId(employeeUserId);
    }


    public Employee modifyEmployee(Employee employee) {
        employee.getModification().setModifiedAt(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public boolean deleteEmployee(Long id) {
        try {
            Employee existing = employeeRepository.getOne(id);

            if (!CollectionUtils.isEmpty(existing.getStudents())) {
                for (Student student: existing.getStudents()) {
                    student.getEmployee().remove(existing);
                }

                existing.getStudents().clear();
            }

            employeeRepository.delete(existing);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
