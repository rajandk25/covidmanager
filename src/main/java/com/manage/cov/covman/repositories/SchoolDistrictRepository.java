package com.manage.cov.covman.repositories;

import com.manage.cov.covman.entity.SchoolDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDistrictRepository extends JpaRepository<SchoolDistrict, Long> {
}
