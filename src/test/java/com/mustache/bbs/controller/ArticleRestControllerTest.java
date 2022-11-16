package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleResponse;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    void getArticleResponse() throws Exception {
        ArticleResponse articleResponse = new ArticleResponse((long)1, "제목", "글 내용");

        long articleId = 1;

        given(articleService.getArticleResponseById(articleId)).willReturn(articleResponse);


        mockMvc.perform(
                        get("/api/v1/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("글 내용"))
                .andDo(print());

        verify(articleService).getArticleResponseById(articleId);
    }
}