package com.backend.videoproject_backend.service;
import com.backend.videoproject_backend.dto.TbUserEntity;

/**
 * @author 展驰
 * @version 1.0
 * 2022/10/06
 */
public interface UserService {

    void addUser(TbUserEntity tbUserEntity);

    void deleteUser(Integer id);

    /*void findUser();

    void updateUser();*/
}
