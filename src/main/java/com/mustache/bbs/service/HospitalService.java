package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.hospital.HospitalExistReviewDto;
import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs.domain.dto.hospital.ReviewAddResponse;
import com.mustache.bbs.domain.dto.hospital.ReviewDto;
import com.mustache.bbs.domain.entity.hospital.Hospital;
import com.mustache.bbs.domain.entity.hospital.Review;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;


    @Transactional
    public ReviewAddResponse addReview(ReviewDto reviewDto, int id) {
        Hospital hospital = hospitalRepository.findById(id).get();
        Review review = new Review(reviewDto.getNickname(), reviewDto.getContent(), hospital);
        Review save = reviewRepository.save(review);

        hospital.setReviewCount(hospital.getReviews().size()+1);

        return new ReviewAddResponse(save.getId(), save.getNickname(), save.getContent());
    }

    public List<ReviewDto> getReviews(int id) {
        Hospital hospital = hospitalRepository.findById(id).orElse(null);
        List<Review> reviews = hospital.getReviews();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            reviewDtoList.add(new ReviewDto(review.getNickname(), review.getContent()));
        }
        return reviewDtoList;
    }

    public List<HospitalExistReviewDto> getHospitalExistReview(Pageable pageable) {
        List<Hospital> hospitals = hospitalRepository.findByReviewCountGreaterThan(0, pageable).getContent();
        List<HospitalExistReviewDto> dtoList = new ArrayList<>();
        for (int i = 0; i < hospitals.size(); i++) {
            Hospital hospital = hospitals.get(i);
            dtoList.add(new HospitalExistReviewDto(hospital.getHospitalName(), hospital.getFullAddress(), hospital.getReviewCount()));
        }
        return dtoList;
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
