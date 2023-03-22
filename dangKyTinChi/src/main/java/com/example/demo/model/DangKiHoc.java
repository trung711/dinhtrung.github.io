package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbldangkihoc")
@Data
public class DangKiHoc extends BaseEntity{
    @Column(nullable = false)
    private int tblsinhvienkhoaID;
    @Column(nullable = false)
    private int tbllophocphanID;
    private String ghichu;

    @Transient
    private SinhVienKhoa sinhVienKhoa;
    @Transient
    private LopHocPhan lopHocPhan;
}
