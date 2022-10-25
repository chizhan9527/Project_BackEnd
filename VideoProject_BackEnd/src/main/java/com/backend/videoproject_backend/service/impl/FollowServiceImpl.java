package com.backend.videoproject_backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.backend.videoproject_backend.dao.FollowDao;
import com.backend.videoproject_backend.dto.TbFollowEntity;
import com.backend.videoproject_backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;

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
            return "关注成功";
        }else{
            //3.否则取消关注，删除数据
            followDao.deleteByUserIdAndFollowerId(userId, followUserId);
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

    private Boolean isFollow(Integer followUserId) {
        //查询是否已经关注
        int userId = StpUtil.getLoginIdAsInt();
        //得到count值
        int count = followDao.countByUserIdAndFollowerId(userId, followUserId);
        return count > 0;
    }

}

