package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.Test;

/**
 * @author 展驰
 * @version 1.0
 * 2022/09/15
 */
public interface TestService {
    void addTest(Test test);

    void delTest(Integer id);
}
