package com.mustache.bbs.domain.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleDto {
    private Long id;
    private final String title;
    private final String content;

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
