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
    public boolean joinClub(TbManagerEntity tbManagerEntity){
        try{
            managerDao.save(tbManagerEntity);

        }catch (Exception e) {
            return false;

        }
        return true;
    }

    @Override
    public boolean quitClub(Integer as_id,Integer user_id){
        try{
            managerDao.deleteByAsIdAndUserId(as_id,user_id);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<TbManagerEntity> ReturnAllMember(Integer as_id){
        return managerDao.findAllByAsId(as_id);
    }

    @Override
    public TbManagerEntity ReturnOneMember(Integer as_id,Integer user_id)
    {
        TbManagerEntity res=null;
        try {
            res=managerDao.findByAsIdAndUserId(as_id,user_id);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return res;
    }

    @Override
    public List<TbManagerEntity> ReturnJoinedClub(Integer user_id){
        return managerDao.findAllByUserId(user_id);
    }
    @Override
    public boolean updateManager(TbManagerEntity tbManagerEntity){
        try{
            managerDao.save(tbManagerEntity);
        }
        catch (Exception e){
            return false;

        }
        return true;

    }
}
