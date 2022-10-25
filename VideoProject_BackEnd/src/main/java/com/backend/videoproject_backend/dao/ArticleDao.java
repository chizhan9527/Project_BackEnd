package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ArticleDao extends JpaRepository<TbArticleEntity,Integer> {

    List<TbArticleEntity> findAllByOrderByCreateTimeDesc(Pageable pageable);

}
