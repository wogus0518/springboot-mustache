package com.mustache.bbs.domain.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalExistReviewDto {
    private String hospitalName;
    private String hospitalAddress;
    private int reviewCount;
}
