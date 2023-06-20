package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.FeedbackDao;
import com.backend.videoproject_backend.dao.PhysicalDao;
import com.backend.videoproject_backend.dao.UserDao;
import com.backend.videoproject_backend.dto.TbFeedbackEntity;
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

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public String addUser(TbUserEntity tbUserEntity)
    {
        if(tbUserEntity.getName()==null)return "userName input error";
        else if(tbUserEntity.getPhone().length()!=11)return "phone error";
        else if(tbUserEntity.getPhone().contains("a")) return "phone error";
        else if(tbUserEntity.getPassword().length()<4)return "password too short";
        else if(tbUserEntity.getAvator().length()<8)return "url invalid";
        else if(tbUserEntity.getCreateTime()==null)return "time no format";
        //userDao.save(tbUserEntity);
        return "register success";
    }

    @Override
    public void deleteUser(Integer id)
    {
        //userDao.deleteById(id);
    }

    @Override
    public String deleteUser2(Integer id)
    {
        if(id<=0||id>=100000000)
            return "invalidInput";
        if(findUserById(id).isEmpty())
            return "userNotFound";
        return "ok";
        //userDao.deleteById(id);
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
        //userDao.save(tbUserEntity);
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
        //physicalDao.save(tbPhysicalEntity);
    }

    @Override
    public void postFeedback(TbFeedbackEntity tbFeedbackEntity) {
        //feedbackDao.save(tbFeedbackEntity);
    }

    @Override
    public String updateUser2(Integer id, Integer gender, String email) {
        try {
            if(id>=100000000||id<=0)
                return "id invalid";
            Optional<TbUserEntity> target = findUserById(id);
            if(target.isEmpty()){
                return "用户不存在";
            }
            if(gender!=null)
                if(gender!=0&&gender!=1)
                    return "gender invalid";
                else if (email!=null)
                    if(!email.contains(".com"))
                        return "email invalid";
            return "ok";
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
