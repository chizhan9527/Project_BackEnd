package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.vo.FollowBox;

import java.util.List;

public interface FollowService {

    String doFollow(Integer userId, Integer followUserId);

    String doUnfollow(Integer userId,Integer followUserId);

    String followOrNot(Integer userId,Integer followUserId);

    List<TbUserEntity> getCommonFollow(Integer userId,Integer id);

    List<FollowBox> getFollowInfo(Integer id);
}
