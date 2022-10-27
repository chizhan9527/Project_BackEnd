package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ManagerDao extends JpaRepository<TbManagerEntity,Integer>{
    @Transactional
    Optional<TbManagerEntity> deleteByAsIdAndUserId(Integer as_id,Integer user_id);

    @Transactional
    List<TbManagerEntity> findAllByAsId(Integer as_id);

    @Transactional
    TbManagerEntity findByAsIdAndUserId(Integer as_id,Integer user_id);
}
