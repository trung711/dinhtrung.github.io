package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbllichhoc")
@Data
public class LichHoc extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    private String mota;
    @Column(nullable = false)
    private int tblkiphocID;
    @Column(nullable = false)
    private int tblngayhocID;
    @Column(nullable = false)
    private int tbltuanhocID;
    @Column(nullable = false)
    private int tblphonghocID;
    @Column(nullable = false)
    private int tblgiangvienID;
    @Column(nullable = false)
    private int IDtbllophocphan;

    @Transient
    private KipHoc kipHoc;
    @Transient
    private NgayHoc ngayHoc;
    @Transient
    private TuanHoc tuanHoc;
    @Transient
    private PhongHoc phongHoc;
    @Transient
    private ThanhVien giangVien;
    @Transient
    private LopHocPhan lopHocPhan;
}
