package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.Test;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 展驰
 * @version 2.0
 * 2022/09/28
 */
public interface TestDao extends JpaRepository <Test,Integer> {
}
