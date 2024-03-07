package com.t3h.web_demo.service;

import com.t3h.web_demo.storage.dto.OfficeEmployeeProjection;
import com.t3h.web_demo.storage.entity.OfficeEntity;

import java.util.List;
import java.util.Map;

/**
 * xxxService: quy ước đăt tên
 * interface định nghĩa các tên hành động, logic liên quan
 */
public interface OfficeService {
    List<OfficeEntity> getAll();
    List<Map> getAll2();
    List<OfficeEmployeeProjection> getAll3();

    OfficeEntity getByOfficeCode(String code);
    OfficeEntity getByOfficeCode2(String code);

}
