package com.backend.videoproject_backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.backend.videoproject_backend.dao.ArticleDao;
import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbArticleEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

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

    @Override
    public TbArticleEntity getArticleById(Integer id) {
        //进行isPresent检查
        Optional<TbArticleEntity> articleEntity = articleDao.findById(id);
        if (articleEntity.isPresent()) {
            TbArticleEntity tbArticleEntity = articleEntity.get();
            //查询article有关的用户
            queryArticleUser(tbArticleEntity);
            //查询是否被点过赞
            isArticleLiked(tbArticleEntity);
            return tbArticleEntity;
        }
        return null;
    }

    //查询article有关用户
    private void queryArticleUser(TbArticleEntity tbArticleEntity)
    {
        int user_id = tbArticleEntity.getUserId();
        Optional<TbUserEntity> userEntity = userDao.findById(user_id);
        if (userEntity.isPresent()) {
            TbUserEntity tbUserEntity = userEntity.get();
            tbArticleEntity.setName(tbUserEntity.getName());
            tbArticleEntity.setAvatar(tbUserEntity.getAvator());
        }
    }

    private void isArticleLiked(TbArticleEntity tbArticleEntity)
    {
        int user_id = tbArticleEntity.getUserId();
        String key = "article:like" + tbArticleEntity.getId();
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key, Integer.toString(user_id));
        tbArticleEntity.setLike(Boolean.TRUE.equals(isMember));
    }

    @Override
    public void addArticle(String context) {
        //用saToken获取用户当前id
        int user_id = StpUtil.getLoginIdAsInt();
        TbArticleEntity tbArticleEntity = new TbArticleEntity();
        tbArticleEntity.setContext(context);
        tbArticleEntity.setLikes(0);
        tbArticleEntity.setUserId(user_id);
        //设置时间
        tbArticleEntity.setCreateTime(new Timestamp(new Date().getTime()));
        tbArticleEntity.setType("article");
        articleDao.save(tbArticleEntity);
    }
}

