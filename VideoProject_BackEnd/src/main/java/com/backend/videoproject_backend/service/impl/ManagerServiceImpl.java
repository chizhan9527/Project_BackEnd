package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dao.ManagerDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.*;

@Service
public class ManagerServiceImpl implements ManagerService  {

    @Autowired
    private ManagerDao managerDao;
    @Override
    public boolean joinClub(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional){
        try{
            if (associationEntityOptional.isEmpty())
                return false;

            if(userEntityOptional.isEmpty())
                return false;

/*             TbManagerEntity tbManagerEntity = new TbManagerEntity();
             tbManagerEntity.setAsId(AssociationService.findAssociationById(as_id).get().getId());
             tbManagerEntity.setUserId(user_id);
             tbManagerEntity.setJoinTime(new Timestamp(new Date().getTime()));
             tbManagerEntity.setStatus(0);
             managerService.joinClub(tbManagerEntity);
             Optional<TbAssociationEntity> tbAssociationEntity = AssociationService.findAssociationById(as_id);
             if (tbAssociationEntity.isPresent()) {
                 TbAssociationEntity tbAssociationEntity1 = tbAssociationEntity.get();
                 tbAssociationEntity1.setMemberNum(tbAssociationEntity1.getMemberNum() + 1);
                 AssociationService.updateAssociation(tbAssociationEntity1);
             }
             return ("OK");*/
            TbManagerEntity tbManagerEntity = new TbManagerEntity();
            tbManagerEntity.setAsId(associationEntityOptional.get().getId());
            tbManagerEntity.setUserId(userEntityOptional.get().getId());
            tbManagerEntity.setJoinTime(new Timestamp(new Date().getTime()));
            tbManagerEntity.setStatus(0);
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
