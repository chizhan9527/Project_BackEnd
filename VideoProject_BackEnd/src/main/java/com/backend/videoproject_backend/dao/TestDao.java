package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 展驰
 * @version 1.0
 * 2022/09/15
 */
public interface TestDao extends JpaRepository <Test,Integer> {
}
