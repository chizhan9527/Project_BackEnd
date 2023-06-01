package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbArticleEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    void likeArticle() {
    }

    @Test
    void getArticleById() {
    }

    @Test
    void addArticle() {
    }

    @Test
    void getByPageService() {

    }

    @Test
    void getByPageAndUserIdService() {
        int[] id = new int[]{4,4,4,4,4,4,4,-1,1000};
        int[] page = new int[]{3,-1,0,1,4,5,6,3,3};
        boolean[] res=new boolean[]{true,false,true,true,true,true,false,false,false};
        int num=0;
        for(int i=0;i<id.length;i++){
            List<TbArticleEntity> mid=articleService.getByPageAndUserIdService(id[i],page[i]);
            if(res[i]){
                if(mid!=null)num++;
            }
            else{
                if(mid==null)num++;
            }
        }
        System.out.println(6-num+"个测试用例通过");

    }
}