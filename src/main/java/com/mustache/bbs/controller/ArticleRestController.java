package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleResponse;
import com.mustache.bbs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Long id) {
        ArticleResponse articleResponse = articleService.getArticleResponseById(id);
        return ResponseEntity.ok().body(articleResponse);
    }
}
