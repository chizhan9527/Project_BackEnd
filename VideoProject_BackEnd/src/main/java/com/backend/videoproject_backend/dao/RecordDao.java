package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author 展驰
 * @version 1.0
 * 2022/10/20
 */
public interface RecordDao extends JpaRepository<TbRecordEntity,Integer> {
    List<TbRecordEntity> findByUserId(int userId);



}

