package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblsinhvienkhoa")
@Data
public class SinhVienKhoa extends BaseEntity{
    @Column(nullable = false)
    private String nienkhoa;
    @Column(nullable = false)
    private boolean danghoc;
    @Column(nullable = false)
    private int tblsinhvienID;
    @Column(nullable = false)
    private int IDkhoa;

    @Transient
    private ThanhVien sinhVien;
    @Transient
    private Khoa khoa;
}
