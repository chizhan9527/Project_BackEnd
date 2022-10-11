package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociationDao extends JpaRepository<TbAssociationEntity,Integer>{
    Optional<TbAssociationEntity> findByName(String name);
}
