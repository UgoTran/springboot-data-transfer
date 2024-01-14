package com.t3h.web_demo.storage.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

/**
 * xxxEntity: quy ước đặt tên có entity đằng sau, xxx: tên bảng
 */
@Table(name = "offices") // mapping entity với bảng, name = xxx dùng khi 2 tên khác nhau
@Entity // xác định class là entity
@Data // tự đn set, get
public class OfficeEntity {
    @Id // mapping field là khóa chính PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("officecode")
    @Column(name = "officecode")
    private String officeCode;
    // những cột còng lại trùng tên cột, ko cần dùng @Column để định nghĩa cột
    @JsonProperty("city") // định nghĩ field mappsing json filed
    private String city;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("addressline1")
    @Column(name = "addressline1")
    private String addressLine1;
    @JsonProperty("addressline2")
    @Column(name = "addressline2")
    private String addressLine2;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postalcode")
    @Column(name = "postalcode")
    private String postalCode;
    @JsonProperty("territory")
    private String territory;

}
