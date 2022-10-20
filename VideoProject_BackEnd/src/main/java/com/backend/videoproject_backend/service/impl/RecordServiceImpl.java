package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.RecordDao;
import com.backend.videoproject_backend.dto.TbRecordEntity;
import com.backend.videoproject_backend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public void addRecord(TbRecordEntity tbRecordEntity)
    {
        recordDao.save(tbRecordEntity);
    }
}
