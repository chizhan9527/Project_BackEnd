package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.config.hiberBegin;
import com.backend.videoproject_backend.dto.TbManagerEntity;

public class AssociationDao  extends hiberBegin {
    public void updateName(String name)
    {

        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TbAssociationEntity club=session.get(TbAssociationEntity.class,6);
        club.setAssociationName(name);
        session.update(club);
        ts.commit();
        closeConn();

    }

    /*
    public void createAssociation(String name)
    {
        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TbAssociationEntity club=new TbAssociationEntity();
        club.setAssociationName(name);
        session.save(club);
        ts.commit();
        closeConn();
    }
    public void createAssociation(String name,String desc)
    {
        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TbAssociationEntity club=new TbAssociationEntity();
        club.setAssociationName(name);
        club.setAssociationDesc(desc);
        session.save(club);
        ts.commit();
        closeConn();
    }
    */
    public void create(TbAssociationEntity club)
    {
        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        session.save(club);
        ts.commit();
        closeConn();
    }
}
