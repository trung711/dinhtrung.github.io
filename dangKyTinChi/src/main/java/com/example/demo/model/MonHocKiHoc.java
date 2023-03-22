package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblmonhockihoc")
@Data
public class MonHocKiHoc extends BaseEntity{
    @Column(nullable = false)
    private int IDmonhoc;
    @Column(nullable = false)
    private int tblkihocID;

    @Transient
    private MonHoc monHoc;
    @Transient
    private KiHoc kiHoc;
}
