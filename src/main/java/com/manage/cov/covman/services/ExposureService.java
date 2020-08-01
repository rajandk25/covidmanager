package com.manage.cov.covman.services;


import com.manage.cov.covman.entity.ExposureIncident;
import com.manage.cov.covman.entity.Modification;
import com.manage.cov.covman.repositories.ExposureIncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExposureService {

    private ExposureIncidentRepository exposureIncidentRepository;

    @Autowired
    public ExposureService(ExposureIncidentRepository exposureIncidentRepository) {
        this.exposureIncidentRepository = exposureIncidentRepository;

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
