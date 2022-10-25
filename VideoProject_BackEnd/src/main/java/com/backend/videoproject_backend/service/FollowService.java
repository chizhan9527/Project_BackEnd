package com.backend.videoproject_backend.service;

public interface FollowService {
    String follow(Integer followUserId);

    String followOrNot(Integer followUserId);
}
