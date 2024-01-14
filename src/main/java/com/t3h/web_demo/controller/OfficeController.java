package com.t3h.web_demo.controller;

import com.t3h.web_demo.service.OfficeService;
import com.t3h.web_demo.storage.entity.OfficeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xử lý diều hướng client, diều hướng service
 * xxxController: quy ước đặt tên
 */
@Controller
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    /**
     * "/api/v1/office": api - xác định api; v1: phiên bản; offices: lấy thứ gì liên quan office
     */
    @GetMapping("/api/v1/offices")
    @ResponseBody // trả kq ra body dạng json mặc định
    public ResponseEntity<List<OfficeEntity>> offices() {
        return ResponseEntity.ok(officeService.getAll());

    }

    @GetMapping("/api/v1/offices/{officeCode}")
    @ResponseBody // trả kq ra body dạng json mặc định
    public ResponseEntity<OfficeEntity> officeByCode(@PathVariable String officeCode) {
        return ResponseEntity.ok(officeService.getByOfficeCode(officeCode));

    }
}
