package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;

public class AssociationServiceImpl implements AssociationService {
    @Autowired
    private AssociationDao clubDao;

    @Override
    public void addClub(TbAssociationEntity association){clubDao.create(association);};

    @Override
    public void delClub(Integer id){};

    @Override
    public void changeName(String name){};

    @Override
    public void changeDesc(String desc){};

    @Override
    public void joinClub(){};
}
