package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author 展驰
 * @version 1.0
 * 2022/10/06
 */
public interface UserDao extends JpaRepository<TbUserEntity,Integer> {
    Optional<TbUserEntity> findByName(String name);

    Optional<TbUserEntity> findByPhone(String phone);

}
