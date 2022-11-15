package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
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
    public Hospital getHospitalById(int id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        if (optHospital.isEmpty()) {
            throw new NullPointerException();
        } else {
            return optHospital.get();
        }
    }

    @Transactional
    public HospitalResponse getHospitalResponseById(int id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        if (optHospital.isEmpty()) {
            throw new NullPointerException();
        } else {
            HospitalResponse hospitalResponse = Hospital.of(optHospital.get());
            if (hospitalResponse.getBusinessStatusCode() == 13) {
                hospitalResponse.setBusinessStatusName("영업중");
            } else if (hospitalResponse.getBusinessStatusCode() == 3) {
                hospitalResponse.setBusinessStatusName("폐업");
            }
            return hospitalResponse;
        }
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
