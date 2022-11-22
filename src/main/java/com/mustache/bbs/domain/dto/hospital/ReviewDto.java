package com.mustache.bbs.domain.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDto {
    private String nickname;
    private String content;
}
