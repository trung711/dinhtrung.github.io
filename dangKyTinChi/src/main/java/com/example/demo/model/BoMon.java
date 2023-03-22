package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "tblbomon")
@Data
public class BoMon extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    @Column(nullable = false)
    private String mota;
    @Column(nullable = false)
    private int tblkhoaID;

    @Transient
    private Khoa khoa;
    @Transient
    private List<ThanhVien> listGiangVien;
    @Transient
    private List<MonHoc> listMonHoc;
}
