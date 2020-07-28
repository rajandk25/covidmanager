package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.entity.ExposureIncident;
import com.manage.cov.covman.entity.Modification;
import com.manage.cov.covman.repositories.EmployeeRepository;
import com.manage.cov.covman.repositories.ExposureIncidentRepository;
import com.manage.cov.covman.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private ExposureIncidentRepository exposureIncidentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ExposureIncidentRepository exposureIncidentRepository) {
        this.employeeRepository = employeeRepository;
        this.exposureIncidentRepository = exposureIncidentRepository;
    }

    public List<Employee> getAllTeachers() {
        return employeeRepository.findByUserRole(RoleEnum.TEACHER);
    }

    //only get today's check-In for all the students
    public Employee getEmployee(Long employeeUserId) {
        return employeeRepository.findByUserId(employeeUserId);
    }

    public ExposureIncident addExposureIncident(ExposureIncident exposureIncident) {
        exposureIncident.setModification(new Modification(LocalDateTime.now(), exposureIncident.getEmployee().getUser().getEmail()));

        return this.exposureIncidentRepository.save(exposureIncident);
    }

    //get all exposures for given student Ids
    public List<ExposureIncident> getExposuresForStudents(List<Long> studentIds) {
        return exposureIncidentRepository.findByStudentIdIn(studentIds);
    }
}
