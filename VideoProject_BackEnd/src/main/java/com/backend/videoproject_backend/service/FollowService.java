package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbUserEntity;

import java.util.List;

public interface FollowService {
    String follow(Integer followUserId);

    String followOrNot(Integer followUserId);

    List<TbUserEntity> getCommonFollow(Integer id);
}
