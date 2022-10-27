package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowDao extends JpaRepository<TbFollowEntity, Integer> {
    void deleteByUserIdAndFollowerId(int userId, Integer followUserId);

    int countByUserIdAndFollowerId(int userId, Integer followUserId);

    List<TbFollowEntity> findAllByFollowerId(int user_id);
}

