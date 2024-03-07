package com.t3h.web_demo.service.impl;

import com.t3h.web_demo.service.CustomerService;
import com.t3h.web_demo.storage.entity.CustomerEntity;
import com.t3h.web_demo.storage.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // định nghĩa class ở tầng service & phục để nhúng vào controller / service khác
public class CustomerServiceImpl implements CustomerService {
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<CustomerEntity> findAll(Pageable pageable) {
        Page customerPage = customerRepository.findAll(pageable);
        log.info("trang {}, tong pt {}, tong trang {}",
                customerPage.getNumber(), customerPage.getTotalElements(), customerPage.getTotalPages());
        return customerPage.getContent();
    }
}
