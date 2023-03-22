package com.example.demo.repository;

import com.example.demo.model.DangKiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DangKiHocRepository extends JpaRepository<DangKiHoc, Integer> {
    @Query("SELECT d FROM DangKiHoc d WHERE d.tblsinhvienkhoaID = ?1")
    List<DangKiHoc> getDangKyHocBySinhVienKhoaId(int id);

    @Query("SELECT d FROM DangKiHoc d WHERE d.tbllophocphanID = ?1 AND d.tblsinhvienkhoaID = ?2")
    DangKiHoc getDangKiHoc(int idLopHocPhan, int sinhVienKhoa);

    @Modifying
    @Query("DELETE FROM DangKiHoc d WHERE d.tbllophocphanID = ?1")
    void deleteAllByLopHocPhanId(int tbllophocphanID);
}
