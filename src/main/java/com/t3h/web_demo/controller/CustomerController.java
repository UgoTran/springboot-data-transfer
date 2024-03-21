package com.t3h.web_demo.controller;

import com.t3h.web_demo.service.CustomerService;
import com.t3h.web_demo.storage.entity.CustomerEntity;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController // xác định file điều hướg req/res dạng restful
@Slf4j // log in ra console thay cho system.out
public class CustomerController {
    @Autowired
    CustomerService customerService;

    // ko ap dung phan trang
    @GetMapping("/api/v1/customer")
    public ResponseEntity<List<CustomerEntity>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/api/v1/customer2")
    public ResponseEntity<List<CustomerEntity>> findAll2(@RequestParam int pNumber, // 0 ++
                                                         @RequestParam int size, // 0 ++
                                                         @RequestParam String direction, // ASC, DESC
                                                         @RequestParam String properties // ten cot
    ) {

        return ResponseEntity.ok(customerService.findAll(PageRequest.of(pNumber, size, Sort.by(Sort.Direction.valueOf(direction), "customerNumber"))));
    }


}
