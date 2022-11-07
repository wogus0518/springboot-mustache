package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션 log를 사용할 수있다.
public class ArticleController {

    @GetMapping(value = "/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping(value = "/articles/posts")
    public String createArticle(ArticleDto form) {
        // 실무에서 println 안씀 로그를 쓴다(서버에서 일어나는 일을 기록하는것)
        log.info(form.toString());
        return "";
    }
}
