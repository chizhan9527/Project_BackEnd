package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<TbArticleEntity,Integer> {

}
