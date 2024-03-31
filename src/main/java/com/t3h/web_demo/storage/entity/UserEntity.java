package com.t3h.web_demo.storage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table(name = "user") // mapping entity với bảng, name = xxx dùng khi 2 tên khác nhau
@Entity
@Data
@ToString
public class UserEntity {
    @Id
    @Column(name = "user_id")
    public Integer userId;
    public String password;
    @Column(name = "user_name")
    public String userName;
    public String email;
    public String avatar;

    /*
    ko ảnh hưởng quá trình mapping entity - bảng
    bỏ qua mapping field - cột t/ứ
    @Transient
    List<MediaEntity> media;

     */
}
