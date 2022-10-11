package com.backend.videoproject_backend.service;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;

import java.util.List;
import java.util.Optional;

public interface AssociationService {
    void addClub(TbAssociationEntity tbAssociationEntity);

    void delClub(Integer id);

    Optional<TbAssociationEntity> findAssociationById(Integer id);

    public List<TbAssociationEntity> findAllClub();

    void updateAssociation(TbAssociationEntity tbAssociationEntity);


}
