package com.mustache.bbs.domain.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ReviewAddResponse {
    private final int id;
    private final String nickname;
    private final String content;
}