package com.mustache.bbs.controller.article;

import com.mustache.bbs.domain.dto.article.add.ArticleAddRequest;
import com.mustache.bbs.domain.dto.article.add.ArticleAddResponse;
import com.mustache.bbs.domain.dto.article.ArticleResponse;
import com.mustache.bbs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<ArticleAddResponse> add(@RequestBody ArticleAddRequest dto) {
        return ResponseEntity.ok().body(articleService.add(dto));
    }
}
