package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 展驰
 * @version 1.0
 * 2022/10/06
 */
public interface UserDao extends JpaRepository<TbUserEntity,Integer> {

}
