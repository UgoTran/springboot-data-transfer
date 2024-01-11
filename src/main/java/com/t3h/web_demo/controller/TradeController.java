package com.t3h.web_demo.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // xác định file điều hướg req/res dạng restful
@Log4j // log in ra console thay cho system.out
public class TradeController {

    //tạo req GET
    @GetMapping("/eportal/trades") // hứng req theo đường dẫn
    public ResponseEntity<String> trades(@RequestParam Integer tradeId) {//hướng tham số trên url
        return ResponseEntity.ok("method GET: Id truyền vào là: " + tradeId);
    }

    //tạo req Post
    @PostMapping("/eportal/trades")
    public ResponseEntity<String> trades2(@RequestParam Integer tradeId) {
        return ResponseEntity.ok("method POST: Id truyền vào là: " + tradeId);
    }

    //tạo req Delete
    @DeleteMapping("/eportal/trades")
    public ResponseEntity<String> trades3(@RequestParam Integer tradeId) {
        return ResponseEntity.ok("method DELETE: Id truyền vào là: " + tradeId);
    }

}
