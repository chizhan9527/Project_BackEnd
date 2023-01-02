package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbManagerEntity;

import java.util.List;

public interface ManagerService {
    void joinClub(TbManagerEntity tbManagerEntity);

    void quitClub(Integer as_id,Integer user_id);

    List<TbManagerEntity> ReturnAllMember(Integer as_id);

    TbManagerEntity ReturnOneMember(Integer as_id,Integer user_id);

    List<TbManagerEntity> ReturnJoinedClub(Integer user_id);

    void updateManager(TbManagerEntity tbManagerEntity);
}
