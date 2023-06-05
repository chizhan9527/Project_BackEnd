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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDao userDao;

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

    @Override
    public List<TbUserEntity> getCommonFollow(Integer userId,Integer id) {
        if(userId<=0||id<=0||userId>=100000000||id>=100000000||userId.equals(id))
            return null;
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

    @Override
    public List<FollowBox> getFollowInfo(Integer id) {
        if(id<=0||id>=100000000)
            return null;
        // 存储followBox的结果
        List<FollowBox> followBoxList = new ArrayList<>();
        // 用id查到所有的关注表实体
        List<TbFollowEntity> tbFollowEntityList = followDao.findAllByUserId(id);
        // 取得follow_id的idList
        List<Integer> ids = tbFollowEntityList.stream().map(TbFollowEntity::getFollowerId).toList();
        ids.forEach(
                i ->{
                    Optional<TbUserEntity> userEntity = userDao.findById(i);
                    if(userEntity.isPresent()){
                        FollowBox followBox = new FollowBox();
                        followBox.setId(i);
                        followBox.setName(userEntity.get().getName());
                        followBox.setAvatar(userEntity.get().getAvator());
                        followBoxList.add(followBox);
                    }
                }
        );
        return followBoxList;
    }

    private Boolean isFollow(Integer userId,Integer followUserId) {
        if(userId<=0||followUserId<=0||userId>=100000000||followUserId>=100000000|| userId.equals(followUserId))
            return false;
        //得到count值
        int count = followDao.countByUserIdAndFollowerId(userId, followUserId);
        return count > 0;
    }

}

