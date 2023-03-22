package com.example.demo.repository;

import com.example.demo.model.BoMon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoMonRepository extends JpaRepository<BoMon, Integer> {
    @Query("SELECT m FROM BoMon m WHERE m.id = ?1")
    BoMon getBoMonbyId(int id);

    @Query("SELECT m FROM BoMon m WHERE m.tblkhoaID = ?1")
    List<BoMon> getListBoMonByKhoaId(int id);
}