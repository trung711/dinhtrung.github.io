package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblthanhvien")
@Data
public class ThanhVien extends BaseEntity{
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String hodem;
    @Column(nullable = false)
    private String ten;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngaysinh;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String sdt;
    private String ghichu;
    @Column(nullable = false)
    private String vaitro;
    @Column(nullable = false)
    private int tbldiachiID;
    @Column(nullable = false)
    private int tblbomonID = 0;
    private String masv;
    private String vitri;

    @Transient
    private DiaChi diaChi;
    @Transient
    private BoMon boMon;
    @Transient
    private SinhVienKhoa sinhVienKhoa;
}
