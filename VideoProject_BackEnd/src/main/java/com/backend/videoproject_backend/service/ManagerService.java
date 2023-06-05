package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    String joinClub(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional);

    String DeleteManager(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional,Optional<TbUserEntity> managerEntityOptional);

    List<Integer> ReturnTragetMembers(Optional<TbAssociationEntity> associationEntityOptional,Integer rank);

    Optional<TbManagerEntity> ReturnOneMember(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional);

    List<TbManagerEntity> ReturnJoinedClub(Optional<TbUserEntity> userEntityOptional);

    String ChangeRank(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional,Optional<TbUserEntity> managerEntityOptional);
}
