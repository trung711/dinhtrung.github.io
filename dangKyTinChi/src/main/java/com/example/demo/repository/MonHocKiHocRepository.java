package com.example.demo.repository;

import com.example.demo.model.MonHoc;
import com.example.demo.model.MonHocKiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonHocKiHocRepository extends JpaRepository<MonHocKiHoc, Integer> {
    @Query("SELECT m FROM MonHocKiHoc m WHERE m.IDmonhoc = ?1")
    MonHocKiHoc getMonHocKiHocByMonHocId(int id);

    @Query("SELECT m FROM MonHocKiHoc m WHERE m.id = ?1")
    MonHocKiHoc getMonHocKiHocById(int id);

    @Query("SELECT m FROM MonHocKiHoc m WHERE m.tblkihocID = ?1")
    List<MonHocKiHoc> getMonHocKiHocListByKiHocId(int id);

    @Query("SELECT m FROM MonHocKiHoc m WHERE m.tblkihocID = ?1 AND m.IDmonhoc = ?2")
    MonHocKiHoc getMonHocKiHocByMonHocAndKiHoc(int tblkihocID, int IDmonhoc);
}