package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HospitalService hospitalService;

    @Test
    @DisplayName("MockMvc를 통한 HospitalResponse 데이터 가져오기 테스트")
    void getHospital() throws Exception {
        HospitalResponse hospitalResponse = new HospitalResponse(2312, (byte)13, "영업중", "595-9828",
                "서울특별시 서초구 잠원로 148, 206호 (잠원동, 잠원연합상가)", "연세의원", "의원");

        given(hospitalService.getHospitalResponseById(2312)).willReturn(hospitalResponse);

        int hospitalId = 2312;

        mockMvc.perform(
                get("/api/v1/hospitals/"+hospitalId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2312))
                .andExpect(jsonPath("$.businessStatusCode").value(13))
                .andExpect(jsonPath("$.businessStatusName").value("영업중"))
                .andExpect(jsonPath("$.phone").value("595-9828"))
                .andExpect(jsonPath("$.fullAddress").value("서울특별시 서초구 잠원로 148, 206호 (잠원동, 잠원연합상가)"))
                .andExpect(jsonPath("$.hospitalName").value("연세의원"))
                .andExpect(jsonPath("$.businessTypeName").value("의원"))
                .andDo(print());

        verify(hospitalService).getHospitalResponseById(hospitalId);
    }

}