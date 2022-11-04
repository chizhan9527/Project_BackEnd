package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.RecordDao;
import com.backend.videoproject_backend.dao.UserRecordDao;
import com.backend.videoproject_backend.dto.TbRecordEntity;
import com.backend.videoproject_backend.dto.VUserRecordEntity;
import com.backend.videoproject_backend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Autowired
    private UserRecordDao userRecordDao;

    @Override
    public void addRecord(TbRecordEntity tbRecordEntity)
    {
        recordDao.save(tbRecordEntity);
    }

    @Override
    public List<TbRecordEntity> findByUserId(Integer userId)
    {
        return recordDao.findByUserId(userId);
    }

    @Override
    public List<VUserRecordEntity> findUserRecordDesc() {
        return userRecordDao.findByOrderBySumDistanceDesc();
    }

}
