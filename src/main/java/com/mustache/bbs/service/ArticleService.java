package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.ArticleResponse;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponse getArticleResponseById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            return Article.of(article);
        } else {
            throw new RuntimeException();
        }
    }
}
