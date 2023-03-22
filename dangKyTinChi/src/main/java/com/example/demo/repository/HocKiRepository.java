package com.example.demo.repository;

import com.example.demo.model.HocKi;
import com.example.demo.model.KiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HocKiRepository extends JpaRepository<HocKi, Integer> {
    @Query("SELECT m FROM HocKi m WHERE m.id = ?1")
    HocKi getHocKi(int tblhockiID);
}
