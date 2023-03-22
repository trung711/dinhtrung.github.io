package com.example.demo.repository;

import com.example.demo.model.TuanHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TuanHocRepository extends JpaRepository<TuanHoc, Integer> {
    @Override
    @Query("SELECT t FROM TuanHoc t WHERE t.id = ?1")
    public TuanHoc getById(Integer integer);
}
