package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "关注管理")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PutMapping("/follow/{followUserId}")
    @ResponseBody
    @ApiOperation("关注/取消关注")
    public String follow(@PathVariable Integer followUserId)
    {
        return followService.follow(followUserId);
    }

    @GetMapping("/follow/or/not/{followUserId}")
    @ResponseBody
    @ApiOperation("是否已经关注")
    public String followOrNot(@PathVariable Integer followUserId)
    {
        return followService.followOrNot(followUserId);
    }

    @GetMapping("/follow/common/{id}")
    @ResponseBody
    @ApiOperation("获取共同关注")
    public List<TbUserEntity> getCommonFollow(@PathVariable Integer id)
    {
        return followService.getCommonFollow(id);
    }
}
