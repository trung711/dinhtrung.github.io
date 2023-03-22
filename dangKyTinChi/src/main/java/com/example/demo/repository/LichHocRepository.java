package com.example.demo.repository;

import com.example.demo.model.LichHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LichHocRepository extends JpaRepository<LichHoc, Integer> {
    @Query("SELECT l FROM LichHoc l WHERE l.IDtbllophocphan = ?1")
    List<LichHoc> getLichHocForHocPhan(int idHocPhan);

    @Query("SELECT l FROM LichHoc l WHERE l.IDtbllophocphan = ?1 OR l.IDtbllophocphan = ?2 GROUP BY l.tblkiphocID, l.tblngayhocID, l.tbltuanhocID HAVING COUNT(*) > 1 ")
    List<LichHoc> checkLichHoc(int idLopHocPhan1, int idLopHocPhan2);
}
