package com.t3h.web_demo.service;

import com.t3h.web_demo.storage.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> findAll();

    /**
     * @param pageable: đối tượng  chứa tt phân trang
     * @return
     */
    List<CustomerEntity> findAll(Pageable pageable);
}
