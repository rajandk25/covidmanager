package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
