package com.t3h.web_demo.storage.repository;

import com.t3h.web_demo.storage.dto.OfficeEmployeeProjection;
import com.t3h.web_demo.storage.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * xxxRepository: quy ước đặt tên, xxx tên class
 * định nghĩa repo tương ứng với entity, dùng hàm gọi dữ liệu
 * "<OfficeEntity, Integer>": truyền kiểu entity & khóa chính PK theo bảng
 * extends JpaRepository: kế thừa hàm có sẳn gọi DB
 */
public interface OfficeRepository extends JpaRepository<OfficeEntity, String> {

    OfficeEntity getByOfficeCode(String code);

    //cach 4: native query
    // tra ve map, @Query tạo query native / object
    @Query(nativeQuery = true, value =
            "SELECT officeCode, country, city " +
            "FROM offices;")
    List<Map> findAll2();

    // tra ve interface -> projection, co the dung map duoc
    @Query(nativeQuery = true, value =
            "SELECT  " +
            " e.employeeNumber, e.firstName AS employeeFirstName, e.email AS employeeEmail,  " +
            " o.officeCode, o.addressLine1, o.city   " +
            "FROM employees e  " +
            "INNER JOIN offices o ON o.officeCode = e.officeCode; ")
    List<OfficeEmployeeProjection> findAll3();

    //4 JPQL
    // query ḍang select entity
//    @Query(value = "select o from OfficeEntity o where o.officeCode = :code")
    @Query(value = "from OfficeEntity o where o.officeCode = :code")
    OfficeEntity findOneByCode(String code);

}
