package com.mustache.bbs.domain.entity.hospital;

import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "nation_wide_hospitals")
public class Hospital {

    @Id
    @GeneratedValue
    @Column(name = "hospital_id")
    private Integer id;

    @Column(name = "open_service_name")
    private String openServiceName;

    @Column(name = "open_local_government_code")
    private Integer openLocalGovernmentCode;

    @Column(name = "management_number")
    private String managementNumber;

    @Column(name = "license_date")
    private LocalDateTime license_date;

    @Column(name = "business_status")
    private Byte businessStatus;

    @Column(name = "business_status_code")
    private Byte businessStatusCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "business_type_name")
    private String businessTypeName;

    @Column(name = "healthcare_provider_count")
    private Integer healthcareProviderCount;

    @Column(name = "patient_room_count")
    private Integer patientRoomCount;

    @Column(name = "total_number_of_beds")
    private Integer totalNumberOfBeds;

    @Column(name = "total_area_size")
    private Float totalAreaSize;

    @OneToMany(mappedBy = "hospital")
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "review_count")
    private Integer reviewCount;

    //HospitalEntity를 HospitalResponse DTO로 만들어주는 부분
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
//                hospital.getOpenServiceName(),
//                hospital.getOpenLocalGovernmentCode(),
//                hospital.getManagementNumber(),
//                hospital.getLicense_date(),
//                hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(),
                "상태",
                hospital.getPhone(),
                hospital.getFullAddress(),
//                hospital.getRoadNameAddress(),
                hospital.getHospitalName(),
                hospital.getBusinessTypeName()
//                hospital.getHealthcareProviderCount(),
//                hospital.getPatientRoomCount(),
//                hospital.getTotalNumberOfBeds(),
//                hospital.getTotalAreaSize()
        );
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
}
