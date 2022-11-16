package com.mustache.bbs.domain.dto.article.add;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddResponse {
    private final Long id;
    private final String title;
    private final String content;
}
