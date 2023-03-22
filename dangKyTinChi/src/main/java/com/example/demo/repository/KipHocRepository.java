package com.example.demo.repository;

import com.example.demo.model.KipHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KipHocRepository extends JpaRepository<KipHoc, Integer> {
    @Override
    @Query("SELECT k FROM KipHoc k WHERE k.id = ?1")
    KipHoc getById(Integer integer);
}
