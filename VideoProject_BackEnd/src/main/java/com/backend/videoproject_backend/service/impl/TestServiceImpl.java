package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.Test1;
import com.backend.videoproject_backend.dao.TestDao;
import com.backend.videoproject_backend.dto.TTestEntity;
import com.backend.videoproject_backend.dto.Test;
import com.backend.videoproject_backend.service.TestService;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 展驰
 * @version 1.0
 * 2022/09/15
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    private Test1 test1;

    public TestServiceImpl()
    {
        test1=new Test1();
    }

    @Override
    public void addTest(Test test){
        testDao.save(test);
    }

    @Override
    public void delTest(Integer id){
        testDao.deleteById(id);
    }

    @Override
    public void updateTest(String email)
    {
        test1.updateTest(email);
    }

}
