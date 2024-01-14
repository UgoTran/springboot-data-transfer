package com.t3h.web_demo.service.impl;

import com.t3h.web_demo.service.OfficeService;
import com.t3h.web_demo.storage.entity.OfficeEntity;
import com.t3h.web_demo.storage.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * xxxService: quy ước đăt tên
 * nhiệm vụ: xử lý logic liên quan đến office
 * class triển khai logic của office & kế thừa interface tương ứng
 * nhúng repo tương ứng dùng @Autowire, lấy dữ liệu xử lý
 */
@Service // định nghĩa class ở tầng service & phục để nhúng vào controller / service khác
public class OfficeServiceImpl implements OfficeService {

    // nhúng repo office, ko cần dùng từ 'new'
    @Autowired
    private OfficeRepository officeRepo;

    @Override
    public List<OfficeEntity> getAll() {
        // xxrepo.findAll = select * from offices
        // hàm có sẳn trong module spring data, cụ thể I JpaRepository
        List<OfficeEntity> offices = officeRepo.findAll();

        return offices;
    }

    @Override
    public OfficeEntity getByOfficeCode(String code) {
        return officeRepo.getByOfficeCode(code);
    }
}
