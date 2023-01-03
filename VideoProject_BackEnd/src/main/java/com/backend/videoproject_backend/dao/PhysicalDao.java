package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbPhysicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysicalDao extends JpaRepository<TbPhysicalEntity,Integer> {

    Optional<TbPhysicalEntity> findByUserId(Integer userId);
}
