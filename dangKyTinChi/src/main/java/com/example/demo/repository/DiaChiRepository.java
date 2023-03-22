package com.example.demo.repository;

import com.example.demo.model.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiaChiRepository extends JpaRepository<DiaChi, Integer> {
    @Query("SELECT m FROM DiaChi m WHERE m.id = ?1")
    DiaChi getDiaChi(int id);
}