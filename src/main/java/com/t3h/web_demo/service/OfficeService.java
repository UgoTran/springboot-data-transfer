package com.t3h.web_demo.service;

import com.t3h.web_demo.storage.entity.OfficeEntity;

import java.util.List;

/**
 * xxxService: quy ước đăt tên
 * interface định nghĩa các tên hành động, logic liên quan
 */
public interface OfficeService {
    List<OfficeEntity> getAll();

    OfficeEntity getByOfficeCode(String code);

}
