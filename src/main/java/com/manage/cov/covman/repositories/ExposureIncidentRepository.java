package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.ExposureIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExposureIncidentRepository extends JpaRepository<ExposureIncident, Long> {

    List<ExposureIncident> findByStudentIdIn(List<Long> studentIds);
}
