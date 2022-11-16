package com.mustache.bbs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
}
