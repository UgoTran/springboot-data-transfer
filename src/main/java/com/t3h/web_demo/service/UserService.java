package com.t3h.web_demo.service;

import com.t3h.web_demo.storage.entity.UserEntity;

/**
 * CRUD user
 */
public interface UserService {

    UserEntity findUser(String username, String pw);
}
