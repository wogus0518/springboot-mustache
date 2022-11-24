package com.mustache.bbs.controller.hospital;


import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs.domain.dto.hospital.ReviewAddResponse;
import com.mustache.bbs.domain.dto.hospital.ReviewDto;
import com.mustache.bbs.domain.entity.hospital.Hospital;
import com.mustache.bbs.domain.entity.hospital.Review;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) {
        HospitalResponse hospitalResponse = hospitalService.getHospitalResponseById(id);
        return ResponseEntity.ok().body(hospitalResponse);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewAddResponse> add(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        ReviewAddResponse reviewAddResponse = hospitalService.addReview(reviewDto, id);
        return ResponseEntity.ok().body(reviewAddResponse);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Map<String, Object>> get(@PathVariable int id) {
        List<ReviewDto> reviewDtoList = hospitalService.getReviews(id);

        Map<String, Object> result = new HashMap<>();
        result.put("data", reviewDtoList);
        result.put("count", reviewDtoList.size());
        result.put("hospital_id", id);
        result.put("hospital_name", hospitalService.getHospitalById(id).getHospitalName());

        return ResponseEntity.ok().body(result);
    }
}
