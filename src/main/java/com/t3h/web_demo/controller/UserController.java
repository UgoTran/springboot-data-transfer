package com.t3h.web_demo.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // xác định file điều hướg req/res dạng restful
@Log4j // log in ra console thay cho system.out
public class UserController {

    //tạo req GET
    @GetMapping("/user/{userId}") // hứng req theo đường dẫn path
    public ResponseEntity<String> user(@PathVariable Integer userId) { // tên biến trùng tham số hứng userId
        return ResponseEntity.ok("method GET, PathVariable: userId truyền vào là: " + userId);
    }

    //tạo req POST
    @GetMapping("/user/{userId}/albums/{albumId}") // hứng req theo đường dẫn path
    public ResponseEntity<String> albumUser(@PathVariable Integer userId,
                                            @PathVariable Integer albumId) { // tên biến trùng tham số hứng userId, albumId
        return ResponseEntity.ok("method POST, PathVariable: userId, AlbumId: " + userId + "; " + albumId);
    }
}
