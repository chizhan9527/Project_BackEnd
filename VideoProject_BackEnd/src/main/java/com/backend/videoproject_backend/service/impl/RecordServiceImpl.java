package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.RecordDao;
import com.backend.videoproject_backend.dto.TbRecordEntity;
import com.backend.videoproject_backend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;


    @Override
    public boolean addRecord(TbRecordEntity tbRecordEntity)
    {
        try {
            recordDao.save(tbRecordEntity);
            return true;
        }catch (Exception e) {
            return false;

        }
    }

    @Override
    public List<TbRecordEntity> findByUserId(Integer userId)
    {
        return recordDao.findByUserId(userId);
    }


}
