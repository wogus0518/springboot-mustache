package com.mustache.bbs.controller.hospital;


import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}