package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbArticleEntity;
import com.backend.videoproject_backend.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "文章管理")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @PostMapping("/article")
    @ResponseBody
    @ApiOperation("发布文章")
    public String publishArticle(String context) {
        try {
            articleService.addArticle(context);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/article/like/{id}")
    @ResponseBody
    @ApiOperation("点赞")
    public String likeArticle(@PathVariable Integer id)
    {
        return articleService.likeArticle(id);
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    @ApiOperation("获取文章")
    public TbArticleEntity getArticleById(@PathVariable Integer id)
    {
        return articleService.getArticleById(id);
    }

    @GetMapping("/article/page/{currentPage}")
    @ResponseBody
    @ApiOperation("分页查询文章")
    public List<TbArticleEntity> getArticleByPage(@PathVariable Integer currentPage)
    {
        return articleService.getByPageService(currentPage);
    }
}
