package com.mustache.bbs.controller.hospital;


import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs.domain.dto.hospital.ReviewAddResponse;
import com.mustache.bbs.domain.dto.hospital.ReviewDto;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
