package com.backend.videoproject_backend.dao;

import com.backend.videoproject_backend.config.hiberBegin;
import com.backend.videoproject_backend.dto.TTestEntity;

public class Test1 extends hiberBegin {
    public void updateTest(String email)
    {

        creatConn();
        org.hibernate.Transaction ts=session.beginTransaction();
        TTestEntity test=session.get(TTestEntity.class,3);

        test.setName("11");

        session.update(test);
        ts.commit();
        closeConn();

    }

}
