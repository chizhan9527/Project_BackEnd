package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.VUserRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRecordDao extends JpaRepository<VUserRecordEntity, Integer> {

    List<VUserRecordEntity> findByOrderBySumDistanceDesc();

}

