package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbllophocphan")
@Data
public class LopHocPhan extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    @Column(nullable = false)
    private int sisotoida;
    @Column(nullable = false)
    private int sisohientai;
    @Column(nullable = false)
    private int tblmonhockihocID;
    private String mota;

    @Transient
    private MonHocKiHoc monHocKiHoc;
}
