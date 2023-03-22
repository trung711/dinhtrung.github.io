package com.example.demo.repository;

import com.example.demo.model.NamHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NamHocRepository extends JpaRepository<NamHoc, Integer> {
    @Query("SELECT m FROM NamHoc m WHERE m.id = ?1")
    NamHoc getNamHoc(int tblnamhocID);
}
