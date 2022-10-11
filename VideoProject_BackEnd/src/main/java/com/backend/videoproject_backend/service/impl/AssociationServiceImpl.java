package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationServiceImpl implements AssociationService {
    @Autowired
    private AssociationDao associationDao;

    @Override
    public void addClub(TbAssociationEntity tbAssociationEntity)
    {
        associationDao.save(tbAssociationEntity);
    }

    @Override
    public void delClub(Integer id){associationDao.deleteById(id);}

    public Optional<TbAssociationEntity> findAssociationById(Integer id){
        return associationDao.findById(id);
    }

    @Override
    public List<TbAssociationEntity> findAllClub()
    {
        return associationDao.findAll();
    }
    @Override
    public void updateAssociation(TbAssociationEntity tbAssociationEntity){
        associationDao.save(tbAssociationEntity);
    }

    public Optional<TbAssociationEntity> findUserByName(String name){
        return associationDao.findByName(name);
    };
}
