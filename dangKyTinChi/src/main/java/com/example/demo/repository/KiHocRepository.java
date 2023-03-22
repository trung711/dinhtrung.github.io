package com.example.demo.repository;

import com.example.demo.model.KiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KiHocRepository extends JpaRepository<KiHoc, Integer> {
    @Query("SELECT m FROM KiHoc m WHERE m.id = ?1")
    KiHoc getKiHocById(int id);

    @Query("SELECT m FROM KiHoc m WHERE m.dangdangky = 1")
    List<KiHoc> getListKiHoc();
}
