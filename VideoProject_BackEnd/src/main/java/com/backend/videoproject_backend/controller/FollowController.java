package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dao.FollowDao;
import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbFollowEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.FollowService;
import com.backend.videoproject_backend.vo.FollowBox;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Api(tags = "关注管理")
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private FollowDao followDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDao userDao;

    @PostMapping("/follow")
    @ResponseBody
    @ApiOperation("关注/取消关注")
    public String follow(Integer userId, Integer followUserId)
    {
        //1.判断是否已经关注
        String isFollow = followService.followOrNot(userId,followUserId);
        if(isFollow.equals("False")){
            return followService.doFollow(userId,followUserId);
        }
        else if(isFollow.equals("True")){
            return followService.doUnfollow(userId,followUserId);
        }
        else {
            return "Invalid input";
        }
    }

    @GetMapping("/follow/or/not/{followUserId}")
    @ResponseBody
    @ApiOperation("是否已经关注")
    public String followOrNot(Integer userId,@PathVariable Integer followUserId)
    {
        return followService.followOrNot(userId,followUserId);
    }

    @GetMapping("/follow/common/{id}")
    @ResponseBody
    @ApiOperation("获取共同关注")
    public List<TbUserEntity> getCommonFollow(Integer userId,@PathVariable Integer id)
    {
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

    @GetMapping("/follow/{id}")
    @ResponseBody
    @ApiOperation("获取用户关注的人")
    public List<FollowBox> getUserFollowers(@PathVariable Integer id)
    {
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


}
