package com.t3h.web_demo.controller;

import com.t3h.web_demo.storage.entity.Organization;
import com.t3h.web_demo.storage.repository.OrganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestRelationship {

    @Autowired
    OrganRepository orgRepo;

    @GetMapping("/api/v1/org")
    public ResponseEntity<List<Organization>> findOrganization() {
        return ResponseEntity.ok(orgRepo.findAll());
    }

    @GetMapping("/api/v1/org/{ordId}")
    public ResponseEntity<Organization> findOrganization(@PathVariable int ordId) {
        return ResponseEntity.ok(orgRepo.findById(ordId).get());// tự động join với entity có quan hệ - OneToOne
    }

    @PutMapping("/api/v1/org")
    public ResponseEntity<Organization> updateOrganization(@RequestBody Organization data) {
        Organization orgDB = orgRepo.findById(data.getId()).get();
        orgDB.setName(data.getName());
        orgDB.setAddress(data.getAddress());

        return ResponseEntity.ok(
                orgRepo.save(orgDB)
        );
    }

    @DeleteMapping("/api/v1/org/{ordId}")
    public ResponseEntity<String> removeOrganization(@PathVariable int ordId) {
        Organization orgDB = orgRepo.findById(ordId).get();
        orgRepo.delete(orgDB);

        return ResponseEntity.ok(
                "xoa thanh cong: " + ordId
        );
    }

}
