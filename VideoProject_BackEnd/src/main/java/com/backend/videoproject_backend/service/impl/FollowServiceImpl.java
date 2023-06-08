package com.backend.videoproject_backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.backend.videoproject_backend.dao.FollowDao;
import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbFollowEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.FollowService;
import com.backend.videoproject_backend.vo.FollowBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;

    @Override
    public String doFollow(Integer userId,Integer followUserId) {
        if(userId<=0||followUserId<=0||userId>=100000000||followUserId>=100000000||userId.equals(followUserId))
            return "Invalid input";
        TbFollowEntity followEntity = new TbFollowEntity();
        followEntity.setUserId(userId);
        followEntity.setFollowerId(followUserId);
        followEntity.setCreateTime(new Timestamp(new Date().getTime()));
        /* 以下为函数实际执行代码
          followDao.save(followEntity);
          stringRedisTemplate.opsForSet().add("follows:"+userId,followUserId.toString());
         */
        return "Follow success";
    }

    @Override
    public String doUnfollow(Integer userId,Integer followUserId) {
        if(userId<=0||followUserId<=0||userId>=100000000||followUserId>=100000000||userId.equals(followUserId) )
            return "Invalid input";
        /* 以下为函数实际执行代码
        followDao.deleteByUserIdAndFollowerId(userId, followUserId);
        stringRedisTemplate.opsForSet().remove("follows:"+userId,followUserId.toString());
         */
        return "Unfollow success";
    }

    @Override
    public String followOrNot(Integer userId,Integer followUserId) {
        if(userId<=0||followUserId<=0||userId>=100000000||followUserId>=100000000||userId.equals(followUserId))
            return "Invalid input";
        if(isFollow(userId,followUserId)){
            return "True";
        } else{
            return "False";
        }
    }

    private Boolean isFollow(Integer userId,Integer followUserId) {
        if(userId<=0||followUserId<=0||userId>=100000000||followUserId>=100000000|| userId.equals(followUserId))
            return false;
        //得到count值
        int count = followDao.countByUserIdAndFollowerId(userId, followUserId);
        return count > 0;
    }

}

