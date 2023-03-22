package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblphonghoc")
@Data
public class PhongHoc extends BaseEntity{
    @Column(nullable = false)
    private String ten;
    @Column(nullable = false)
    private int succhua;
    private String mota;
    @Column(nullable = false)
    private int tbltoanhaID;

    @Transient
    private ToaNha toaNha;
}
