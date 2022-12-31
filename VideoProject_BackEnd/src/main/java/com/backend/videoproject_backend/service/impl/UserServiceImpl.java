package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.PhysicalDao;
import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbPhysicalEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.Checksum;


/**
 * @author 展驰
 * @version 1.0
 * 2022/10/06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PhysicalDao physicalDao;

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

    @Override
    public void updateUser(TbUserEntity tbUserEntity)
    {
        userDao.save(tbUserEntity);
    }

    @Override
    public Optional<TbUserEntity> findUserByName(String name)
    {
        return userDao.findByName(name);
    }

    @Override
    public Optional<TbUserEntity> findUserByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public Optional<TbPhysicalEntity> findPhysicalByUserId(Integer id) {
        return physicalDao.findByUserId(id);
    }

    @Override
    public void addPhysical(TbPhysicalEntity tbPhysicalEntity) {
        physicalDao.save(tbPhysicalEntity);
    }
}
