package com.t3h.web_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice // bắt tất cả các lỗi chưa được xử lỵ́

public class Advise {
    // trả kq dạng restful

    @ExceptionHandler(OutOfMemoryError.class)
    ResponseEntity<Map> handlerRuntimeException(OutOfMemoryError ex){
        //bat exception -> custom msg tra
        return ResponseEntity.internalServerError().body(
                Map.of(
                        "error", ex.getClass().getClass(),
                        "msg", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Map> handlerIllegalAccessError(IllegalArgumentException ex){
        //bat exception -> custom msg tra
        return ResponseEntity.internalServerError().body(
                Map.of(
                        "error", ex.getClass().getName(),
                        "msg", ex.getMessage()
                )
        );
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    ModelAndView handlerIllegalAccessError(IllegalArgumentException ex){
//        System.out.println("bat loi IllegalArgumentException > tra ve trang login ");
//        //bat exception -> custom msg tra
//        return new ModelAndView("form_login");
//    }
}
