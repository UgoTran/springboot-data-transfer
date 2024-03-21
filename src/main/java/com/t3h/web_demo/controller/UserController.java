package com.t3h.web_demo.controller;

import com.t3h.web_demo.service.UserService;
import com.t3h.web_demo.storage.entity.UserEntity;
import com.t3h.web_demo.storage.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j // log in ra console thay cho system.out
public class UserController {

    @Autowired
    UserService userService;

    static Map USER_BAD_REQ = Map.of(
            "msg", "empty credentials",
            "httpCode", HttpStatus.BAD_REQUEST
    );

    @PostMapping("/api/v1/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody Map<String, String> payload) {
        //ktra input
        if (payload == null || StringUtils.isAnyBlank(payload.get("username"), payload.get("password"))) {
            return ResponseEntity.badRequest().body(USER_BAD_REQ);

        }

        //ktra trong DB
        UserEntity user = userService.findUser(payload.get("username"), payload.get("password"));
        if (user == null) {
            return ResponseEntity.badRequest().body(USER_BAD_REQ);
        }

        user.setPassword(null);
        return ResponseEntity.ok().body(user);
    }
}
