package com.t3h.web_demo.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "user") // mapping entity với bảng, name = xxx dùng khi 2 tên khác nhau
@Entity
@Data
public class UserEntity {
    @Id
    @Column(name = "user_id")
    public Integer userId;
    public String password;
    @Column(name = "user_name")
    public String userName;
    public String email;
}
