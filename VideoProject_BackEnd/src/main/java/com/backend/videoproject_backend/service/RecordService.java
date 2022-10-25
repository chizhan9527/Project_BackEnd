package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbRecordEntity;

import java.util.List;

public interface RecordService {
    void addRecord(TbRecordEntity tbRecordEntity);

    List<TbRecordEntity> findByUserId(Integer userId);

}
