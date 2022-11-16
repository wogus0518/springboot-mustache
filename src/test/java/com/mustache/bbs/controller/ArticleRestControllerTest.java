package com.mustache.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.controller.article.ArticleRestController;
import com.mustache.bbs.domain.dto.article.ArticleResponse;
import com.mustache.bbs.domain.dto.article.add.ArticleAddRequest;
import com.mustache.bbs.domain.dto.article.add.ArticleAddResponse;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("id로 게시글 1개가 잘 조회되는지")
    void getArticleResponse() throws Exception {
        ArticleResponse articleResponse = new ArticleResponse((long)1, "제목", "글 내용");

        long articleId = 1;

        given(articleService.getArticleResponseById(articleId)).willReturn(articleResponse);


        mockMvc.perform(get("/api/v1/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("글 내용"))
                .andDo(print());

        verify(articleService).getArticleResponseById(articleId);
    }

    @Test
    @DisplayName("글 등록이 잘 되는지")
    void add() throws Exception {

        ArticleAddResponse response = new ArticleAddResponse(1L, "제목입니다", "내용입니다.");
        ArticleAddRequest request = new ArticleAddRequest("제목입니다", "내용입니다.");

        given(articleService.add(any(ArticleAddRequest.class))).willReturn(response);

        mockMvc.perform(post("/api/v1/articles/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
    }
}