package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDao extends JpaRepository<TbFeedbackEntity,Integer> {
}
