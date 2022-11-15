package com.mustache.bbs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class HospitalResponse {
    private Integer id;
    private String openServiceName;
    private Integer openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime license_date;
    private Byte businessStatus;
    private Byte businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private Integer healthcareProviderCount;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private Float totalAreaSize;
}
