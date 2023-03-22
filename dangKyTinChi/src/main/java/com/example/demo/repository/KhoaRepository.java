package com.example.demo.repository;

import com.example.demo.model.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhoaRepository extends JpaRepository<Khoa, Integer> {
    @Query("SELECT m FROM Khoa m WHERE m.id = ?1")
    Khoa getKhoaById(int id);
    @Query("SELECT m FROM Khoa m")
    List<Khoa> getListKhoa();
}