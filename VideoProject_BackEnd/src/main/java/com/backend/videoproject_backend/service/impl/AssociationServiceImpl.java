package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationServiceImpl implements AssociationService {
    @Autowired
    private AssociationDao associationDao;

    @Override
    public void addClub(TbAssociationEntity tbAssociationEntity)
    {
        associationDao.save(tbAssociationEntity);
    }

    /*@Override
    public void delClub(Integer id){clubDao.delete(id);}

    @Override
    public void changeName(Integer id,String name){clubDao.updateName(id,name);}

    @Override
    public void changeDesc(Integer id,String desc){clubDao.changeDesc(id,desc);}*/

}
