package com.mustache.bbs.domain.dto;

public class ArticleDto {
    private Long id;
    private final String title;
    private final String content;

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "title='" + title + '\'' + ", content='" + content + '\'' + '}';
    }
}
