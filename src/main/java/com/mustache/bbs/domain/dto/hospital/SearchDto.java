package com.mustache.bbs.domain.dto.hospital;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class SearchDto {
    private final String businessTypeName;
    @NotBlank
    private final String location;

    public SearchDto(String businessTypeName, String location) {
        this.businessTypeName = businessTypeName;
        this.location = location;
    }
}
