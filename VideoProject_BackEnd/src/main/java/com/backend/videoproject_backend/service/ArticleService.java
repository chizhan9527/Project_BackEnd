package com.backend.videoproject_backend.service;


import com.backend.videoproject_backend.dto.TbArticleEntity;

import java.util.List;

public interface ArticleService {
    String likeArticle(Integer id);

    TbArticleEntity getArticleById(Integer id);

    void addArticle(String context);

    List<TbArticleEntity> getByPageService(Integer currentPage);

}
