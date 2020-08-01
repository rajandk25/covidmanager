package com.manage.cov.covman.services;

import com.manage.cov.covman.entity.School;
import com.manage.cov.covman.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }
}
