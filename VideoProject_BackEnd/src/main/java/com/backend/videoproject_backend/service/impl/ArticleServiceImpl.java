package com.backend.videoproject_backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.backend.videoproject_backend.dao.ArticleDao;
import com.backend.videoproject_backend.dto.TbArticleEntity;
import com.backend.videoproject_backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String likeArticle(Integer id) {
        //用saToken获取用户当前id
        int user_id = StpUtil.getLoginIdAsInt();
        //判断当前用户是否已经点赞
        String key = "article:like" + id;
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key, Integer.toString(user_id));
        //如果未点赞可以点赞
        //数据库相应字段加一
        //保存到redis的set中
        if (Boolean.FALSE.equals(isMember)) {
            //进行isPresent检查
            Optional<TbArticleEntity> articleEntity = articleDao.findById(id);
            if (articleEntity.isPresent()) {
                TbArticleEntity tbArticleEntity = articleEntity.get();
                tbArticleEntity.setLikes(tbArticleEntity.getLikes() + 1);
                articleDao.save(tbArticleEntity);
                stringRedisTemplate.opsForSet().add(key, Integer.toString(user_id));
                return "点赞成功";
            } else {
                return "文章不存在";
            }
        }
        //如果已点赞可以取消点赞
        //数据库对应字段减一
        //从redis的set中删除
        else {
            //进行isPresent检查
            Optional<TbArticleEntity> articleEntity = articleDao.findById(id);
            if (articleEntity.isPresent()) {
                TbArticleEntity tbArticleEntity = articleEntity.get();
                tbArticleEntity.setLikes(tbArticleEntity.getLikes() - 1);
                articleDao.save(tbArticleEntity);
                stringRedisTemplate.opsForSet().remove(key, Integer.toString(user_id));
                return "取消点赞成功";
            } else {
                return "文章不存在";
            }
        }
    }
}

