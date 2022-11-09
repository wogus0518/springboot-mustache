package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping
    public String redirectList() {
        return "redirect:/articles/list";
    }

    @GetMapping("/list")
    public String selectAll(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        model.addAttribute("message", "메시지");
        return "list";
    }

    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto form) {
        // 실무에서 println 안씀 로그를 쓴다(서버에서 일어나는 일을 기록하는것)
        log.info(form.toString());
        Article article = form.toEntity();
        Article save = articleRepository.save(article);
        log.info("id={}", save.getId());
        return "redirect:/articles/list/" + save.getId();
    }

    @GetMapping("/list/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isPresent()) {
            model.addAttribute("article", optArticle.get());
            return "show";
        } else {
            return "error";
        }
    }

    @GetMapping("/list/{id}/edit")
    public String editPage(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isPresent()) {
            model.addAttribute("article", optArticle.get());
            return "edit";
        } else {
            model.addAttribute("message", "id=" + id + " 가 없습니다.");
            return "error";
        }
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto) {
        Article article = new Article(id, articleDto.getTitle(), articleDto.getContent());
        Article save = articleRepository.save(article);
        log.info("id={}", save.getId());
        return "redirect:/articles/list/" + save.getId();
    }

    @GetMapping("/list/{id}/delete")
    public void delete(@PathVariable Long id, HttpServletResponse response) throws IOException {
        articleRepository.deleteById(id);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('삭제가 완료되었습니다.'); location.href='/articles/list'</script>");
        out.flush();
    }
}
