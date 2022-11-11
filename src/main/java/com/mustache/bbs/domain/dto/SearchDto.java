package com.mustache.bbs.domain.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchDto {
    private final String businessTypeName;
    private final String location;

    public SearchDto(String businessTypeName, String location) {
        this.businessTypeName = businessTypeName;
        this.location = location;
    }
}
