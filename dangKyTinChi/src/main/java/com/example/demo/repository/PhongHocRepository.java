package com.example.demo.repository;

import com.example.demo.model.PhongHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhongHocRepository extends JpaRepository<PhongHoc, Integer> {
    @Override
    @Query("SELECT p FROM PhongHoc p WHERE p.id = ?1")
    PhongHoc getById(Integer integer);
}
