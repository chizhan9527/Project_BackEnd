package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    boolean joinClub(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional);

    boolean quitClub(Integer as_id,Integer user_id);

    List<TbManagerEntity> ReturnAllMember(Integer as_id);

    TbManagerEntity ReturnOneMember(Integer as_id,Integer user_id);

    List<TbManagerEntity> ReturnJoinedClub(Integer user_id);

    boolean updateManager(TbManagerEntity tbManagerEntity);
}
