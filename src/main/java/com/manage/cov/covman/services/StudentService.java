package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.entity.Modification;
import com.manage.cov.covman.entity.Student;
import com.manage.cov.covman.entity.SymptomAnswers;
import com.manage.cov.covman.repositories.StudentRepository;
import com.manage.cov.covman.repositories.SymptomAnswersRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private SymptomAnswersRepository symptomAnswersRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, SymptomAnswersRepository symptomAnswersRepository) {
        this.studentRepository = studentRepository;
        this.symptomAnswersRepository = symptomAnswersRepository;
    }

    public Student getParent(Long id) {
        return studentRepository.getOne(id);
    }

    /**
     * Used to both add and update the students
     *
     *
     * @param student
     * @return
     */
    public Student addStudent(Student student) {
        //if this is an update
        if (student.getId() != null) {
            Student existingStudent = studentRepository.getOne(student.getId());
            Hibernate.initialize(student.getEmployee());
            student.setParent(existingStudent.getParent());
        }

        student.setModification(new Modification(LocalDateTime.now(), student.getParent().getUser().getEmail()));

        if(student.getEmployee() != null && student.getEmployee().size() > 0) {
            for (Employee employee: student.getEmployee()) {
                employee.getStudents().add(student);
            }
        }

        return studentRepository.save(student);
    }

    /**
     * Delete student as well as all its checkIns
     * @param studentId
     */
    public boolean deleteStudent(Long studentId) {
        try {
            //if student has symptoms, delete those
            List<SymptomAnswers> symptomAnswers = symptomAnswersRepository.findByStudentId(studentId);

            if (symptomAnswers != null && symptomAnswers.size() > 0) {
                symptomAnswersRepository.deleteAll(symptomAnswers);
            }

            studentRepository.deleteById(studentId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
