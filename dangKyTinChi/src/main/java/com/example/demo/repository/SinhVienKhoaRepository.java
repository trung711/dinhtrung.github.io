package com.example.demo.repository;

import com.example.demo.model.Khoa;
import com.example.demo.model.SinhVienKhoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SinhVienKhoaRepository extends JpaRepository<SinhVienKhoa, Integer> {
    @Query("SELECT k FROM SinhVienKhoa k WHERE k.tblsinhvienID = ?1")
    List<SinhVienKhoa> getSVKhoaByThanhVienId(int idThanhVien);

    @Query("SELECT k FROM SinhVienKhoa k WHERE k.tblsinhvienID = ?1 AND k.IDkhoa = ?2")
    SinhVienKhoa findSinhVienKhoa(int idSV, int idKhoa);

    @Override
    @Query("SELECT k FROM SinhVienKhoa k WHERE k.id = ?1")
    SinhVienKhoa getById(Integer integer);

    @Query("SELECT m FROM SinhVienKhoa m WHERE m.tblsinhvienID = ?1")
    SinhVienKhoa getSinhVienKhoaBySinhVienID(int tblsinhvienID);

    @Modifying
    @Query("UPDATE SinhVienKhoa SET nienKhoa = ?1, IDkhoa = ?2 WHERE id = ?3")
    void suaSinhVienKhoa(String nienKhoa, int tblkhoaId,int id);
}
