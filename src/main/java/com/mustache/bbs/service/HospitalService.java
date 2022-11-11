package com.mustache.bbs.service;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public Optional<Hospital> getHospitalById(int id) {
        return hospitalRepository.findById(id);
    }

    @Transactional
    public Page<Hospital> getHospitalList(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    @Transactional
    public Page<Hospital> getHospitalListBySearchOption(String businessTypeName, String location, Pageable pageable) {
        return hospitalRepository.findByBusinessTypeNameAndFullAddressContaining(businessTypeName, location, pageable);
    }
}
