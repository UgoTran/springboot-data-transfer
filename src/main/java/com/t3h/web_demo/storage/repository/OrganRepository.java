package com.t3h.web_demo.storage.repository;

import com.t3h.web_demo.storage.entity.CustomerEntity;
import com.t3h.web_demo.storage.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganRepository extends JpaRepository<Organization, Integer> {


}
