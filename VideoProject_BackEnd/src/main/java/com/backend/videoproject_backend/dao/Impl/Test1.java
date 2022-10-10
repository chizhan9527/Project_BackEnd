package com.backend.videoproject_backend.dao.Impl;

import com.backend.videoproject_backend.config.hiberBegin;
import com.backend.videoproject_backend.dto.TTestEntity;

public class Test1 extends hiberBegin {
    public void updateTest(String email)
    {

        creatConn();
        //开启一个事务
        org.hibernate.Transaction ts=session.beginTransaction();

        TTestEntity test=session.get(TTestEntity.class,6);
        test.setEmail("11");
        session.update(test);

        ts.commit();
        closeConn();

    }

}
