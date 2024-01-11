package com.t3h.web_demo.controller;

import com.t3h.web_demo.storage.dto.Person;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller // xác định file điều hướg req/res dạng restful
@Log4j // log in ra console thay cho system.out
public class PersonController {

    //tạo req GET, // hứng data trong body request
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            value = "/example/handler")
    @ResponseBody
    public ResponseEntity<String> handleDataOnBody(@RequestBody Person person) {
        // @RequestBody mặc định hứng đầu vào dạng json
        return ResponseEntity.ok("data body " + person);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            value = "/example/handler2")
    @ResponseBody
    public ResponseEntity<String> handleDataOnBody2(String name, String address) {
        // hứng đầu vào dạng form-data
        return ResponseEntity.ok("data form " + name + address);
    }

    @GetMapping("person-form-register")
    public ModelAndView form() {
        //trả đến file person_register.html
        //ModelAndView: điều hướng file html, tempalte engine là thymeleaf
        // spring-boot thymeleaf: tự động cấu hình ăn html trong resource/templetes
        return new ModelAndView("person_register");
    }

}
