package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.ui.Model;

import java.util.List;

public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {
    @Query("SELECT m FROM MonHoc m")
    List<MonHoc> getListMonHoc();
    @Query("SELECT m FROM MonHoc m WHERE m.mamonhoc = ?1")
    MonHoc getMonHocByMaMonHoc(String mamonhoc);

    @Override
    @Query("SELECT m FROM MonHoc m WHERE m.id = ?1")
    MonHoc getById(Integer integer);

    @Query("SELECT mh FROM MonHoc mh, MonHocKiHoc mhkh, KiHoc kh, Khoa k, BoMon bm WHERE " +
            "mhkh.IDmonhoc = mh.id AND mhkh.tblkihocID = kh.id AND mh.IDtblbomon = bm.id " +
            "AND bm.tblkhoaID = k.id AND k.id = ?1 AND kh.id = ?2")
    List<MonHoc> getMonHocListByKhoaAndKiHoc(int idKhoa, int idKiHoc);


}