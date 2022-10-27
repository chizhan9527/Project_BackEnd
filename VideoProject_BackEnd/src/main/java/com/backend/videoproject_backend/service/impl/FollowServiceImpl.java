package com.backend.videoproject_backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.backend.videoproject_backend.dao.FollowDao;
import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbFollowEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.FollowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public String follow(Integer followUserId) {
        int userId = StpUtil.getLoginIdAsInt();
        //1.判断是否已经关注
        Boolean isFollow = isFollow(followUserId);
        if(!isFollow){
            //2.若没有关注，新增数据
            TbFollowEntity followEntity = new TbFollowEntity();
            followEntity.setUserId(userId);
            followEntity.setFollowerId(followUserId);
            followEntity.setCreateTime(new Timestamp(new Date().getTime()));
            followDao.save(followEntity);
            //3.更新redis
            stringRedisTemplate.opsForSet().add("follows:"+userId,followUserId.toString());
            return "关注成功";
        }else{
            //3.否则取消关注，删除数据
            followDao.deleteByUserIdAndFollowerId(userId, followUserId);
            //4.更新redis
            stringRedisTemplate.opsForSet().remove("follows:"+userId,followUserId.toString());
            return "取消关注成功";
        }
    }

    @Override
    public String followOrNot(Integer followUserId) {
        if(isFollow(followUserId)){
            return "true";
        }else{
            return "false";
        }
    }

    @Override
    public List<TbUserEntity> getCommonFollow(Integer id) {
        int userId = StpUtil.getLoginIdAsInt();
        String key1 = "follows:"+userId;
        String key2 = "follows:"+id;
        //求交集
        Set<String> commonFollows = stringRedisTemplate.opsForSet().intersect(key1, key2);
        if(commonFollows == null || commonFollows.isEmpty()){
            return new ArrayList<>();
        }
        //解析集合
        List<Integer> ids = commonFollows.stream().map(Integer::valueOf).toList();
        //查询用户信息
        List<TbUserEntity> tbUserEntityList = new ArrayList<>();
        ids.forEach(
                i -> {
                    Optional<TbUserEntity> userEntity = userDao.findById(i);
                    userEntity.ifPresent(tbUserEntityList::add);
                }
        );
        return tbUserEntityList;
    }

    private Boolean isFollow(Integer followUserId) {
        //查询是否已经关注
        int userId = StpUtil.getLoginIdAsInt();
        //得到count值
        int count = followDao.countByUserIdAndFollowerId(userId, followUserId);
        return count > 0;
    }

}

