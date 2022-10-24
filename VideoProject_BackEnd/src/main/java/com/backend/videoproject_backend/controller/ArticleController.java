package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "文章管理")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @PutMapping("/article/like/{id}")
    @ResponseBody
    @ApiOperation("点赞")
    public String LikeArticle(@PathVariable Integer id)
    {
        return articleService.likeArticle(id);
    }
}
