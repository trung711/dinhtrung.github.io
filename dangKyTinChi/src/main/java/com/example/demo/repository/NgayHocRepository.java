package com.example.demo.repository;

import com.example.demo.model.NgayHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NgayHocRepository extends JpaRepository<NgayHoc, Integer> {
    @Override
    @Query("SELECT n FROM NgayHoc n WHERE n.id = ?1")
    NgayHoc getById(Integer integer);
}
