package com.backend.videoproject_backend.test;

import com.backend.videoproject_backend.dto.TbArticleEntity;
import com.backend.videoproject_backend.service.impl.ArticleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleServiceTest {

    @Autowired
    ArticleServiceImpl articleService;

    @Test
    public void testLike() {
        String output;
        int num=1;
        //用例1
        output = articleService.likeArticle2(1000000000,2);
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例2
        output = articleService.likeArticle2(30,2);
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例3
        output = articleService.likeArticle2(4,1000000000);
        if (!Objects.equals(output, "文章不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例4
        output = articleService.likeArticle2(4,34);
        if (!Objects.equals(output, "文章不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例5
        output = articleService.likeArticle2(4,3);
        if (!Objects.equals(output, "点赞成功")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例6
        output = articleService.likeArticle2(4,3);
        if (!Objects.equals(output, "取消点赞成功")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
    }



    @Test
    public void testGet(){
        TbArticleEntity tbArticleEntity;
        int num=1;
        //用例1
        tbArticleEntity = articleService.getArticleById(1000000000);
        if(tbArticleEntity!=null){
            System.out.println("The testGet"+num+" Test Failed");
            return;
        }
        num++;
        //用例2
        tbArticleEntity = articleService.getArticleById(114514);
        if(tbArticleEntity!=null){
            System.out.println("The testGet"+num+" Test Failed");
            return;
        }
        num++;
        //用例3
        tbArticleEntity = articleService.getArticleById(2);
        if(tbArticleEntity==null){
            System.out.println("The testGet"+num+" Test Failed");
            return;
        }
        num++;
        //用例4
        tbArticleEntity = articleService.getArticleById(3);
        if(tbArticleEntity==null){
            System.out.println("The testGet"+num+" Test Failed");
            return;
        }
        num++;
        //用例5
        tbArticleEntity = articleService.getArticleById(-1);
        if(tbArticleEntity!=null){
            System.out.println("The testGet"+num+" Test Failed");
            return;
        }
        num++;
    }

    @Test
    public void testPost(){
        String output;
        int num=1;
        //用例1
        output = articleService.addArticle2(-1,null);
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例2
        output = articleService.addArticle2(-1,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The " + num + " Test Failed");
            return;
        }
        num++;
        //用例3
        output = articleService.addArticle2(-1,"hello,world!");
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例4
        output = articleService.addArticle2(100000000,null);
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例5
        output = articleService.addArticle2(100000000,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The " + num + " Test Failed");
            return;
        }
        num++;
        //用例6
        output = articleService.addArticle2(100000000,"hello,world!");
        if (!Objects.equals(output, "用户不存在")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例7
        output = articleService.addArticle2(4,null);
        if (!Objects.equals(output, "文章不能为空")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例8
        output = articleService.addArticle2(4,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if (!Objects.equals(output, "超出上限")) {
            System.out.println("The " + num + " Test Failed");
            return;
        }
        num++;
        //用例9
        output = articleService.addArticle2(4,"hello,world!");
        if (!Objects.equals(output, "保存文章成功")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
    }
}
