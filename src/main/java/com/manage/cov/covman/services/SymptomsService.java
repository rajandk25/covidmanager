package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.*;
import com.manage.cov.covman.repositories.SymptomAnswersRepository;
import com.manage.cov.covman.repositories.SymptomQuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();

        SymptomAnswers todayCheckIn =  checkInRepository.findByStudentIdAndModificationModifiedAtAfter(studentId, startOfDay);

        return todayCheckIn;
    }

    public SymptomAnswers addDailyCheckIn(SymptomAnswers checkIn) {
        Student studentWithParent = studentService.getParent(checkIn.getStudent().getId());

        String emailId = studentWithParent.getParent().getUser().getEmail();
        Modification modification = new Modification(LocalDateTime.now(), emailId);

        for (SymptomAnswer answer: checkIn.getSymptomAnswer()) {
            answer.setSymptomAnswers(checkIn);
        }

        checkIn.setModification(modification);

        return checkInRepository.save(checkIn);
    }
}
