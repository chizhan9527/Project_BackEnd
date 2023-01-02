package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dao.ManagerDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService  {

    @Autowired
    private ManagerDao managerDao;
    @Override
    public void joinClub(TbManagerEntity tbManagerEntity){
        managerDao.save(tbManagerEntity);
    }

    @Override
    public void quitClub(Integer as_id,Integer user_id){
        managerDao.deleteByAsIdAndUserId(as_id,user_id);
    }

    @Override
    public List<TbManagerEntity> ReturnAllMember(Integer as_id){
        return managerDao.findAllByAsId(as_id);
    }

    @Override
    public TbManagerEntity ReturnOneMember(Integer as_id,Integer user_id)
    {
        return managerDao.findByAsIdAndUserId(as_id,user_id);
    }

    @Override
    public List<TbManagerEntity> ReturnJoinedClub(Integer user_id){
        return managerDao.findAllByUserId(user_id);
    }
    @Override
    public void updateManager(TbManagerEntity tbManagerEntity){
        managerDao.save(tbManagerEntity);

    }
}
