package com.t3h.web_demo.storage.repository;

import com.t3h.web_demo.storage.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * xxxRepository: quy ước đặt tên, xxx tên class
 * định nghĩa repo tương ứng với entity, dùng hàm gọi dữ liệu
 * "<OfficeEntity, Integer>": truyền kiểu entity & khóa chính PK theo bảng
 * extends JpaRepository: kế thừa hàm có sẳn gọi DB
 */
public interface OfficeRepository extends JpaRepository<OfficeEntity, String> {

    OfficeEntity getByOfficeCode(String code);
}
