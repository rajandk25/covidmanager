package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.*;
import com.manage.cov.covman.repositories.SymptomAnswersRepository;
import com.manage.cov.covman.repositories.SymptomQuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SymptomsService {

    private SymptomQuestionRepository symptomQuestionRepository;

    private SymptomAnswersRepository checkInRepository;

    private StudentService studentService;

    public SymptomsService(SymptomQuestionRepository symptomQuestionRepository,
                           SymptomAnswersRepository checkInRepository, StudentService studentService) {
        this.symptomQuestionRepository = symptomQuestionRepository;
        this.checkInRepository = checkInRepository;
        this.studentService = studentService;
    }

    public List<SymptomQuestion> getAllQuestions() {
        return symptomQuestionRepository.findAll();
    }

    public SymptomAnswers getDailyCheckIn(Long studentId) {
        return checkInRepository.findByStudentIdAndModificationModifiedAt(studentId, LocalDateTime.now().toLocalDate());
    }

    public SymptomAnswers addDailyCheckIn(Student student) {
        SymptomAnswers checkIn = student.getSymptomAnswers();
        Student studentWithParent = studentService.getParent(student.getId());
        String emailId = studentWithParent.getParent().getUser().getEmail();
        Modification modification = new Modification(LocalDateTime.now(), emailId);
        checkIn.setStudent(student);

        for (SymptomAnswer answer: checkIn.getSymptomAnswer()) {
            answer.setSymptomAnswers(checkIn);
        }

        checkIn.setModification(modification);
        student.setModification(modification);

        return checkInRepository.save(checkIn);
    }
}
