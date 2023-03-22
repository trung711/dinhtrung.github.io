package com.example.demo.repository;

import com.example.demo.model.KiHoc;
import com.example.demo.model.LopHocPhan;
import com.example.demo.model.MonHoc;
import com.example.demo.model.MonHocKiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LopHocPhanRepository extends JpaRepository<LopHocPhan, Integer> {
    @Query("SELECT l FROM LopHocPhan l, MonHocKiHoc m, KiHoc k WHERE l.tblmonhockihocID = m.id AND m.tblkihocID = k.id AND k.id = ?1")
    List<LopHocPhan> lopHocPhanList(int idKiHoc);

    @Query("SELECT l FROM LopHocPhan l WHERE l.id = ?1")
    LopHocPhan getLopHocPhanById(int id);

    @Query("SELECT lhp FROM LopHocPhan lhp, MonHocKiHoc mhkh, MonHoc mh, KiHoc kh WHERE lhp.tblmonhockihocID = mhkh.id AND mhkh.IDmonhoc = mh.id AND mhkh.tblkihocID = kh.id AND mh.id = ?1 AND kh.id = ?2")
    List<LopHocPhan> getLopHocPhanListByMonHocAndKiHoc(int idMonHoc, int idKiHoc);


}
