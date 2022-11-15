package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.SearchDto;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/list")
    public String findByOption(@RequestParam(required = false) String businessTypeName,
                              @RequestParam(required = false) String location,
                              Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        if (businessTypeName==null && location==null) {
            Page<Hospital> hospitals = hospitalService.getHospitalList(pageable);
            modelAttributer(model, pageable, hospitals);

        } else {
            Page<Hospital> hospitals = hospitalService.getHospitalListBySearchOption(businessTypeName, location, pageable);
            int resultSize = hospitals.getContent().size();
            if (resultSize == 0) {
                model.addAttribute("error", "좀 더 광범위한 지역명이 필요합니다.");
            }
            modelAttributer(model, pageable, hospitals);
            model.addAttribute("businessTypeName", businessTypeName);
            model.addAttribute("location", location);
        }
        return "hospital/hospitalList";
    }

    @PostMapping("/list")
    public String findByLocation(@Validated SearchDto searchDto, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        boolean selectBusinessTypeName = searchDto.getBusinessTypeName().equals("업태구분명 선택");

        if (bindingResult.hasErrors() || selectBusinessTypeName) {
            model.addAttribute("error", "검색 조건이 올바르지 않습니다.");

            Page<Hospital> hospitals = hospitalService.getHospitalList(pageable);
            modelAttributer(model, pageable, hospitals);

            return "hospital/hospitalList";
        }

        redirectAttributes.addAttribute("businessTypeName", searchDto.getBusinessTypeName());
        redirectAttributes.addAttribute("location", searchDto.getLocation());

        return "redirect:/hospital/list";
    }

    @GetMapping("/list/{id}")
    public String showHospital(@PathVariable int id, Model model) {

        Hospital hospital = hospitalService.getHospitalById(id);
        model.addAttribute("hospital", hospital);
        return "hospital/show";
    }

    private static void modelAttributer(Model model, Pageable pageable, Page<Hospital> hospitals) {
        model.addAttribute("hospitals", hospitals);

        if (pageable.hasPrevious()) model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());

        if (hospitals.hasNext()) model.addAttribute("next", pageable.next().getPageNumber());

    }
}
