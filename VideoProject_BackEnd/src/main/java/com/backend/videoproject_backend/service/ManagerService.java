package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbManagerEntity;

public interface ManagerService {
    void joinClub(TbManagerEntity tbManagerEntity);

    void quitClub(Integer as_id,Integer user_id);
}
