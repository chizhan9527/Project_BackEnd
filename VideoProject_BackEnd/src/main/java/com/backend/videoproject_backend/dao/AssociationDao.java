package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.config.hiberBegin;
import com.backend.videoproject_backend.dto.TbManagerEntity;

public class AssociationDao  extends hiberBegin {
    public void updateName(Integer id, String name)
    {

        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TbAssociationEntity club=session.get(TbAssociationEntity.class,id);
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

    public void delete(Integer id)
    {
        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TbAssociationEntity club=session.get(TbAssociationEntity.class,id);
        session.delete(club);
        ts.commit();
        closeConn();
    }

    public void changeDesc(Integer id,String desc)
    {
        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TbAssociationEntity club=session.get(TbAssociationEntity.class,id);
        club.setAssociationDesc(desc);
        ts.commit();
        closeConn();
    }
}
