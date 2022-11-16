package com.mustache.bbs.domain.entity.article;

import com.mustache.bbs.domain.dto.article.ArticleResponse;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static ArticleResponse of(Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }
}
