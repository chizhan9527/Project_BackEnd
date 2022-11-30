package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<TbLikeEntity,Integer> {
    TbLikeEntity findByUserIdAndArticleId(int user_id, Integer id);
}
