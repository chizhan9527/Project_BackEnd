package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<TbUserEntity,Integer> {

}
