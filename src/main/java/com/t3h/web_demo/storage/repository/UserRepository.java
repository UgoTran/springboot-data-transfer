package com.t3h.web_demo.storage.repository;//package com.t3h.web_demo.storage.repository;


import com.t3h.web_demo.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity  findByUserName(String username);

    @Query(nativeQuery = true,
    value = "SELECT r.name FROM `user` u  " +
            "INNER JOIN `role` r ON u.user_id  = r.id " +
            "WHERE  u.user_id = ?"
    )
    List<String> findRoleByUserId(int userId);
}
