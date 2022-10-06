package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author 展驰
 * @version 1.0
 * 2022/10/06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(TbUserEntity tbUserEntity)
    {
        userDao.save(tbUserEntity);
    }

    @Override
    public void deleteUser(Integer id)
    {
        userDao.deleteById(id);
    }

    @Override
    public List<TbUserEntity> findAllUser()
    {
        return userDao.findAll();
    }

    @Override
    public Optional<TbUserEntity> findUserById(Integer id)
    {
        return userDao.findById(id);
    }

}
