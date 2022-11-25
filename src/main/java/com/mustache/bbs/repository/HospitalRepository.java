package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.hospital.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Page<Hospital> findByBusinessTypeNameAndFullAddressContaining(String businessTypeName, String fullAddress, Pageable pageable);

    Page<Hospital> findByReviewCountGreaterThan(int reviewCount, Pageable pageable);
}
