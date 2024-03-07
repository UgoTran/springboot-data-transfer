package com.t3h.web_demo.storage.repository;

import com.t3h.web_demo.storage.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {


}
