package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.SearchDto;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                              Model model,
                              @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("businessTypeName={}", businessTypeName);
        if (businessTypeName==null) {
            model.addAttribute("hospitals", hospitalService.getHospitalList(pageable));
            model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
            model.addAttribute("next", pageable.next().getPageNumber());
        } else {
            model.addAttribute("hospitals", hospitalService.getHospitalListBySearchOption(businessTypeName, location, pageable));
            model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
            model.addAttribute("next", pageable.next().getPageNumber());
        }
        return "hospital/hospitalList";
    }

    @PostMapping("/list")
    public String findByLocation(SearchDto searchDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("businessTypeName", searchDto.getBusinessTypeName());
        redirectAttributes.addAttribute("location", searchDto.getLocation());
        return "redirect:/hospital/list";
    }

    @GetMapping("/list/{id}")
    public String showHospital(@PathVariable int id, Model model) {
        log.info("id={}", id);
        Optional<Hospital> optHospital = hospitalService.getHospitalById(id);
        optHospital.ifPresent(hospital -> model.addAttribute("hospital", hospital));
        return "hospital/show";
    }
}
