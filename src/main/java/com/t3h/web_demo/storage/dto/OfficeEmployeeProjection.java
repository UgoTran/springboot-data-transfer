package com.t3h.web_demo.storage.dto;

public interface OfficeEmployeeProjection {
    // tạo method lấy data
    Long getEmployeeNumber(); // getxxx: xxx tên cột, mỗi chữ cái đầu mỗi từ viết hoa, hoặc dùng alias
    String getEmployeeFirstName();
    String getEmployeeEmail();
    String getOfficeCode();
    String getAddressLine1();
    String getCity();
}
