package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblmonhoc")
@Data
public class MonHoc extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    @Column(nullable = false)
    private int sotinchi;
    @Column(nullable = false)
    private String mamonhoc;
    private String mota;
    @Column(nullable = false)
    private int IDtblbomon;

    @Transient
    private BoMon boMon;
}
