package com.backend.videoproject_backend.service;
import com.backend.videoproject_backend.dto.TbAssociationEntity;

public interface AssociationService {
    void addClub(TbAssociationEntity association);

    void delClub(Integer id);

    void changeName(String name);

    void changeDesc(String desc);

    void joinClub();
}
