package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.entity.Modification;
import com.manage.cov.covman.entity.Student;
import com.manage.cov.covman.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getParent(Long id) {
        return studentRepository.getOne(id);
    }

    public Student addStudent(Student student) {
        student.setModification(new Modification(LocalDateTime.now(), student.getParent().getUser().getEmail()));

        if(student.getEmployee() != null && student.getEmployee().size() > 0) {
            for (Employee employee: student.getEmployee()) {
                employee.getStudents().add(student);
            }
        }

        return studentRepository.save(student);
    }
}
