package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerDao extends JpaRepository<TbManagerEntity,Integer>{

}
