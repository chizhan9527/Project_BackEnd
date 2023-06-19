package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbArticleEntity;
import com.backend.videoproject_backend.service.ArticleService;
import com.backend.videoproject_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "文章管理")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @Autowired
    public UserService userService;

    @PostMapping("/article")
    @ResponseBody
    @ApiOperation("发布文章")
    public String publishArticle(String context) {
        try {
            if(context==null)
                return "不能为空";
            if(context.length()>=200)
                return "长度过长";
            articleService.addArticle(context);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/article/like2")
    @ResponseBody
    @ApiOperation("点赞")
    public String likeArticle2(Integer user_id,Integer id)
    {
        return articleService.likeArticle2(user_id,id);
    }

    @PutMapping("/article/like")
    @ResponseBody
    @ApiOperation("点赞")
    public String likeArticle(Integer id)
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

    @GetMapping("/article/page/{id}/{currentPage}")
    @ResponseBody
    @ApiOperation("按userid分页查询文章")
    public List<TbArticleEntity> getArticleByPageAndUserId(@PathVariable Integer id,@PathVariable Integer currentPage)
    {
        List<TbArticleEntity>tbArticleEntityList=articleService.getByPageAndUserIdService(id,currentPage);
        Iterator<TbArticleEntity> iterator=tbArticleEntityList.iterator();
        while(iterator.hasNext())
        {
            TbArticleEntity tbArticleEntity=iterator.next();
            tbArticleEntity.setName(userService.findUserById(tbArticleEntity.getUserId()).get().getName());
        }
        return tbArticleEntityList;
    }
}
