package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblkihoc")
@Data
public class KiHoc extends BaseEntity{
    @Column(nullable = false)
    private boolean danghoc;
    @Column(nullable = false)
    private boolean dangdangky;
    @Column(nullable = false)
    private int tblnamhocID;
    @Column(nullable = false)
    private int tblhockiID;
    @Transient
    private NamHoc namHoc;
    @Transient
    private HocKi hocKi;
}
