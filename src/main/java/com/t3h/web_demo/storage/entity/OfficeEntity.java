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
    @JsonProperty("officecode") // mapping ra json
    private String officeCode;
    // những cột còng lại trùng tên cột, ko cần dùng @Column để định nghĩa cột
    private String city;
    private String phone;
    @JsonProperty("addressline1")
    private String addressLine1;
    @JsonProperty("addressline2")
    private String addressLine2;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postalcode")
    private String postalCode;
    @JsonProperty("territory")
    private String territory;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
}
