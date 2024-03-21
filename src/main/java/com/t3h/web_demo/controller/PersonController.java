package com.t3h.web_demo.controller;

import com.t3h.web_demo.storage.dto.Person;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller // xác định file điều hướg req/res dạng restful
@Slf4j // log in ra console thay cho system.out
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

    @GetMapping(value = {"/form-login", "/login"})
    public ModelAndView formLogin() {
        return new ModelAndView("form_login");
    }

    @PostMapping("/api/login")
    public ResponseEntity<Map> login(@RequestBody Map loginRequest) {
        String username = (String) loginRequest.get("username");
        String pw = (String) loginRequest.get("pw");
        // ktra user/pw -> rỗng -> trả code 401 + message dạng json
        if (StringUtils.isAnyBlank(username, pw)) {
            return new ResponseEntity<>(
                    Map.of("errorMessage", "username / pw empty",
                            "code", 401),
                    HttpStatus.UNAUTHORIZED
            );
        }

        return ResponseEntity.ok(
                Map.of("errorMessage", "",
                        "code", 200));
    }

    @GetMapping("persons")
    public ModelAndView personsView() {
        ModelAndView personsView = new ModelAndView("persons");
        List<Map> persons = new ArrayList<>();

        persons.add(
                Map.of(
                        "id", 1,
                        "name", "th",
                        "birthDay", LocalDate.now()
                )
        );
        persons.add(
                Map.of(
                        "id", 2,
                        "name", "aaaa",
                        "birthDay", LocalDate.now()
                )
        );

        // thêm persons vào view
        personsView.addObject("persons", persons);
        personsView.addObject("count", persons.size());

        return personsView;
    }

    @GetMapping("persons/{personId}")
    public ModelAndView personsView(@PathVariable Integer personId) {
        ModelAndView personsView = new ModelAndView("persons");
        Map person = Map.of(
                "id", 1,
                "name", "thasdas",
                "birthDay", LocalDate.now()
        );

        // thêm person vào view
        personsView.addObject("person", person);

        return personsView;
    }

}
